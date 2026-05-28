import models.*;
import strategies.*;

public class Main {
    public static void main(String[] args){
        UberEats uberEats = new UberEats();

        User user = new User(101, "Hameed", "Taxila");
        System.out.println("User: " + user.getName() + " " is active);

        java.util.List<Restaurant> restaurantList = uberEats.searchRestaurants("Taxila");

        if(restaurantList.isEmpty()){
            System.out.println("No restaurants found");
            return;
        }

        System.out.println("Restaurants found: " + restaurantList.size());

        for(Restaurant res: restaurantList){
            System.out.println(" - " + restaurant.getName());
        }

        uberEats.selectRestaurant(user, restaurantList.get(0));
        System.out.println("Selected Restaurant: " + user.get(0).getName());

        uberEats.addToCart(user, "P1");
        uberEats.addToCart(user, "P2");

        uberEats.printUserCart(user);

        Order order = uberEats.checkoutNow(user, "Delivery", new UpiPaymentStrategy("1234567890"));

        uberEats.payForOrder(user, order);
    }
}