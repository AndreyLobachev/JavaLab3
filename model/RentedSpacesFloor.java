package model;

import java.util.ArrayList;
import java.util.List;

public class RentedSpacesFloor implements Floor{
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
    public boolean add(Space rentedSpace) {
        return add(amountOccupiedSpaces, rentedSpace);
    }

    @Override
    public boolean add(int index, Space space) {
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
        return getNodeByIndex(index + 1).value;
    }

    @Override
    public Space getSpace(String registrationNumber) {
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int getIndexSpace(String registrationNumber) {
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
        Node node = getNodeByIndex(index + 1);
        Space oldSpace = node.value;
        node.value = space;
        return oldSpace;
    }

    @Override
    public Space removeSpace(int index) {
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
    public Space[] getSpaces(VehicleTypes type) {
        List<Space> spacesList = new ArrayList<>();
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            if (node.value.getVehicle().getType().equals(type)) {
                spacesList.add(node.value);
            }
        }
        return spacesList.toArray(new Space[0]);
    }

    @Override
    public Space[] getLiberalSpace() {
        return null;
    }

    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[amountOccupiedSpaces];
        Node node = head.next;
        for (int i = 0; i < amountOccupiedSpaces; i++, node = node.next) {
            vehicles[i] = node.value.getVehicle();
        }
        return vehicles;
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
}
