package model;

import java.util.*;
import java.time.LocalDate;


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
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        for (Space currentRentedSpace : spaces) {
            if (currentRentedSpace != null && currentRentedSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return currentRentedSpace;
            }
        }
        return null;
    }

    @Override
    public int getIndexSpace(String registrationNumber) {
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] != null && spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Space setSpace(int index, Space rentedSpace) {
        if(rentedSpace == null)throw new NullPointerException();
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
            throw new IndexOutOfBoundsException();
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
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
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
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
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

    @Override
    public boolean removeSpace(Space space) {
        if(space == null)throw new NullPointerException();
        int index = getIndexSpace(space);
        if (index != -1) {
            removeSpace(index);
            return true;
        }
        return false;
    }

    @Override
    public int getIndexSpace(Space space) {
        return getIndexSpace(space.getVehicle().getRegistrationNumber());
    }

    @Override
    public int getValueSpaces(Person person) {
        int count = 0;
        for (Space space : spaces) {
            if (space.getPerson().equals(person)) {
                count++;
            }
        }
        return count;
    }
    @Override
    public LocalDate getEarlyFinishDate() throws NoRentedSpaceException {
        LocalDate earlyFinishDate = null;
        for (Space space : spaces) {
            if (space.getClass().equals(RentedSpace.class))
                if (Objects.isNull(earlyFinishDate)) {
                    earlyFinishDate = ((RentedSpace) space).getFinishDate();
                } else if (((RentedSpace) space).getFinishDate().isBefore(earlyFinishDate)) {
                    earlyFinishDate = ((RentedSpace) space).getFinishDate();
                }
        }
        if (Objects.isNull(earlyFinishDate)) {
            throw new NoRentedSpaceException();
        }
        return earlyFinishDate;
    }

    @Override
    public Space getSpaceWithEarlyFinishDate() throws NoRentedSpaceException {
        RentedSpace rentedSpace = null;
        for (Space space : spaces) {
            if (space.getClass().equals(RentedSpace.class))
                if (Objects.isNull(rentedSpace)) {
                    rentedSpace = (RentedSpace) space;
                } else if (((RentedSpace) space).getFinishDate().isBefore(rentedSpace.getFinishDate())) {
                    rentedSpace = (RentedSpace) space;
                }
        }
        if (Objects.isNull(rentedSpace)) {
            throw new NoRentedSpaceException();
        }
        return rentedSpace;
    }

    private boolean checkInvalidIndex(int index) {
        return index < 0 || index >= spaces.length;
    }
    private void checkValidRegistrationNumber(String regNumber) {
        if (!regNumber.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}")) {
            throw new RegistrationNumberFormatException();
        }
    }

    private void increaseArraySize(int newLength) {
        Space[] newArray = new Space[newLength];
        System.arraycopy(spaces, 0, newArray, 0, amountOccupiedSpaces);
        spaces = newArray;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Spaces:\n");
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            sb.append(spaces[i]).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnersFloor)) return false;
        OwnersFloor that = (OwnersFloor) o;
        if (amountOccupiedSpaces == that.amountOccupiedSpaces) {
            return false;
        }
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            if (!Objects.equals(spaces[i], that.spaces[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        for (int i = 0; i < amountOccupiedSpaces; i++) {
            result = 71 * result + (spaces[i] == null ? 0 : spaces[i].hashCode());
        }

        return result;
    }
}
