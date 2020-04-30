package model;

public class RentedSpace extends AbstractSpace{
    public RentedSpace() {
        super();
    }

    public RentedSpace(Person person) {
        super(person);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
