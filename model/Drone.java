package mahdialemiclub.model;

public class Drone extends Item {

    private int maxFlightTime;
    private int maxRange;

    public Drone(String id, String name, double basePricePerDay, int maxFlightTime, int maxRange) {
        super(id, name, basePricePerDay);
        this.maxFlightTime = maxFlightTime;
        this.maxRange = maxRange;
    }

    @Override
    public String getItemDetails() {
        return String.format("Drone - Flight Time: %dmin, Range: %dm", maxFlightTime, maxRange);
    }

    public int getMaxFlightTime() {
        return maxFlightTime;
    }

    public int getMaxRange() {
        return maxRange;
    }

}
