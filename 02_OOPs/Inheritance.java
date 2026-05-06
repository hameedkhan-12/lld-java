class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }

    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + "Engine started");
    }

    public void stopEngine(){
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + "Engine stopped");
    }

    public void accelerate(){
        if(!isEngineOn){
            System.out.println("Engine is off");
            return;
        }

        currentSpeed += 10;
        System.out.println("Speed increased to " + currentSpeed);
    }

    public void brake(){
        currentSpeed -= 10;
        if(currentSpeed < 0){
            currentSpeed = 0;
        }
        System.out.println("Speed decreased to " + this.currentSpeed);
    }

}

class ManualCar extends Car {
    private int currentGear;
    public ManualCar(String brand, String model){
        super(brand, model);
        this.currentGear = 0;
    }

    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
}

class ElectricCar extends Car{
    private int batteryLevel;

    public ElectricCar(String brand, String model){
        super(brand, model);
        this.batteryLevel = 100;
    }

    public void chargeBattery(){
        batteryLevel = 100;
        System.out.println("Battery charged to 100%");
    }
}

public class Inheritance{
    public static void main(String[] args) {
        ManualCar manualCar = new ManualCar("Honda", "Civic");
        manualCar.startEngine();
        manualCar.accelerate();
        manualCar.shiftGear(1);
        manualCar.brake();
        manualCar.stopEngine();

        System.out.println("------------");

        ElectricCar electricCar = new ElectricCar("Tesla", "Model 3");
        electricCar.startEngine();
        electricCar.accelerate();
        electricCar.chargeBattery();
        electricCar.brake();
        electricCar.stopEngine();
    }
}