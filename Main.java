import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Test();
    }

    public static void Test() {
        try {
            Person andrey = new Person("Andrey", "Andrey");
            Person andrew = new Person("Andrew", "Andrew");
            Person andriano = new Person("Andr", "Andr");
            Person ympa = new Person("ympa", "ympa");
            Person lympa = new Person("lympa", "lympa");
            Vehicle gran = new Vehicle("A123BB111", "Lada", "Granta", VehicleTypes.CAR);
            Vehicle mas = new Vehicle("Y342OA32", "cx 5", "cx 5", VehicleTypes.CAR);
            Vehicle volk = new Vehicle("K624CT333", "Volkswagen", "Polo", VehicleTypes.CAR);
            Vehicle ren = new Vehicle("X999XX12", "Renault", "Sandero", VehicleTypes.CAR);
            Vehicle kia = new Vehicle("E452TT19", "KIA", "Rio", VehicleTypes.CAR);
            OwnersFloor ownersFloor = new OwnersFloor();
            OwnersFloor ownersFloor1 = new OwnersFloor(20);
            RentedSpace space1 = new RentedSpace(andrey, kia, LocalDate.parse("2015-02-20"), LocalDate.parse("2016-02-20"));
            RentedSpace space2 = new RentedSpace(andriano, volk, LocalDate.of(2012, 02, 20), LocalDate.of(2013, 02, 20));
            RentedSpace space3 = new RentedSpace(andrew, mas, LocalDate.parse("2018-02-20"), LocalDate.parse("2019-02-20"));
            RentedSpace space4 = new RentedSpace(lympa, gran, LocalDate.parse("2019-02-20"), LocalDate.parse("2020-02-20"));
            RentedSpace space5 = new RentedSpace(ympa, ren, LocalDate.parse("2020-02-20"), LocalDate.parse("2021-02-20"));
            RentedSpace space6 = new RentedSpace(andrey, ren, LocalDate.parse("2018-04-14"), LocalDate.parse("2019-04-14"));
            RentedSpace space0 = new RentedSpace();
            OwnersFloor[] ownersFloors = new OwnersFloor[]{ownersFloor, ownersFloor1};
            Parking parking = new Parking(ownersFloors);
            parking.addLast(new RentedSpacesFloor());
            parking.addLast(new RentedSpacesFloor());
            parking.addLast(new RentedSpacesFloor());
            parking.getOwnerFloor(0).add(space1);
            parking.getOwnerFloor(0).add(space4);
            parking.getOwnerFloor(0).add(space6);
            parking.getOwnerFloor(0).add(space5);
            parking.getOwnerFloor(1).add(space3);
            parking.getOwnerFloor(1).add(space0);
            parking.getOwnerFloor(1).add(space0);
            parking.getOwnerFloor(2).add(space4);
            parking.getOwnerFloor(2).add(space2);
            parking.getOwnerFloor(2).add(space2);
            parking.getOwnerFloor(2).add(space6);
            List<Floor> floors = parking.getOwnerFloors();
            Set<Floor> floorSet = parking.getOwnersFloors(andrew);
            for (Floor floor : floors) {
                print(floor);
            }
            for (Floor floor : floorSet) {
                print(floor);
            }
        }
        catch (RegistrationNumberFormatException e){
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
    public static void print(Object x) {
        System.out.println("\n" + x);
    }
}
