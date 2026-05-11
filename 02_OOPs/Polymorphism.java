abstract class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int curSpeed;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.curSpeed = 0;
    }

    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + "Engine started");
    }

    public void stopEngine() {
        isEngineOn = false;
        curSpeed = 0;
        System.out.println(brand + " " + model + "Engine stopped");
    }

    //since these classes are abstract and will behave differently 
    public abstract void accelerate();
    public abstract void brake();
}

class ManualCar extends Car {
    public int currentGear;

    public ManualCar(String brand, String model) {
        super(brand, model);
        this.currentGear = 0;
    }

    public void shiftGear(int gear){
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    @Override
    public void brake() {
        curSpeed -= 10;
        if(curSpeed < 0) curSpeed = 0;
        System.out.println(brand + " " + model + " : Brake applied at " + curSpeed + " km/h");
    }

    @Override
    public void accelerate() {
        curSpeed += 10;
        System.out.println(brand + " " + model + " : Accelerated to " + curSpeed + " km/h");
    }
}

class ElectronicCar extends Car {
    public int batteryLevel;

    public ElectricCar(String brand, String model) {
        super(brand, model);
        this.batteryLevel = 100;
    }

    public void chargeBattery(){
        batteryLevel = 100;
        System.out.println("Battery charged to 100%");
    }

    @Override
    public void accelerate(){
        if(!isEngineOn){
            System.out.println("Engine is off");
            return;
        }
        if(batteryLevel<=0){
            System.out.println("Battery is empty, cannot accelerate");
            return;
        }

        curSpeed += 10;
        batteryLevel -= 10;
        System.out.println(brand + " " + model + " : Accelerated to " + curSpeed + " km/h");
    }

    @Override
    public void brake(){
        curSpeed -= 10;
        if(curSpeed < 0) curSpeed = 0;
        System.out.println(brand + " " + model + " : Brake applied at " + curSpeed + " km/h");
    }
}

public class DynamicPolymorphism {
    public static void main(String[] args){
        Car myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();
        myManualCar.accelerate();
        myManualCar.accelerate();
        myManualCar.accelerate();
        myManualCar.accelerate();
        myManualCar.shiftGear(1);
        myManualCar.brake();
        myManualCar.stopEngine();

        System.out.println("------------");

        Car myElectricCar = new ElectronicCar("Tesla", "Model 3");
        myElectricCar.startEngine();
        myElectricCar.accelerate();
        myElectricCar.accelerate();
        myElectricCar.accelerate();
        myElectricCar.accelerate();
        myElectricCar.chargeBattery();
        myElectricCar.brake();
        myElectricCar.stopEngine();
    }
}

//Static Polymorphism
/*
Static Polymorphism (Compile-time polymorphism) in real life says that
the same action can behave differently depending on the input parameters.
For example, a Manual car can accelerate by a fixed amount or by a
specific amount you request. In programming, we achieve this via method
overloading: multiple methods with the same name but different signatures.
*/

class ManualCar {
    private String brand;
    private String model;
    private boolean isEngineOn;
    private int currentSpeed;
    private int currentGear;

    public ManualCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
        this.currentGear = 0;
    }

    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    // Overloading accelerate - Static Polymorphism
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) {
            currentSpeed = 0;
        }
        System.out.println(brand + " " + model + " : Braking! Speed is now "
                           + currentSpeed + " km/h");
    }

    public void shiftGear(int gear) {
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
}

public class StaticPolymorphism {
    public static void main(String[] args) {
        ManualCar myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();
        myManualCar.accelerate();      
        myManualCar.accelerate(40);    
        myManualCar.brake();
        myManualCar.stopEngine();
    }
}

//Static and Dynamic Polymorphism

// Base Car class
abstract class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }

    //Common methods for All cars.
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    public abstract void accelerate();             // Abstract method for Dynamic Polymorphism

    public abstract void accelerate(int speed);    //Abstract method for Static Polymorphism
    
    public abstract void brake();                  // Abstract method for Dynamic Polymorphism
}

class ManualCar extends Car {
    private int currentGear;

    public ManualCar(String brand, String model) {
        super(brand, model);
        this.currentGear = 0;
    }

    //Specialized method for Manual Car
    public void shiftGear(int gear) {
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    // Overriding accelerate - Dynamic Polymorphism
    @Override
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    //overriding and overloading accelerate at the same time.
    @Override
    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Overriding brake - Dynamic Polymorphism
    @Override
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }
}

class ElectricCar extends Car {
    private int batteryLevel;

    public ElectricCar(String brand, String model) {
        super(brand, model);
        this.batteryLevel = 100;
    }

    //specialized method for Electric Car
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println(brand + " " + model + " : Battery fully charged!");
    }

    // Overriding accelerate - Dynamic Polymorphism
    @Override
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        if (batteryLevel <= 0) {
            System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
            return;
        }
        batteryLevel -= 10;
        currentSpeed += 15;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }

    // Overriding accelerate - Dynamic Polymorphism
    @Override
    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        if (batteryLevel <= 0) {
            System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
            return;
        }
        batteryLevel -= 10 + speed;
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }

    // Overriding brake - Dynamic Polymorphism
    @Override
    public void brake() {
        currentSpeed -= 15;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model +
                           " : Regenerative braking! Speed is now " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }
}

// Main function
public class StaticAndDynamicPolymorphism {
    public static void main(String[] args) {
        Car myManualCar = new ManualCar("Ford", "Mustang");
        myManualCar.startEngine();
        myManualCar.accelerate();
        myManualCar.accelerate();
        myManualCar.brake();
        myManualCar.stopEngine();

        System.out.println("----------------------");

        Car myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.startEngine();
        myElectricCar.accelerate();
        myElectricCar.accelerate();
        myElectricCar.brake();
        myElectricCar.stopEngine();
    }
}