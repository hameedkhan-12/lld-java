package models;
import java.util.*;

public class Cart{
    private Restaurant restaurant;
    private List<MenuItem> items = new ArrayList<>();

    public Cart(){
        restaurant = null;
    }

    public void addItem(MenuItem item){
        if(restaurant == null){
            System.err.println("Please select a restaurant first.");
            return;
        }

        items.add(item);
    }

    public double getTotalCost(){
        double sum = 0;
        for(MenuItem item: items){
            sum += item.getPrice();
        }

        return sum;
    }

    public boolean isEmpty(){
        return restaurant == null || items.isEmpty();
    }

    public void clear(){
        items.clear();
        restaurant = null;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public Restaurant  getRestaurant(){
        return restaurant;
    }

    public List<MenuItem> getItems(){
        return items;
    }
}