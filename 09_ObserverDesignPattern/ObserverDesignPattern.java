import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    void update();
}

interface IChannel {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers();
}

class Channel implements IChannel {
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name){
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        if(!subscribers.contains(subscriber)){
            this.subscribers.add(subscriber);
        }
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(){
        for(ISubscriber subscriber : subscribers){
            subscriber.update();
        }
    }

    public void uploadVideo(String title){
        this.latestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    public String getLatestVideo(){
        return "\nCheckout our new Video : " + latestVideo + "\n";
    }
}

class Subscriber implements ISubscriber {
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel){
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update(){
        System.out.println("Hey " + name + ", " + channel.getLatestVideo());
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel channel = new Channel("CodeWithHameed");
        Subscriber sub1 = new Subscriber("Hameed", channel);
        Subscriber sub2 = new Subscriber("John", channel);
        channel.subscribe(sub1);
        channel.subscribe(sub2);
        channel.uploadVideo("Design Patterns");

        channel.uploadVideo("Observer Design Pattern");

        channel.unsubscribe(sub1);

        channel.uploadVideo("Strategy Design Pattern");
    }
}