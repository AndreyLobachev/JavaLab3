package model;

import java.time.LocalDate;

public interface Floor {
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

    Space[] getSpaces(VehicleTypes type);

    Space[] getLiberalSpace();

    Vehicle[] getVehicles();

    boolean equals(Object o);

    int hashCode();

    String toString();

    LocalDate getEarlyFinishDate() throws NoRentedSpaceException;

    Space getSpaceWithEarlyFinishDate() throws NoRentedSpaceException;
}
