interface Engine {
    void start();
}

class PetrolEngine implements Engine {
    @Override
    public void start(){
        System.out.println("Petrol Engine starting with Ignition");
    }
}

class ElectricEngine implements Engine {
    @Override
    public void start(){
        System.out.println("Electric Engine starting with Battery");
    }
}

class DieselEngine implements Engine {
    @Override
    public void start(){
        System.out.println("Diesel Engine starting with Ignition");
    }
}

abstract class Car {
    protected Engine engine;
    public Car(Engine eng) {
        this.engine = eng;
    }
    public abstract void drive();
}

class SedanCar extends Car {
    public PetrolCar(Engine eng) {
        super(eng);
    }
    public void drive() {
        engine.start();
        System.out.println("Driving Sedan Car");
    }
}

class SUV extends Car {
    public ElectricCar(Engine eng) {
        super(eng);
    }
    public void drive() {
        engine.start();
        System.out.println("Driving SUV");
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        Engine petrolEng = new PetrolEngine();
        SedanCar sedan = new SedanCar(petrolEng);
        sedan.drive();

        Engine dieselEng = new DieselEngine();
        SUV suv = new SUV(dieselEng);
        suv.drive();

        Engine electricEng = new ElectricEngine();
        SUV electricSUV = new SUV(electricEng);
        electricSUV.drive();
    }
}