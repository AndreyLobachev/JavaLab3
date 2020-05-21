package model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;


public class RentedSpacesFloor implements Floor, Cloneable {
    private Node head;
    private int amountOccupiedSpaces;

    public RentedSpacesFloor() {
        initialHead();
    }

    public RentedSpacesFloor(Space[] spaces) {
        for (Space space : spaces) {
            addNode(space);
        }
    }

    @Override
    public boolean add(Space space) {
        return add(amountOccupiedSpaces, space);
    }

    @Override
    public boolean add(int index, Space space) {
        if(space == null)throw new NullPointerException();
        checkInvalidIndex(index);
        Node newNodes = new Node(space);
        Node currentNodes = getNodeByIndex(index);
        newNodes.next = currentNodes.next;
        newNodes.prev = currentNodes;
        currentNodes.next = newNodes;
        amountOccupiedSpaces++;
        return true;
    }

    @Override
    public Space getSpace(int index) {
        checkInvalidIndex(index);
        return getNodeByIndex(index + 1).value;
    }

    @Override
    public Space getSpace(String registrationNumber) {
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return node.value;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int getIndexSpace(String registrationNumber) {
        if(registrationNumber == null)throw new NullPointerException();
        checkValidRegistrationNumber(registrationNumber);
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Space setSpace(int index, Space space) {
        if(space == null)throw new NullPointerException();
        checkInvalidIndex(index);
        Node node = getNodeByIndex(index + 1);
        Space oldSpace = node.value;
        node.value = space;
        return oldSpace;
    }

    private void checkInvalidIndex(int index) {
        if (index < 0 || index > amountOccupiedSpaces) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Space removeSpace(int index) {
        checkInvalidIndex(index);
        Node node = getNodeByIndex(index);
        Space removedSpace = node.next.value;
        node.next = node.next.next;
        node.next.next.prev = node;
        amountOccupiedSpaces--;
        return removedSpace;
    }

    @Override
    public Space removeSpace(String registrationNumber) {
        return removeSpace(getIndexSpace(registrationNumber));
    }

    @Override
    public boolean isSpaceExist(String registrationNumber) {
        return getIndexSpace(registrationNumber) != -1;
    }

    @Override
    public int numberOccupiedSpaces() {
        return amountOccupiedSpaces;
    }

    @Override
    public int totalNumberSpaces() {
        return amountOccupiedSpaces;
    }

    @Override
    public List<Space> getSpaces(VehicleTypes type) {
        if(type == null)throw new NullPointerException();
        List<Space> spacesList = new ArrayList<>();
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getVehicle().getType().equals(type)) {
                spacesList.add(node.value);
            }
        }
        return spacesList;
    }

    @Override
    public Deque<Space> getLiberalSpace() {
        return null;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        Vehicle[] vehicles = new Vehicle[amountOccupiedSpaces];
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            vehicles[i] = node.value.getVehicle();
        }
        return Arrays.asList(vehicles);
    }

    private void addNode(Space space) {
        if (head == null) {
            initialHead();
        }
        Node node = head;
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            node = node.next;
        }
        node.next = new Node(space, node.next, node);
        amountOccupiedSpaces++;
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
        if(person == null)throw new NullPointerException();
        int count = 0;
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getPerson().equals(person)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public LocalDate getEarlyFinishDate() throws NoRentedSpaceException {
        LocalDate earlyFinishDate = null;
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getClass().equals(RentedSpace.class))
                if (earlyFinishDate == null) {
                    earlyFinishDate = ((RentedSpace) node.value).getFinishDate();
                } else if (((RentedSpace) node.value).getFinishDate().isBefore(earlyFinishDate)) {
                    earlyFinishDate = ((RentedSpace) node.value).getFinishDate();
                }
        }
        if (earlyFinishDate == null) {
            throw new NoRentedSpaceException();
        }
        return earlyFinishDate;
    }

    @Override
    public Space getSpaceWithEarlyFinishDate() throws NoRentedSpaceException {
        RentedSpace rentedSpace = null;
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getClass().equals(RentedSpace.class))
                if (rentedSpace == null) {
                    rentedSpace = (RentedSpace) node.value;
                } else if (((RentedSpace) node.value).getFinishDate().isBefore(rentedSpace.getFinishDate())) {
                    rentedSpace = (RentedSpace) node.value;
                }
        }
        if (rentedSpace == null) {
            throw new NoRentedSpaceException();
        }
        return rentedSpace;
    }

    private void initialHead() {
        head = new Node(null);
        head.next = head;
        head.prev = head;
    }

    private Node getNodeByIndex(int index) {
        Node node = head;
        for (; index != 0; index--) {
            node = node.next;
        }
        return node;
    }

    private void checkValidRegistrationNumber(String regNumber) {
        if (!regNumber.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}")) {
            throw new RegistrationNumberFormatException();
        }
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(amountOccupiedSpaces, o.numberOccupiedSpaces());
    }

    @Override
    public int size() {
        return amountOccupiedSpaces;
    }

    @Override
    public boolean isEmpty() {
        return amountOccupiedSpaces == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Space[] newInstance = (Space[]) Array.newInstance(head.next.value.getClass(), amountOccupiedSpaces);
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            newInstance[i] = getSpace(i);
        }
        return newInstance;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T[] toArray(T[] a) {
        T[] newInstance = (T[]) Array.newInstance(a.getClass().getComponentType(), amountOccupiedSpaces);
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            newInstance[i] = (T) getSpace(i);
        }
        return newInstance;
    }

    @Override
    public boolean remove(Object o) {
        return removeSpace((Space) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object x : c) {
            if (!contains(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Space> c) {
        for (Space object : c) {
            add(object);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean collectionsHasChanged = false;
        for (Object x : c) {
            int counter = amountOccupiedSpaces;
            for (int i = 0; i < counter; i++) {
                if (head.next.value.equals(x)) {
                    head.next = head.next.next;
                    amountOccupiedSpaces--;
                    collectionsHasChanged = true;
                } else {
                    head = head.next;
                }
            }
            head = head.next;
        }
        return collectionsHasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean collectionHasChanged = false;
        Node node = head;
        for (int i = 0; i < amountOccupiedSpaces; i++) {
            if (!c.contains(getSpace(i))) {
                remove(i--);
                collectionHasChanged = true;
            }
        }
        return collectionHasChanged;
    }

    @Override
    public void clear() {
        head.value = null;
        head.next = head;
        amountOccupiedSpaces = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rented Spaces:\n");
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            sb.append(node.value).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentedSpacesFloor)) return false;
        RentedSpacesFloor that = (RentedSpacesFloor) o;
        Node node = head.next;
        Node node1 = that.head.next;
        if (amountOccupiedSpaces != that.amountOccupiedSpaces) {
            return false;
        }
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next, node1 = node1.next) {
            if (!Objects.equals(node.value, node1.value))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 53 * amountOccupiedSpaces;

        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            result ^= (node.value == null ? 0 : node.value.hashCode());
        }

        return result;
    }

    @Override
    public Floor clone() throws CloneNotSupportedException {
        return (RentedSpacesFloor) super.clone();
    }

    private class Node {
        Node next;
        Node prev;
        Space value;

        Node(Space value) {
            this.value = value;
        }

        Node(Space value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator();
    }

    private class SpaceIterator implements Iterator<Space> {

        Node node = head.next;

        @Override
        public boolean hasNext() {
            return node.next.value != null;
        }

        @Override
        public Space next() {
            if (hasNext()) {
                node = node.next;
                return node.value;
            }
            throw new NoSuchElementException();
        }
    }
}
