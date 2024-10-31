public class Car {
    private String brand;
    private int speed;
    private double fuel;
    private double mileage;

    public Car(String brand, int speed, double fuel, double mileage) {
        setBrand(brand);
        setSpeed(speed);
        setFuel(fuel);
        setMileage(mileage);
    }

    public void accelerate(int increment) {
        speed += increment;
    }

    public void refuel(double amount) {
        fuel += amount;
    }

    public void drive(int distance) {
        double fuelNeeded = distance * 0.1; // Примерно 0.1 литра на километър
        if (fuel >= fuelNeeded) {
            mileage += distance;
            fuel -= fuelNeeded;
        } else {
            System.out.println("Not enough fuel");
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFuel() {
        return fuel;
    }

    public double getMileage() {
        return mileage;
    }
}
