package mahdialemiclub.ui;

import java.util.List;
import java.util.Scanner;
import mahdialemiclub.model.Member;
import mahdialemiclub.repository.MemberRegistry;

public class MemberMenu {

    private MemberRegistry memberRegistry;
    private Scanner scanner;

    public MemberMenu(MemberRegistry memberRegistry, Scanner scanner) {

        this.scanner = scanner;
        this.memberRegistry = memberRegistry;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== MEDLEMSMENY ===");
            System.out.println("Tryck 1 för att lägga ett till medlem");
            System.out.println("Tryck 2 för att söka efter ett medlem");
            System.out.println("Tryck 3 för att uppdatera ett medlem");
            System.out.println("Tryck 4 för att se en lista på alla medlemmar");
            System.out.println("Tryck 5 för att ta bort ett medlem");
            System.out.println("Tryck 0 för att gå tillbaka till huvudmenyn");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addMember();
                case 2 -> searchMember();
                case 3 -> updateMember();
                case 4 -> listAllMembers();
                case 5 -> removeMember();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Ogiltig val!");
            }
        }
    }

    private void addMember() {
        System.out.print("Ange medlems-ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        System.out.print("Ange namn: ");
        String name = scanner.nextLine().trim();

        System.out.println("Välj medlemsnivån (1.Standard 2.Student 3.Premium): ");
        int levelChoice = Integer.parseInt(scanner.nextLine());
        Member.MemberLevel level = switch (levelChoice) {
            case 1 -> Member.MemberLevel.STANDARD;
            case 2 -> Member.MemberLevel.STUDENT;
            case 3 -> Member.MemberLevel.PREMIUM;
            default -> Member.MemberLevel.STANDARD;
        };

        try {
            Member member = memberRegistry.addMember(id, name, level);
            System.out.println("Medlem " + member.getName() + " är tillagd.");
        } catch (Exception e) {
            System.out.println("Det gick ej att lägga till medlem: " + e.getMessage());
        }
    }

    private void searchMember() {
        System.out.print("Ange namnet på medlemmen du söker: ");
        String name = scanner.nextLine();
        List<Member> members = memberRegistry.searchMembersByName(name);
        if (members.isEmpty())
            System.out.println("Medlem hittades ej.");
        else
            displayMembers(members, "Sökresultat");
    }

    private void updateMember() {
        System.out.print("Ange ID till medlem som ska uppdateras: ");
        String id = scanner.nextLine();
        try {
            Member member = memberRegistry.findMemberById(id);
            System.out.print("Nytt namn (" + member.getName() + "): ");
            String newName = scanner.nextLine();
            System.out.println("Välj medlemsnivån (1.Standard 2.Student 3.Premium): ");
            int levelChoice = Integer.parseInt(scanner.nextLine());
            Member.MemberLevel newLevel = switch (levelChoice) {
                case 1 -> Member.MemberLevel.STANDARD;
                case 2 -> Member.MemberLevel.STUDENT;
                case 3 -> Member.MemberLevel.PREMIUM;
                default -> member.getLevel();
            };
            if (memberRegistry.updateMember(id, newName, newLevel))
                System.out.println("Medlem uppdaterad.");
            else
                System.out.println("Gick ej att uppdatera medlem.");
        } catch (Exception e) {
            System.out.println("Kunde inte hitta medlem: " + e.getMessage());
        }
    }

    private void listAllMembers() {
        List<Member> members = memberRegistry.getAllMembers();
        if (members.isEmpty())
            System.out.println("Medlemsregistret är tomt");
        else
            displayMembers(members, "Lista för all medlemmar");
    }

    private void removeMember() {
        System.out.print("Ange ID till medlem som ska tas bort: ");
        String id = scanner.nextLine();
        try {
            if (memberRegistry.removeMember(id))
                System.out.println("Medlem borttagen.");
            else
                System.out.println("Kunde ej hitta medlem.");
        } catch (Exception e) {
            System.out.println("Kunde inte ta bort medlem: " + e.getMessage());
        }
    }

    private void displayMembers(List<Member> members, String title) {
        if (members.isEmpty()) {
            System.out.println(title + ": Inga medlemmar hittades.");
        } else {
            System.out.println("\n" + title + ":");

            System.out.printf("%-5s %-25s %-10s%n", "ID", "Namn", "Nivå");
            System.out.println("-------------------------------------------------");

            for (Member member : members) {
                System.out.printf("%-5s %-25s %-10s%n",
                        member.getId(),
                        member.getName(),
                        member.getLevel());
            }
            System.out.println();
        }
    }
}
