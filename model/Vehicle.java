package model;

import java.util.Objects;

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
        if (type.equals(VehicleTypes.NONE)) {
            return "NONE";
        }
        return String.format("<%s> <%s> <%s> regNumber: <%s> ", manufacturer, model, type, registrationNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber) &&
                Objects.equals(manufacturer, vehicle.manufacturer) &&
                Objects.equals(model, vehicle.model) &&
                type == vehicle.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, manufacturer, model, type);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Vehicle vehicle = (Vehicle) super.clone();
        vehicle.type = type;
        vehicle.manufacturer = manufacturer;
        vehicle.model = model;
        vehicle.registrationNumber = registrationNumber;
        return vehicle;
    }
}
