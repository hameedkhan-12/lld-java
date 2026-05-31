import java.util.*;

interface INotification {
    String getContent();
}

class SimpleNotification implements INotification {
    private String text;

    public SimpleNotification(String text) {
        this.text = text;
    }

    public String getContent() {
        return this.text;
    }
}

abstract class INotificationDecorator implements INotification {
    protected INotification notification;

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }
}

class TimestampDecorator extends INotificationDecorator {
    public TimestampDecorator(INotification notification) {
        super(notification);
    }

    public String getContent() {
        return "[" + new Date().toString() + "] " + this.notification.getContent();
    }
}

class SignatureDecorator extends INotificationDecorator {
    private String signature;

    public SignatureDecorator(INotification notification, String signature) {
        super(notification);
        this.signature = signature;
    }

    public String getContent() {
        return this.notification.getContent() + " - " + this.signature;
    }
}

interface IObserver {
    void update();
}

interface IObservable {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

class NotificationObservable implements IObservable {
    private List<IObserver> observers = new ArrayList<IObserver>();
    private INotification currNotification;

    public void addObserver(IObserver obs){
        observers.add(obs);
    }

    public void removeObserver(IObserver obs){
        observers.remove(obs);
    }

    public void notifyObservers(){
        for (IObserver obs: observers) {
            obs.update();
        }
    }

    public void setNotification(INotification notification){
        this.currNotification = notification;
        notifyObservers();
    }

    public INotification getNotification(){
        return this.currNotification;
    }

    public String getNotificationContent(){
        return this.currNotification.getContent();
    }
}

class Logger implements IObserver {
    private NotificationObservable notificationObservable;

    public Logger(NotificationObservable notificationObservable) {
        this.notificationObservable = notificationObservable;
    }

    public void update(){
        System.out.println(this.notificationObservable.getNotificationContent());
    }
}

interface INotificationStrategy {
    void sendNotification(String content);
}

class EmailStrategy implements INotificationStrategy {
    private String emailId;

    public EmailStrategy(String emailId){
        this.emailId = emailId;
    }

    public void sendNotification(String content){
        System.out.println("Sending email to " + this.emailId + ": " + content);
    }
}

class SmsStrategy implements INotificationStrategy {
    private String phoneNumber;

    public SmsStrategy(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void sendNotification(String content){
        System.out.println("Sending SMS to " + this.phoneNumber + ": " + content);
    }
}

class PopupStrategy implements INotificationStrategy {
    public void sendNotification(String content){
        System.out.println("Showing popup: " + content);
    }
}

class NotificationEngine implements IObserver {
    private NotificationObservable notificationObservable;
    private List<INotificationStrategy> strategies = new ArrayList<INotificationStrategy>();

    public NotificationEngine(NotificationObservable observable){
        this.notificationObservable = observable;
    }

    public void addNotificationStrategy(INotificationStrategy ns){
        this.strategies.add(ns);
    }

    public void update(){
        String notificationContent = this.notificationObservable.getNotificationContent();

        for(INotificationStrategy ns : this.strategies){
            ns.sendNotification(notificationContent);
        }
    }
}

class NotificationService {
    private NotificationObservable notificationObservable;
    private static NotificationService instance;
    private List<INotification> notifications = new ArrayList<>();

    private NotificationService(){
        notificationObservable = new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(instance == null){
            instance = new NotificationService();
        }
        return instance;
    }

    public NotificationObservable getObservable(){
        return this.notificationObservable;
    }

    public void sendNotification(INotification notification){
        notifications.add(notification);
        notificationObservable.setNotification(notification);
    }
}

public class NotificationSystem {
    public static void main(String[] args){
        NotificationService notificationService = NotificationService.getInstance();

        NotificationObservable notificationObservable = notificationService.getObservable();

        Logger logger = new Logger(notificationObservable);
        NotificationEngine notificationEngine = new NotificationEngine(notificationObservable);
        notificationEngine.addNotificationStrategy(new PopupStrategy());
        notificationEngine.addNotificationStrategy(new EmailStrategy("L0ZQ3@example.com"));
        notificationEngine.addNotificationStrategy(new SmsStrategy("1234567890"));

        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        INotification notification = new SimpleNotification("Your order has been placed");
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "John Doe");
        
        notificationService.sendNotification(notification);
    }
}