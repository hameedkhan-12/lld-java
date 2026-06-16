public interface TrafficLightState {
    void changeState(TrafficLight trafficLight);
}

public class RedState implements TrafficLightState {
    @Override
    public void changeState(TrafficLight trafficLight) {
        trafficLight.setState(new GreenState());
    }
}

public class GreenState implements TrafficLightState(){
    @Override
    public void changeState(TrafficLight trafficLight) {
        trafficLight.setState(new YellowState());
    }
}

public class YellowState implements TrafficLightState {
    @Override
    public void changeState(TrafficLight trafficLight) {
        trafficLight.setState(new RedState());
    }
}

public class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        this.state = new RedState();
    }
    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void changeState() {
        state.changeState(this);
    }
}

public class Main {
    public static void main(String[] args){
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.changeState();

        light.changeState();
        light.changeState();
        light.changeState();
        light.changeState();
        light.changeState();
        light.changeState();
        light.changeState();
    }
}