package mahdialemiclub.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import mahdialemiclub.model.Rental;
import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;
import mahdialemiclub.service.RentalService;

public class RentalMenu {
    private RentalService rentalService;
    private Scanner scanner;

    public RentalMenu(RentalService rentalService, MemberRegistry memberRegistry,
            Inventory inventory, Scanner scanner) {
        this.rentalService = rentalService;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== UTHYRNINGSMENY ===");
            System.out.println("Tryck 1 för att börja en uthyrning");
            System.out.println("Tryck 2 för att avsluta en uthyrning");
            System.out.println("Tryck 3 för att visa aktiva uthyrningar");
            System.out.println("Tryck 0 för att gå tillbaka till huvudmenyn");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createRental();
                case 2 -> completeRental();
                case 3 -> showActiveRentals();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Ogiltig val");
            }
        }
    }

    private void createRental() {
        try {
            System.out.print("Ange medlems-ID: ");
            String memberID = scanner.nextLine();
            System.out.print("Ange utrustnings-ID: ");
            String itemID = scanner.nextLine();
            System.out.print("Ange startdatum (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());
            System.out.print("Ange slutdatum (YYYY-MM-DD): ");
            LocalDate end = LocalDate.parse(scanner.nextLine());
            Rental rental = rentalService.createRental(memberID, itemID, start, end);
            System.out.println("Uthyrning skapad: " + rental.getId());
        } catch (Exception e) {
            System.out.println("Kunde inte skapa uthyrning: " + e.getMessage());
        }
    }

    private void completeRental() {
        try {
            System.out.print("Ange uthyrnings-ID: ");
            String rentalId = scanner.nextLine();
            System.out.print("Återlämningsdatum (YYYY-MM-DD): ");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine());
            double cost = rentalService.completeRental(rentalId, returnDate);
            System.out.printf("Uthyrning avslutad. Total kostnad: %.2f kr%n", cost);
        } catch (Exception e) {
            System.out.println("Kunde inte avsluta uthyrning: " + e.getMessage());
        }
    }

    private void showActiveRentals() {
        List<Rental> active = rentalService.getActiveRentals();
        if (active.isEmpty())
            System.out.println("Inga aktiva uthyrningar.");
        else
            active.forEach(r -> System.out.printf("%s - %s hyr %s (%s - %s)%n",
                    r.getId(), r.getMember().getName(), r.getItem().getName(),
                    r.getStartDate(), r.getEnDate()));
    }
}
