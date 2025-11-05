package mahdialemiclub.ui;

import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;

import mahdialemiclub.service.RentalService;

public class KonsolMenu {
    public static void main(String[] args) {

        MemberRegistry memberRegistry = new MemberRegistry();
        Inventory inventory = new Inventory();

        RentalService rentalService = new RentalService(inventory, memberRegistry);

        SampleDataLoader.loadSampleData(memberRegistry, inventory);

        MainMenu mainMenu = new MainMenu(memberRegistry, rentalService, inventory);
        mainMenu.display();
    }
}
