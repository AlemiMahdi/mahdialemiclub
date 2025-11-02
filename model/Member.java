package mahdialemiclub.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private MemberLevel level;
    private List<Rental> rentalHistory;

    public enum MemberLevel {
        STANDARD, STUDENT, PREMIUM
    }

    public Member(String id, String name, MemberLevel level) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.rentalHistory = new ArrayList<>();
    }

    public void addRentalToHistory(Rental rental) {
        rentalHistory.add(rental);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MemberLevel getLevel() {
        return level;
    }

    public void setLevel(MemberLevel level) {
        this.level = level;
    }

    public List<Rental> getRentalHistory() {
        return new ArrayList<>(rentalHistory);
    }
}
