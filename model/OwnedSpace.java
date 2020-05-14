package model;

import java.time.LocalDate;

public class OwnedSpace extends AbstractSpace{
    public OwnedSpace() {
        super();
    }

    public OwnedSpace(Person person, LocalDate startDate) {
        super(person, startDate);
    }

    public OwnedSpace(Person person, Vehicle vehicle, LocalDate startDate) {
        super(person, vehicle,startDate);
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
