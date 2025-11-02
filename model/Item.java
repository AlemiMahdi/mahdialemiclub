package mahdialemiclub.model;

public abstract class Item {
    private String id;
    private String name;
    private double basePricePerDay;
    private boolean isAvailable;

    public Item(String id, String name, double basePricePerDay) {
        this.id = id;
        this.name = name;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;

    }

    public abstract String getItemDetails();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
