interface Command {
    void execute();
    void undo();
}

class Light {
   public void on() {
        System.out.println("Light is on");
    }
   public void off() {
        System.out.println("Light is off");
    }
}

class LightCommand implements Command {
    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }


    @Override
    public void undo() {
        light.off();
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is on");
    }
    public void off() {
        System.out.println("Fan is off");
    }
}

class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}

class RemoteController {
    private static final int numButtons = 4;
    private Command[] buttons;
    private boolean[] buttonPressed;

    public RemoteController() {
        buttons = new Command[numButtons];
        buttonPressed = new boolean[numButtons];

        for(int i = 0; i<numButtons; i++) {
            buttons[i] = null;
            buttonPressed[i] = false;
        }
    }

    public void setCommand(int idx, Command cmd){
        if(idx >= 0 && idx < numButtons) {
            buttons[idx] = cmd;
            buttonPressed[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if(idx >= 0 && idx < numButtons && buttons[idx] != null) {
            if(!buttonPressed[idx]){
                buttons[idx].execute();
            }else {
                buttons[idx].undo();
            }
            buttonPressed[idx] = !buttonPressed[idx];
        }else{
            System.out.println("Invalid button index");
        }
    }
}

public class CommandPattern {
    public static void main(String[] args){
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        RemoteController remote = new RemoteController();

        remote.setCommand(0, new LightCommand(livingRoomLight));
        remote.setCommand(1, new FanCommand(ceilingFan));

        System.out.println("--- Living Room Light ---");
        remote.pressButton(0);
        remote.pressButton(0);

        System.out.println("--- Ceiling Fan ---");
        
        remote.pressButton(1);
        remote.pressButton(1);
    }
}