interface WalkableRobot {
    void walk();
}

class NormalWalk implements WalkableRobot {
    public void walk(){
        System.out.println("Normal Walk");
    }
}

class NoWalk implements WalkableRobot {
    public void walk(){
        System.out.println("Can not Walk");
    }
}

interface TalkableRobot {
    void talk();
}

class NormalTalk implements TalkableRobot {
    public void talk(){
        System.out.println("Normal Talk");
    }
}

class NoTalk implements TalkableRobot {
    public void talk(){
        System.out.println("Can not Talk");
    }
}

interface FylableRobot {
    void fly();
}

class NormalFly implements FylableRobot {
    public void fly(){
        System.out.println("Normal Fly");
    }
}

class NoFly implements FylableRobot {
    public void fly(){
        System.out.println("Can not Fly");
    }
}

abstract class Robot {
    protected WalkableRobot walkableRobot;
    protected TalkableRobot talkableRobot;
    protected FylableRobot fylableRobot;
    public Robot(WalkableRobot w, TalkableRobot t, FylableRobot f){
        this.walkableRobot = w;
        this.talkableRobot = t;
        this.fylableRobot = f;
    }

    public void walk(){
        this.walkableRobot.walk();
    }

    public void talk(){
        this.talkableRobot.talk();
    }

    public void fly(){
        this.fylableRobot.fly();
    }

    public abstract void projection();
}

class CompanionRobot extends Robot {
    public CompanionRobot(WalkableRobot w, TalkableRobot t, FylableRobot f){
        super(w, t, f);
    }
    public void projection(){
        System.out.println("Projection");
    }
}

class ServiceRobot extends Robot {
    public ServiceRobot(WalkableRobot w, TalkableRobot t, FylableRobot f){
        super(w, t, f);
    }
    public void projection(){
        System.out.println("Projection");
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        Robot companionRobot = new CompanionRobot(new NormalWalk(), new NormalTalk(), new NormalFly());
        companionRobot.walk();
        companionRobot.talk();
        companionRobot.fly();
        companionRobot.projection();

        Robot serviceRobot = new ServiceRobot(new NoWalk(), new NoTalk(), new NoFly());
        serviceRobot.walk();
        serviceRobot.talk();
        serviceRobot.fly();
        serviceRobot.projection();
    }
}