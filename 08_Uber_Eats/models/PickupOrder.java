package models;

public class PickupOrder extends Order{
    private String restaurantAddress;

    public PickupOrder(){
        restaurantAddress = "";
    }

    @Override
    public String getType(){
        return "Pickup";
    }

    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantAddress(){
        return restaurantAddress;
    }
}