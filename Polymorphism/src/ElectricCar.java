public class ElectricCar extends Car {
    private double batteryLevel;
    private double efficiency;

    public ElectricCar(String brand, int speed, double fuel, double mileage, double batteryLevel, double efficiency) {
        super(brand, speed, fuel, mileage);
        this.batteryLevel = batteryLevel;
        this.efficiency = efficiency;
    }

    public void charge(double amount) {
        this.batteryLevel += amount * getEfficiency();
    }

    public void driveElectric(int distance) {
        double energyNeeded = distance * getEfficiency();
        if (getBatteryLevel() >= energyNeeded) {
            super.drive(distance);
            this.batteryLevel -= energyNeeded;
        } else {
            System.out.println("Not enough fuel");
        }
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public double getEfficiency() {
        return efficiency;
    }
}
