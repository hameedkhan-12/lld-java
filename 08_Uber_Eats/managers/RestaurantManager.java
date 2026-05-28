package managers;
import models.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;

    private RestaurantManager(){
        //private constructor
    }

    public static RestaurantManager getInstance(){
        if(instance == null){
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public List<Restaurant> searchByLocation(String loc){
        List<Restaurant> result = new ArrayList<>();
        loc = loc.toLowerCase();

        for(Restaurant r: restaurants){
            String rloc = r.getLocation().toLowerCase();
            if(rloc.equals(loc)){
                result.add(r);
            }
        }
        return result;
    }
}