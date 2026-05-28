package managers;

import models.*;
import java.util.List;
import java.util.ArrayList;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static OrderManager instance = null;

    private OrderManager(){
        //private constructor
    }

    public static OrderManager getInstance(){
        if(instance == null){
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void listOrders(){
        System.out.println("\n--- Orders ---");
        for(Order order : orders){
            System.out.println(order.getType() + " order for " + order.getUser().getName() + " at " + order.getRestaurant().getName());
        }
    }
}