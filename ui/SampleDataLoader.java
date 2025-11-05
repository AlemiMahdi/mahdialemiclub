package mahdialemiclub.ui;

import mahdialemiclub.model.Camera;
import mahdialemiclub.model.Drone;
import mahdialemiclub.model.GoPro;
import mahdialemiclub.model.Member;
import mahdialemiclub.repository.Inventory;
import mahdialemiclub.repository.MemberRegistry;

public class SampleDataLoader {

    public static void loadSampleData(MemberRegistry membershipService, Inventory inventory) {

        membershipService.addMember("M1", "Mahdi Alemi", Member.MemberLevel.STUDENT);
        membershipService.addMember("M2", "Lars Blomgren", Member.MemberLevel.PREMIUM);
        membershipService.addMember("M3", "Annie Lööf", Member.MemberLevel.STANDARD);

        inventory.addItem(new Camera("CAM1", "Canon EOS R5", 299.0, "Full-frame", "RF 24-70mm"));
        inventory.addItem(new GoPro("GP1", "Hero 11 Black", 399.0, "4K", true, 120));
        inventory.addItem(new Drone("DRN1", "DJI Mavic 3", 199.0, 240, 2000));
        inventory.addItem(new Camera("CAM2", "Nikon Z6 II", 279.0, "Full-frame", "Nikkor Z 24-70mm"));
        inventory.addItem(new Camera("CAM3", "Sony A7 III", 259.0, "Full-frame", "Sony 28-70mm"));
        inventory.addItem(new Drone("DRN2", "DJI Air 2S", 179.0, 150, 1800));
        inventory.addItem(new Drone("DRN3", "Parrot Anafi", 149.0, 120, 1500));
        inventory.addItem(new GoPro("GP2", "Hero 10 Black", 349.0, "5K", true, 100));
        inventory.addItem(new GoPro("GP3", "Hero 9 Black", 299.0, "4K", true, 90));
    }
}
