class SportsCar {
    private String brand;
    private String model;
    private boolean isEngineOn = false;
    private int currentSpeed = 0;
    private int currentGear = 0;
    private String tyreCompany;

    public SportsCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public int getSpeed(){
        return currentSpeed;
    }

    public String getTyreCompany(){
        return tyreCompany;
    }

    public void setTyreCompany(String tyreCompany){
        this.tyreCompany = tyreCompany;
    }

    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + "Engine started");
    }

    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
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

    public void stopEngine(){
        isEngineOn = false;
        currentGear = 0;
         currentSpeed = 0;
        System.out.println(brand + " " + model + "Engine stopped");
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        SportsCar myCar = new SportsCar("Honda", "Civic");
        myCar.startEngine();
        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();
        myCar.shiftGear(1);
        myCar.brake();
        myCar.stopEngine();
       System.out.println("Current Speed of My Sports Car is " + myCar.getSpeed());
    }

}