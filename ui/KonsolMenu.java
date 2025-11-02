package mahdialemiclub.ui;

import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;
import mahdialemiclub.service.MembershipService;
import mahdialemiclub.service.RentalService;

public class KonsolMenu {
    public static void main(String[] args) {

        MemberRegistry memberRegistry = new MemberRegistry();
        Inventory inventory = new Inventory();
        MembershipService membershipService = new MembershipService(memberRegistry);
        RentalService rentalService = new RentalService(inventory, memberRegistry);

        SampleDataLoader.loadSampleData(membershipService, inventory);

        MainMenu mainMenu = new MainMenu(membershipService, rentalService, inventory);
        mainMenu.display();
    }
}
