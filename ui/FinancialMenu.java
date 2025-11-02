package mahdialemiclub.ui;

import mahdialemiclub.service.RentalService;

public class FinancialMenu {
    private RentalService rentalService;

    public FinancialMenu(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public void displayMenu() {
        System.out.println("\n=== EKONOMI ===");
        double totalRevenue = rentalService.calculateTotalRevenue();
        System.out.printf("Totala intäkter: %.2f kr%n", totalRevenue);
    }
}
