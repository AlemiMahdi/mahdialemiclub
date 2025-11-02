package mahdialemiclub.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private String id;
    private Member member;
    private Item item;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;
    private double totalCost;

    public Rental(String id, Member member, Item item, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.member = member;
        this.item = item;
        this.startDate = startDate;
        this.endDate = endDate;
        this.item.setAvailable(false);

    }

    public long calculateRentalDays() {
        LocalDate returnDate = actualReturnDate != null ? actualReturnDate : endDate;
        return ChronoUnit.DAYS.between(startDate, returnDate);
    }

    public void completeRental(double totalCost, LocalDate returnDate) {
        this.actualReturnDate = returnDate;
        this.totalCost = totalCost;
        this.item.setAvailable(true);
        this.member.addRentalToHistory(this);
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEnDate() {
        return endDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isActive() {
        return actualReturnDate == null;
    }
}
