package model;

public class OwnedSpace extends AbstractSpace{
    public OwnedSpace() {
        super();
    }

    public OwnedSpace(Person person) {
        super(person);
    }

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OwnedSpace{");
        sb.append("person=").append(person);
        sb.append(", vehicle=").append(vehicle);
        sb.append('}');
        return sb.toString();
    }
}
