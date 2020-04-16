package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnersFloor implements Floor {
    private Space[] spaces;
    private int amountOccupiedSpaces;

    public OwnersFloor() {
        spaces = new RentedSpace[16];
        amountOccupiedSpaces = 0;
    }

    public OwnersFloor(int amountOccupiedSpaces) {
        spaces = new Space[amountOccupiedSpaces];
        this.amountOccupiedSpaces = 0;
    }

    public OwnersFloor(Space[] spaces) {
        this.spaces = spaces;
        for (int i = 0; i < spaces.length && spaces[i] != null; i++) {
            amountOccupiedSpaces = i + 1;
        }
    }

    @Override
    public boolean add(Space space) {
        if (checkInvalidIndex(amountOccupiedSpaces)) {
            increaseArraySize(spaces.length * 2);
        }
        spaces[amountOccupiedSpaces++] = space;
        return true;
    }

    @Override
    public boolean add(int index, Space rentedSpace) {
        if (checkInvalidIndex(index + 1)) {
            increaseArraySize(spaces.length * 2);
        }
        for (int i = amountOccupiedSpaces++; i >= index; i--) {
            if (i != index) {
                spaces[i] = spaces[i - 1];
            } else {
                spaces[i] = rentedSpace;
            }
        }
        return true;
    }

    @Override
    public Space getSpace(int index) {
        if (checkInvalidIndex(index)) {
            return spaces[index];
        } else return null;
    }

    @Override
    public Space getSpace(String registrationNumber) {
        for (Space currentRentedSpace : spaces) {
            if (currentRentedSpace != null && currentRentedSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return currentRentedSpace;
            }
        }
        return null;
    }

    @Override
    public int getIndexSpace(String registrationNumber) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] != null && spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Space setSpace(int index, Space rentedSpace) {
        if (checkInvalidIndex(index)) {
            Space oldRentedSpace = spaces[index];
            spaces[index] = rentedSpace;
            return oldRentedSpace;
        }
        return null;
    }

    @Override
    public Space removeSpace(int index) {
        if (checkInvalidIndex(index)) {
            return null;
        }
        Space removedSpace = spaces[index];
        spaces[index] = null;
        amountOccupiedSpaces--;
        Space[] newArray = new Space[amountOccupiedSpaces];
        for (int i = 0, j = 0; i < spaces.length; i++) {
            if (spaces[i] != null) {
                newArray[j++] = spaces[i];
            }
        }
        spaces = newArray;
        return removedSpace;
    }

    @Override
    public Space removeSpace(String registrationNumber) {
        int index = 0;
        for (Space currentRentedSpace : spaces) {
            if (currentRentedSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return removeSpace(index);
            }
            index++;
        }
        return null;
    }

    @Override
    public boolean isSpaceExist(String registrationNumber) {
        for (Space currentRentedSpace : spaces) {
            if (currentRentedSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOccupiedSpaces() {
        return amountOccupiedSpaces;
    }

    @Override
    public int totalNumberSpaces() {
        return spaces.length;
    }

    @Override
    public Space[] getSpaces(VehicleTypes type) {
        List<Space> spacesList = new ArrayList<>();
        for (Space space : spaces) {
            if (space.getVehicle().getType().equals(type)) {
                spacesList.add(space);
            }
        }
        return spacesList.toArray(new Space[0]);
    }

    @Override
    public Space[] getLiberalSpace() {
        Space[] liberalSpaces = new Space[spaces.length - amountOccupiedSpaces];
        System.arraycopy(spaces, amountOccupiedSpaces, liberalSpaces, 0, liberalSpaces.length);
        return liberalSpaces;
    }

    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[amountOccupiedSpaces];
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            vehicles[i] = spaces[i].getVehicle();
        }
        return vehicles;
    }

    private boolean checkInvalidIndex(int index) {
        return index < 0 || index >= spaces.length;
    }

    private void increaseArraySize(int newLength) {
        Space[] newArray = new Space[newLength];
        System.arraycopy(spaces, 0, newArray, 0, amountOccupiedSpaces);
        spaces = newArray;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OwnersFloor{");
        sb.append("rentedSpaces=").append(Arrays.toString(spaces));
        sb.append(", amountOccupiedSpaces=").append(amountOccupiedSpaces);
        sb.append('}');
        return sb.toString();
    }
}
