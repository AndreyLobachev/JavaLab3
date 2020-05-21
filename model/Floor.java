package model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

public interface Floor extends Comparable<Floor>, Iterable<Space>, Collection<Space> {

    boolean add(Space rentedSpace);

    boolean add(int index, Space rentedSpace);

    Space getSpace(int index);

    Space getSpace(String registrationNumber);

    int getIndexSpace(String registrationNumber);

    Space setSpace(int index, Space rentedSpace);

    Space removeSpace(int index);

    Space removeSpace(String registrationNumber);

    boolean removeSpace(Space space);

    int getIndexSpace(Space space);

    int getValueSpaces(Person person);

    boolean isSpaceExist(String registrationNumber);

    int numberOccupiedSpaces();

    int totalNumberSpaces();

    List<Space> getSpaces(VehicleTypes type);

    Deque<Space> getLiberalSpace();

    Collection<Vehicle> getVehicles();

    LocalDate getEarlyFinishDate() throws NoRentedSpaceException;

    Space getSpaceWithEarlyFinishDate() throws NoRentedSpaceException;

    boolean equals(Object o);

    int hashCode();

    String toString();

    Floor clone() throws CloneNotSupportedException;
}
