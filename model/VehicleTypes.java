package model;

public enum VehicleTypes {
    NONE(""),
    CAR("Машина"),
    CROSSOVER("Кроссовер"),
    MOTOR_BIKE("Мотор-байк"),
    SUV("Сав"),
    TRUCK("Трек"),
    OTHER("Другой");

    VehicleTypes(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
