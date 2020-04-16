package model;

public class Vehicle {
    private String registrationNumber;
    private String manufacturer;
    private String model;
    private VehicleTypes type;
    public final static Vehicle NO_VEHICLE = new Vehicle(VehicleTypes.NONE);

    public Vehicle(VehicleTypes type) {
        this.registrationNumber = "";
        this.manufacturer = "";
        this.model = "";
        this.type = type;
    }

    public Vehicle(String registrationNumber, String manufacturer, String model, VehicleTypes type) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }


    public String getModel() {
        return model;
    }

    public VehicleTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vehicle{");
        sb.append("registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
