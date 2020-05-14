package model;
import java.time.*;
import java.util.Objects;

public class AbstractSpace implements Space, Cloneable {

    protected Person person;
    protected Vehicle vehicle;
    protected LocalDate startDate;

    protected AbstractSpace() {
        this.person = Person.UNKNOWN_PERSON;
        vehicle = Vehicle.NO_VEHICLE;
        startDate = LocalDate.now();
    }

    protected AbstractSpace(Person person, LocalDate startDate) {
        if (startDate == null)throw new NullPointerException();
        if(startDate.isBefore(LocalDate.now())){throw new IllegalArgumentException();}
        this.person = person;
        vehicle = Vehicle.NO_VEHICLE;
        this.startDate = startDate;
    }
    protected AbstractSpace(Person person, Vehicle vehicle, LocalDate startDate){
        if (startDate == null)throw new NullPointerException();
        if(startDate.isBefore(LocalDate.now())){throw new IllegalArgumentException();}
        this.person = person;
        this.vehicle = vehicle;
        this.startDate = startDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        if(startDate == null)throw new NullPointerException();
        if(startDate.isBefore(LocalDate.now()))throw new IllegalArgumentException();
        this.startDate = startDate;
    }

    @Override
    public Period getPeriod() {
        return Period.between(LocalDate.now(),startDate);
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
        return String.format("<%s> TC: <%s> startDate: <%s>", person, vehicle,startDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSpace)) return false;
        AbstractSpace that = (AbstractSpace) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(vehicle, that.vehicle) &&
                Objects.equals(startDate,that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, vehicle,startDate);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractSpace abstractSpace = (AbstractSpace) super.clone();
        abstractSpace.setPerson(person);
        abstractSpace.setVehicle(vehicle);
        abstractSpace.setStartDate(startDate);
        return abstractSpace;
    }
}
