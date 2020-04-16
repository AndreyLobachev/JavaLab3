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
        final StringBuilder sb = new StringBuilder("RentedSpace{");
        sb.append("person=").append(super.person);
        sb.append(", vehicle=").append(super.vehicle);
        sb.append('}');
        return sb.toString();
    }
}
