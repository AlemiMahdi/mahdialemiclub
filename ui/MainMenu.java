package mahdialemiclub.ui;

import java.util.Scanner;
import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;
import mahdialemiclub.service.RentalService;

public class MainMenu {
    private Scanner scanner;
    private MemberRegistry memberRegistry;
    private RentalService rentalService;
    private Inventory inventory;

    public MainMenu(MemberRegistry memberRegistry, RentalService rentalService, Inventory inventory) {
        this.scanner = new Scanner(System.in);
        this.memberRegistry = memberRegistry;
        this.rentalService = rentalService;
        this.inventory = inventory;
    }

    public void display() {
        while (true) {
            System.out.println("\n=== HUVUDMENY ===");
            System.out.println("Tryck 1 för medelemshantering");
            System.out.println("Tryck 2 för ttrustningshantering");
            System.out.println("Tryck 3 för uthyrningsmeny");
            System.out.println("Tryck 4 för info om ekonomin");
            System.out.println("Tryck 0 för att avsluta applikationen");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig val. Ange en siffra.");
                continue;
            }

            switch (choice) {
                case 1 -> new MemberMenu(memberRegistry, scanner).displayMenu();
                case 2 -> new ItemMenu(inventory, scanner).displayMenu();
                case 3 -> new RentalMenu(rentalService, memberRegistry, inventory, scanner).displayMenu();
                case 4 -> new FinancialMenu(rentalService).displayMenu();
                case 0 -> {
                    System.out.println("Tack för användning av tjänsten! Välkommen åter!");
                    return;
                }
                default -> System.out.println("Ogiltig val");
            }
        }
    }
}
