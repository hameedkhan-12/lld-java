class Car {
    protected int speed;

    public Car(){
        speed = 0;
    }

    public void accelerate(){
        System.out.println("Accelerating...");
        speed += 20;
    }

    public void brake(){
        System.out.println("Braking...");
        speed -= 20;
    }
}

class HybridCar extends Car {
   private int charge;

   public HybridCar(){
    super();
    charge = 0;
   }


   @Override
   public void brake(){
    System.out.println("Braking...");
    speed -= 20;
    charge += 10;

    System.out.println("Charge: " + charge + " and speed: " + speed);
   }
}

public class PostConditions {
    public static void main(String[] args){
        Car hybridCar = new HybridCar();
        hybridCar.brake();
    }
}