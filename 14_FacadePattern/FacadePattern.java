class PowerSupply {
    public void providePower(){
        System.out.println("Power is provided");
    }
}

class CoolingSystem {
    public void startFans(){
        System.out.println("Fans are started");
    }
}

class CPU {
    public void run(){
        System.out.println("CPU is running");
    }
}

class Memory {
    public void load(){
        System.out.println("Memory is loaded");
    }
}

class HardDrive {
    public void readData(){
        System.out.println("Data is read");
    }
}

class BIOS {
    public void boot(CPU cpu, Memory memory){
        System.out.println("BIOS: Booting CPU and Memory checks");
        cpu.run();
        memory.load();
    }
}

class OperatingSystem {
    public void load(HardDrive hardDrive){
        System.out.println("OS: Loading data from hard drive");
        hardDrive.readData();
    }
}

class ComputerFacade {
    private PowerSupply powerSupply = new PowerSupply();
    private CoolingSystem coolingSystem = new CoolingSystem();
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private HardDrive hardDrive = new HardDrive();
    private BIOS bios = new BIOS();
    private OperatingSystem operatingSystem = new OperatingSystem();

    public void start(){
        powerSupply.providePower();
        coolingSystem.startFans();
        bios.boot(cpu, memory);
        operatingSystem.load(hardDrive);
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}