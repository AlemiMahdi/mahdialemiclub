package mahdialemiclub.ui;

import java.util.List;
import java.util.Scanner;
import mahdialemiclub.model.*;
import mahdialemiclub.repository.Inventory;

public class ItemMenu {
    private Inventory inventory;
    private Scanner scanner;

    public ItemMenu(Inventory inventory, Scanner scanner) {
        this.inventory = inventory;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== UTRUSTNINGSMENY ===");
            System.out.println("Tryck 1 för att lista all utrustning");
            System.out.println("Tryck 2 för att se tillgänglig utrustning");
            System.out.println("Tryck 3 för att filtrera efter typ");
            System.out.println("Tryck 0 för att gå tillbaka till huvudmenyn");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> listAllItems();
                case 2 -> listAvailableItems();
                case 3 -> filterItemsByType();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Ogiltig val");
            }
        }
    }

    private void listAllItems() {
        displayItems(inventory.getAllItems(), "All utrustning");
    }

    private void listAvailableItems() {
        displayItems(inventory.getAvailableItems(), "Tillgänglig utrustning");
    }

    private void filterItemsByType() {
        System.out.println("Välj typ: 1.Kameror 2.Drönare 3.GoPros");
        int choice = Integer.parseInt(scanner.nextLine());
        List<Item> items = switch (choice) {
            case 1 -> inventory.filterItemsByType(Camera.class);
            case 2 -> inventory.filterItemsByType(Drone.class);
            case 3 -> inventory.filterItemsByType(GoPro.class);
            default -> List.of();
        };
        displayItems(items, "Filtrerad utrustning");
    }

    private void displayItems(List<Item> items, String title) {
        if (items.isEmpty()) {
            System.out.println(title + ": Ingen utrustning hittades.");
        } else {
            System.out.println("\n" + title + ":");
            System.out.printf("%-5s %-25s %-12s %-50s %-12s%n", "ID", "Namn", "Pris/dag", "Detaljer", "Tillgänglig");
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------");
            for (Item item : items) {
                System.out.printf("%-5s %-25s %-12.2f %-50s %-12s%n",
                        item.getId(),
                        item.getName(),
                        item.getBasePricePerDay(),
                        item.getItemDetails(),
                        item.isAvailable() ? "Ja" : "Nej");
            }
            System.out.println();
        }
    }
}
