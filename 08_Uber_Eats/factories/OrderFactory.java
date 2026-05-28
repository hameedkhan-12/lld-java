package factories;

import java.util.List;
import models.*;
import strategies.*;

public interface OrderFactory {
    Order createOrder(User user, Cart cart, Restaurant orderedRestaurant, List<MenuItem> itemsOrdered, PaymentStrategy paymentStrategy, double totalCost, String orderType);
}