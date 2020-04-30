package model;

public interface Space {
    Person getPerson();

    void setPerson(Person person);

    Vehicle getVehicle();

    void setVehicle(Vehicle vehicle);

    boolean isEmptySpace();

    String toString();

    boolean equals(Object o) ;

    int hashCode() ;

    Object clone() throws CloneNotSupportedException;
}
