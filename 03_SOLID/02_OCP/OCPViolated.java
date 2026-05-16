
import java.util.ArrayList;
import java.util.List;

class Product {
    String name;
    double price;

    Product(String name, double price){
        this.name = name;
        this.price = price;
    }
}
class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    void addProduct(Product p){
        products.add(p);
    }

    List<Product> getProducts(){
        return products;
    }

    double calculateTotal(){
        double total = 0;
        for(Product p : products){
            total += p.price;
        }
        return total;
    }
}

class ShoppingCartStorage {
    private ShoppingCart cart;
    ShoppingCartStorage(ShoppingCart cart){
        this.cart = cart;
    }

    void saveToSQLDB(){
        System.out.println("Saving shopping cart to SQL DB...");
    }

    void saveToMongoDB(){
        System.out.println("Saving Shopping cart to MONGO DB");
    }

    void saveToFile(){
        System.out.println("Saving to file");
    }
}

class ShoppingCartPrinter {
    private ShoppingCart cart;

    ShoppingCartPrinter(ShoppingCart cart){
        this.cart = cart;
    }

    void printInvoice(){
        System.out.println("Shopping cart Invoice");
        for(Product p : cart.getProducts()){
            System.out.println(p.name + " - Rs " + p.price);
        }
        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}
public class OCPViolated {
    public static void main(String[] args){
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        ShoppingCartStorage db = new ShoppingCartStorage(cart);
        db.saveToSQLDB();
    }
}