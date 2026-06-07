import java.util.*;

class PaymentRequest {
    public String sender;
    public String reciever;
    public double amount;
    public String currency;

    public PaymentRequest(String sender, String reciever, double amt, String curr){
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amt;
        this.currency = curr;
    }
}

interface BankingSystem {
    boolean processPayment(double amount);
}

class PaytmBankingSystem implements BankingSystem {
    private Random rand = new Random();

    public PaytmBankingSystem(){};

    @Override
    public boolean processPayment(double amount){
        int r = rand.nextInt(100);
        return r<80;
    }
}

class RazorPayBankingSystem implements BankingSystem {
    private Random rand = new Random();

    public RazorPayBankingSystem(){};

    @Override
    public boolean processPayment(double amount){
        int r = rand.nextInt(100);
        return r<90;
    }
}

abstract class PaymentGateway {
    protected BankingSystem bankingSystem;

    public PaymentGateway(){
        this.bankingSystem = null;
    }

    public boolean processPayment(PaymentRequest request){
        if(!validatePayment(request)){
            System.out.println("Invalid payment request");
            return false;
        }
        if(!initiatePayment(request)){
            System.out.println("Unable to initiate payment");
            return false;
        }
        if(!confirmPayment(request)){
            System.out.println("Unable to confirm payment");
            return false;
        }
        return true;
    }

    protected abstract boolean validatePayment(PaymentRequest request);
    protected abstract boolean initiatePayment(PaymentRequest request);
    protected abstract boolean confirmPayment(PaymentRequest request);
}

class PaytmGateway extends PaymentGateway {
    public PaytmGateway(){
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request){
        System.out.println("Validating payment request with Paytm..." + request.sender + " -> " + request.reciever);

        if(request.amount <= 0 || !"Pkr".equals(request.currency)){
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request){
        System.out.println("Initiating payment with Paytm..." + request.sender + " -> " + request.reciever);
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request){
        System.out.println("Confirming payment with Paytm..." + request.sender + " -> " + request.reciever);
        return true;
    }
}

class RazorPayGateway extends PaymentGateway {
    public RazorPayGateway(){
        this.bankingSystem = new RazorPayBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request){
        System.out.println("Validating payment request with RazorPay..." + request.sender + " -> " + request.reciever);

        if(request.amount <= 0 || !"Pkr".equals(request.currency)){
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request){
        System.out.println("Initiating payment with RazorPay..." + request.sender + " -> " + request.reciever);
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request){
        System.out.println("Confirming payment with RazorPay..." + request.sender + " -> " + request.reciever);
        return true;
    }
}

class PaymentGatewayProxy extends PaymentGateway {
    private PaymentGateway realGateway;
    private int retries;

    public PaymentGatewayProxy(PaymentGateway gateway, int maxRetries){
        this.realGateway = gateway;
        this.retries = maxRetries;
    }

    @Override
    public boolean processPayment(PaymentRequest request){
        boolean result = false;
        for(int attempt = 0; attempt<retries; ++attempt){
            if(attempt > 0){
                System.out.println("Retrying payment..." + request.sender + " -> " + request.reciever);
            }
            result = realGateway.processPayment(request);
            if(result){
                break;
            }
        }
        if(!result){
            System.out.println("Payment failed!");
        }
        return result;
    }

    @Override 
    protected boolean validatePayment(PaymentRequest request){
        return realGateway.validatePayment(request);
    }

    @Override 
    protected boolean initiatePayment(PaymentRequest request){
        return realGateway.initiatePayment(request);
    }

    @Override 
    protected boolean confirmPayment(PaymentRequest request){
        return realGateway.confirmPayment(request);
    }
}

enum GatewayType {
    PAYTM, RAZORPAY
}

class GatewayFactory {
    private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory(){};

    public static GatewayFactory getInstance(){
        return instance;
    }

    public PaymentGateway getGateway(GatewayType type){
        if(type == GatewayType.PAYTM){
            PaymentGateway gateway = new PaytmGateway();
            return new PaymentGatewayProxy(gateway, 3);
        }
        else if(type == GatewayType.RAZORPAY){
            PaymentGateway gateway = new RazorPayGateway();
            return new PaymentGatewayProxy(gateway, 3);
        }
        return null;
    }
}

class PaymentService {
    private static final PaymentService instance = new PaymentService();
    private PaymentGateway gateway;

    private PaymentService(){
        this.gateway = null;
    }

    public static PaymentService getInstance(){
        return instance;
    }

    public void setGateway(PaymentGateway g){
        this.gateway = g;
    }

    public boolean processPayment(PaymentRequest request){
        if(gateway == null){
            System.out.println("No gateway set");
            return false;
        }
        return gateway.processPayment(request);
    }
}

class PaymentController {
    private static final PaymentController instance = new PaymentController();

    private PaymentController(){};

    public static PaymentController getInstance(){
        return instance;
    }
    
    public boolean handlePayment(GatewayType type, PaymentRequest request){
        PaymentGateway paymentGateway = GatewayFactory.getInstance().getGateway(type);
        PaymentService.getInstance().setGateway(paymentGateway);
        return PaymentService.getInstance().processPayment(request);
    }
}

public class PaymentGatewayApplication {
    public static void main(String[] args) {
        PaymentRequest request = new PaymentRequest("John Doe", "Jane Doe", 100, "Pkr");
        System.out.println("Processing via paytm");
        System.out.println("--------------------");

        boolean response = PaymentController.getInstance().handlePayment(GatewayType.PAYTM, request);
        System.out.println("Response: " + response);

        System.out.println("--------------------");
        System.out.println("Processing via razorpay");
        response = PaymentController.getInstance().handlePayment(GatewayType.RAZORPAY, request);
        System.out.println("Response: " + response);
    }
}