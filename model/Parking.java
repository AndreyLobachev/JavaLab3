package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


public class Parking {

    private Floor[] ownersFloors;
    private int amountOccupiedFloors;

    public Parking(int amountOwnerFloors) {
        ownersFloors = new Floor[amountOwnerFloors];
    }

    public Parking(Floor[] ownersFloors) {
        this.ownersFloors = ownersFloors;
        for (int i = 0; i < ownersFloors.length && ownersFloors[i] != null; i++) {
            amountOccupiedFloors = i + 1;
        }
    }

    public boolean addLast(Floor ownersFloor) {
        if (checkInvalidIndex(amountOccupiedFloors)) {
            increaseArraySize(ownersFloors.length * 2);
        }
        ownersFloors[amountOccupiedFloors++] = ownersFloor;
        return true;
    }

    public boolean add(int index, Floor ownersFloor) {
        if (checkInvalidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = amountOccupiedFloors++; i >= index; i--) {
            if (i != index) {
                ownersFloors[i] = ownersFloors[i - 1];
            } else {
                ownersFloors[i] = ownersFloor;
            }
        }
        return true;
    }

    public Floor getOwnerFloor(int index) {
        if (checkInvalidIndex(index)) {
            throw new IndexOutOfBoundsException();
        } else return ownersFloors[index];
    }

    public Floor setOwnerFloor(int index, Floor ownersFloor) {
        if (checkInvalidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        Floor oldOwnerFloor = ownersFloors[index];
        ownersFloors[index] = ownersFloor;
        return oldOwnerFloor;
    }

    public Floor removeOwnerFloor(int index) {
        if (checkInvalidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        Floor removedOwnerFloor = ownersFloors[index];
        ownersFloors[index] = null;
        amountOccupiedFloors--;
        Floor[] newArray = new Floor[amountOccupiedFloors];
        for (int i = 0, j = 0; i < ownersFloors.length; i++) {
            if (ownersFloors[i] != null) {
                newArray[j++] = ownersFloors[i];
            }
        }
        ownersFloors = newArray;
        return removedOwnerFloor;
    }

    public int totalAmountOwnersFloor() {
        return ownersFloors.length;
    }

    public Floor[] getOwnerFloors() {
        Floor[] newArray = new Floor[amountOccupiedFloors];
        System.arraycopy(ownersFloors, 0, newArray, 0, amountOccupiedFloors);
        return newArray;
    }

    public Floor[] getOwnersFloors(Person person) {
        if(person == null)throw new NullPointerException();
        List<Floor> floorList = new ArrayList<>();
        for (Floor ownersFloor : ownersFloors) {
            for (int i = 0; i < ownersFloor.numberOccupiedSpaces(); i++) {
                if (ownersFloor.getSpace(i).getPerson().equals(person)) {
                    floorList.add(ownersFloor);
                    break;
                }
            }
        }
        return floorList.toArray(new Floor[0]);
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getTotalAmountVehicles()];
        int counter = 0;
        for (Floor ownersFloor : ownersFloors) {
            System.arraycopy(ownersFloor.getVehicles(), 0, vehicles, counter, ownersFloor.numberOccupiedSpaces());
            counter += ownersFloor.getVehicles().length;
        }
        return vehicles;
    }

    public Space getSpace(String registrationNumber) {
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        for (Floor ownersFloor : ownersFloors) {
            Space rentedSpace = ownersFloor.getSpace(registrationNumber);
            if (rentedSpace != null) {
                return rentedSpace;
            }
        }
        throw new NoSuchElementException();
    }

    public Space removeSpace(String registrationNumber) {
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        for (Floor ownersFloor : ownersFloors) {
            Space rentedSpace = ownersFloor.removeSpace(registrationNumber);
            if (rentedSpace != null) {
                return rentedSpace;
            }
        }
        throw new NoSuchElementException();
    }

    public Space setSpace(String registrationNumber, Space rentedSpace) {
        if(registrationNumber == null)throw new NullPointerException();
        if(rentedSpace == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        for (Floor ownersFloor : ownersFloors) {
            int indexSpace = ownersFloor.getIndexSpace(registrationNumber);
            if (indexSpace != -1) {
                return ownersFloor.setSpace(indexSpace, rentedSpace);
            }
        }
        throw new NoSuchElementException();
    }

    public int getAmountLiberalFloors() {
        return ownersFloors.length - amountOccupiedFloors;
    }

    public int getVehiclesQuantity(VehicleTypes type) {
        int quantity = 0;
        Vehicle[] vehicles = getVehicles();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().equals(type)) {
                quantity++;
            }
        }
        return quantity;
    }

    private int getTotalAmountVehicles() {
        int amount = 0;
        for (Floor ownersFloor : ownersFloors) {
            amount += ownersFloor.getVehicles().length;
        }
        return amount;
    }

    private boolean checkInvalidIndex(int index) {
        return index < 0 || index >= ownersFloors.length;
    }
    private void checkValidRegistrationNumber(String regNumber) {
        if (!regNumber.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}")) {
            throw new RegistrationNumberFormatException();
        }
    }

    private void increaseArraySize(int newLength) {
        Floor[] newArray = new Floor[newLength];
        System.arraycopy(ownersFloors, 0, newArray, 0, amountOccupiedFloors);
        ownersFloors = newArray;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Floors ");
        sb.append("(<size>").append(ownersFloors.length).append("):\n");
        for (Floor ownersFloor : ownersFloors) {
            sb.append(ownersFloor);
        }
        return sb.toString();
    }

}
