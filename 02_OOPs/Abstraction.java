//interface is just a blueprint
interface Car {
    void startEngine();
    void shiftGear(int gear);
    void accelerate();
    void brake();
    void stopEngine();
}

class SportsCar implements Car {
    String brand;
    String model;
    boolean isEngineOn = false;
    int currentSpeed = 0;
    int currentGear = 0;

    public SportsCar(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    @Override
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + "Engine started");
    }

      @Override
    public void shiftGear(int gear) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Engine is off! Cannot Cannot Shift Gear.");
            return;
        }
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    @Override
    public void accelerate(){
        if(!isEngineOn){
            System.out.println("Engine is off");
            return;
        }

        currentSpeed += 10;
        System.out.println("Speed increased to " + currentSpeed);
    }

    @Override
    public void brake(){
        if(!isEngineOn){
            System.out.println("Engine is off");
            return;
        }

        currentSpeed -= 10;
        System.out.println("Speed decreased to " + this.currentSpeed);
    }

    @Override
    public void stopEngine(){
        isEngineOn = false;
        System.out.println(brand + " " + model + "Engine stopped");
    }   

}

public class Abstraction {
    public static void main(String[] args){
        Car myCar = new SportsCar("Honda", "Civic");
        myCar.startEngine();
        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();
        myCar.shiftGear(1);
        myCar.brake();
        myCar.stopEngine();
    }
}