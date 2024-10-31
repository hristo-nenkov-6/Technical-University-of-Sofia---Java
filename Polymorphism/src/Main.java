public class Main {
    public static void main(String[] args) {
        Car[] cars = new Car[2];
        cars[0] = new Car("VW", 50, 49.5, 44.5);
        cars[1] = new ElectricCar("Tesla", 50, 49.5, 44.5, 0.95, 0.95);

        for (Car car : cars) {
            if(car instanceof ElectricCar) {
                    ((ElectricCar) car).driveElectric(50);
            }else{
                car.drive(77);
            }
        }
    }
}