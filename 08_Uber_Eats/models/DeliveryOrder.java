package models;

public class DeliveryOrder extends Order{
    private String userAddress;

    public DeliveryOrder(){
        userAddress = "";
    }

    @Override
    public String getType(){
        return "Delivery";
    }

    public void setUserAddress(String userAddress){
        this.userAddress = userAddress;
    }

    public String getUserAddress(){
        return userAddress;
    }
}