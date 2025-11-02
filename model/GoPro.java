package mahdialemiclub.model;

public class GoPro extends Item {

    private String resolution;
    private boolean isWaterproof;
    private int batterLife;

    public GoPro(String id, String name, double basePricePerDay, String resolution, boolean isWaterproof,
            int batterLife) {
        super(id, name, basePricePerDay);
        this.resolution = resolution;
        this.isWaterproof = isWaterproof;
        this.batterLife = batterLife;

    }

    @Override
    public String getItemDetails() {
        return String.format("GoPro - Resolution: %s, Waterproof: %s, Battery: %dmin", resolution,
                isWaterproof ? "Yes" : "no", batterLife);
    }

    public String getResolution() {
        return resolution;
    }

    public boolean isWaterproof() {
        return isWaterproof;
    }

    public int getBatterLife() {
        return batterLife;
    }

}
