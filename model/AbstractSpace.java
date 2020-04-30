package model;

import java.util.Objects;

public class AbstractSpace implements Space, Cloneable {

    protected Person person;
    protected Vehicle vehicle;

    protected AbstractSpace() {
        person = Person.UNKNOWN_PERSON;
        vehicle = Vehicle.NO_VEHICLE;
    }

    protected AbstractSpace(Person person) {
        this.person = person;
        vehicle = Vehicle.NO_VEHICLE;
    }

    protected AbstractSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean isEmptySpace() {
        return vehicle == null || vehicle == Vehicle.NO_VEHICLE || vehicle.getType().equals(VehicleTypes.NONE);
    }

    @Override
    public String toString() {
        return String.format("<%s> TC: <%s>", person, vehicle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSpace)) return false;
        AbstractSpace that = (AbstractSpace) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, vehicle);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractSpace abstractSpace = (AbstractSpace) super.clone();
        abstractSpace.person = person;
        abstractSpace.vehicle = vehicle;
        return abstractSpace;
    }
}
