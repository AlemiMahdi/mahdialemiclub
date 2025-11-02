// service/RentalService.java
package mahdialemiclub.service;

import mahdialemiclub.model.*;
import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;
import java.time.LocalDate;
import java.util.*;

public class RentalService {
    private Inventory inventory;
    private MemberRegistry memberRegistry;
    private Map<String, Rental> activeRentals;
    private Map<Member.MemberLevel, PricePolicy> pricePolicies;
    private int rentalCounter;

    public RentalService(Inventory inventory, MemberRegistry memberRegistry) {
        this.inventory = inventory;
        this.memberRegistry = memberRegistry;
        this.activeRentals = new HashMap<>();
        this.pricePolicies = new HashMap<>();
        this.rentalCounter = 1;
        initializePricePolicies();
    }

    private void initializePricePolicies() {
        pricePolicies.put(Member.MemberLevel.STANDARD, new mahdialemiclub.membership.StandardPrice());
        pricePolicies.put(Member.MemberLevel.STUDENT, new mahdialemiclub.membership.StudentPrice());
        pricePolicies.put(Member.MemberLevel.PREMIUM, new mahdialemiclub.membership.PremiumPrice());
    }

    public Rental createRental(String memberId, String itemId, LocalDate startDate, LocalDate endDate) {
        try {
            Member member = memberRegistry.findMemberById(memberId);
            Item item = inventory.findItemById(itemId);

            if (!item.isAvailable()) {
                throw new IllegalStateException("Varan är ej tillgänglig för uthyr");
            }

            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("Slutdatum kan inte vara innan startdatum");
            }

            String rentalId = "R" + rentalCounter++;
            Rental rental = new Rental(rentalId, member, item, startDate, endDate);
            activeRentals.put(rentalId, rental);

            return rental;
        } catch (Exception e) {
            throw new RuntimeException("Uthyr gick ej att skapa: " + e.getMessage(), e);
        }
    }

    public double completeRental(String rentalId, LocalDate returnDate) {
        try {
            Rental rental = activeRentals.get(rentalId);
            if (rental == null) {
                throw new IllegalArgumentException("Rental not found");
            }

            if (returnDate.isBefore(rental.getStartDate())) {
                throw new IllegalArgumentException("Return date cannot be before start date");
            }

            int days = (int) rental.calculateRentalDays();
            if (days < 1)
                days = 1;

            PricePolicy policy = pricePolicies.get(rental.getMember().getLevel());
            double totalCost = policy.calculatePrice(rental.getItem().getBasePricePerDay(), days);

            rental.completeRental(totalCost, returnDate);
            activeRentals.remove(rentalId);

            return totalCost;
        } catch (Exception e) {
            throw new RuntimeException("Failed to complete rental: " + e.getMessage(), e);
        }
    }

    public List<Rental> getActiveRentals() {
        return new ArrayList<>(activeRentals.values());
    }

    public double calculateTotalRevenue() {
        double total = 0;
        for (Member member : memberRegistry.getAllMembers()) {
            for (Rental rental : member.getRentalHistory()) {
                total += rental.getTotalCost();
            }
        }
        return total;
    }

    public void setPricePolicy(Member.MemberLevel level, PricePolicy policy) {
        pricePolicies.put(level, policy);
    }
}