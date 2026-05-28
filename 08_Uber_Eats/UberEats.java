import java.util.List;
import models.*;
import managers.*;
import strategies.*;
import factories.*;
import services.NotificationService;

public class UberEats {
    public UberEats(){
        initializeRestaurants();
    }

    public void initializeRestaurants(){
        Restaurant restaurant1 = new Restaurant("R1", "Taxila");
        restaurant1.addMenuItem(new MenuItem("P1", "Chole Bhature", 120));
        restaurant1.addMenuItem(new MenuItem("P2", "Samosa", 40));

        Restaurant restaurant2 = new Restaurant("R2", "Taxila");
        restaurant2.addMenuItem(new MenuItem("P1", "Chicken Biryani", 200));
        restaurant2.addMenuItem(new MenuItem("P2", "Chicken Kebab", 40));

        Restaurant restaurant3 = new Restaurant("R3", "Taxila");
        restaurant3.addMenuItem(new MenuItem("P1", "Chicken Karhayi", 1200));
        restaurant3.addMenuItem(new MenuItem("P2", "Samosa", 40));

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);
    }
    


    public List<Restaurant> searchRestaurants(String location){
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user, Restaurant restaurant){
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode){
        Restaurant restaurant = user.getCart().getRestaurant();
        if(restaurant == null){
            System.out.println("Please select a restaurant first.");
            return;
        }

        for(MenuItem item : restaurant.getMenu()){
            if(item.getCode().equals(itemCode)){
                user.getCart().addItem(item);
                break;
            }
        }
    }

    public Order checkoutNow(User user, String orderType, PaymentStrategy paymentStrategy){
        return checkout(user, orderType, paymentStrategy, new NowOrderFactory());
    }

    public Order checkoutScheduled(User user, String orderType, PaymentStrategy paymentStrategy, String scheduleTime){
        return checkout(user, orderType, paymentStrategy, new ScheduledOrderFactory(scheduleTime));
    }

    public Order checkout(User user, String orderType, PaymentStrategy paymentStrategy, OrderFactory orderFactory){
        if(user.getCart().isEmpty()) return null;

        Cart userCart = user.getCart();
        Restaurant orderedRestaurant = userCart.getRestaurant();
        List<MenuItem> itemsOrdered = userCart.getItems();
        double totalCost = userCart.getTotalCost();

        Order order = orderFactory.createOrder(user, userCart, orderedRestaurant, itemsOrdered, paymentStrategy, totalCost, orderType);
        return order;
    }

    public void payForOrder(User user, Order order){
        boolean isPaymentSuccess = order.processPayment();

        if(isPaymentSuccess){
            NotificationService.notify(order);
            user.getCart().clear();
        }
    }

    public void printUserCart(User user){
        System.out.println("Items in cart:");
        System.out.println("--------------------");

        for(MenuItem item: user.getCart().getItems()){
            System.out.println(item.getCode() + " : " + item.getName() + " - Rs " + item.getPrice());
        }
        System.out.println("------------------------------------");
        System.out.println("Grand total : Rs " + user.getCart().getTotalCost());
    }
}