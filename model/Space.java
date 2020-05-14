package model;
import java.time.*;
public interface Space {
    Person getPerson();
    void setPerson(Person person);
    Vehicle getVehicle();
    void setVehicle(Vehicle vehicle);
    boolean isEmptySpace();
    LocalDate getStartDate();
    void setStartDate(LocalDate date) throws NullPointerException;
    Period getPeriod();
    String toString();
    boolean equals(Object o);
    int hashCode();
    Object clone() throws CloneNotSupportedException;

}
