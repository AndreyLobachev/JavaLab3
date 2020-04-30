import model.*;

public class Main {
    public static void main(String[] args) {
        Test();
    }

    public static void Test(){
        Person andrey = new Person("Андрей","Лобачев");
        Person andreyversiontwo = new Person("Andrey","Lobachev");
        Person andrew = new Person("Andrew","Luis");
        Person andriano = new Person("Andriano","Hehe");
        Person ympalympa = new Person("Ympa","Lumpa");
        Vehicle gran = new Vehicle("1234","Lada", "Granta",VehicleTypes.CAR);
        Vehicle mas = new Vehicle("0000", "Mazda", "cx 5",VehicleTypes.CAR);
        Vehicle volk = new Vehicle("1928", "Volkswagen", "Polo",VehicleTypes.CAR);
        Vehicle ren = new Vehicle("5557", "Renault", "Sandero",VehicleTypes.CAR);
        Vehicle kia = new Vehicle("2548", "KIA", "Rio",VehicleTypes.CAR);
        Space space1 = new RentedSpace(andrey,kia);
        Space space2 = new RentedSpace(andrew,volk);
        Space space3 = new RentedSpace(andreyversiontwo,mas);
        Space space4 = new RentedSpace(ympalympa,gran);
        Space space5 = new RentedSpace(andriano,ren);
        Space space6 = new RentedSpace(andrey,ren);
        Space space0 = new RentedSpace();
        Parking parking = new Parking(3);
        parking.addLast(new RentedSpacesFloor());
        parking.addLast(new RentedSpacesFloor());
        parking.addLast(new RentedSpacesFloor());
        parking.getOwnerFloor(0).add(space1);
        parking.getOwnerFloor(0).add(space4);
        parking.getOwnerFloor(0).add(0,space5);
        parking.getOwnerFloor(1).add(space3);
        parking.getOwnerFloor(1).add(space0);
        parking.getOwnerFloor(1).add(space0);
        parking.getOwnerFloor(2).add(space2);
        parking.getOwnerFloor(2).add(space4);
        System.out.println(parking.getOwnerFloor(0).getIndexSpace("1234"));
        System.out.println(parking.getOwnerFloor(1).removeSpace(space3));
        System.out.println(parking.getOwnerFloor(2).getIndexSpace(space2));
        for (int i = 0; i < parking.getOwnersFloors(andrey).length; i++) {
            System.out.println(parking.getOwnersFloors(andrey)[i]);
        }

        System.out.println(andrey.hashCode());
    }
}
