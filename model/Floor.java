package model;

public interface Floor {
    boolean add(Space rentedSpace);

    boolean add(int index, Space rentedSpace);

    Space getSpace(int index);

    Space getSpace(String registrationNumber);

    int getIndexSpace(String registrationNumber);

    Space setSpace(int index, Space rentedSpace);

    Space removeSpace(int index);

    Space removeSpace(String registrationNumber);

    boolean isSpaceExist(String registrationNumber);

    int numberOccupiedSpaces();

    int totalNumberSpaces();

    Space[] getSpaces(VehicleTypes type);

    Space[] getLiberalSpace();

    Vehicle[] getVehicles();
}
