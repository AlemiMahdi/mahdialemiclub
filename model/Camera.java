package mahdialemiclub.model;

public class Camera extends Item {

    private String sensorType;
    private String lensType;

    public Camera(String id, String name, double basePricePerDay, String sensorType, String lensType) {
        super(id, name, basePricePerDay);
        this.sensorType = sensorType;
        this.lensType = lensType;

    }

    @Override
    public String getItemDetails() {
        return String.format("Cemera - Sensor: %s, Lens: %s", sensorType, lensType);
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getLensType() {
        return lensType;
    }

}
