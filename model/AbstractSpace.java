package model;

public class AbstractSpace implements Space{
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
        return vehicle==null || vehicle == Vehicle.NO_VEHICLE || vehicle.getType().equals(VehicleTypes.NONE);
    }
}
