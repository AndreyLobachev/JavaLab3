package model;

public interface Space {
    Person getPerson();

    void setPerson(Person person);

    Vehicle getVehicle();

    void setVehicle(Vehicle vehicle);

    boolean isEmptySpace();
}
