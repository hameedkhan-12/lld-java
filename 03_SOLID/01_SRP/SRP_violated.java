import java.util.ArrayList;
import java.util.List;

class Product {
    public String name;
    public double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
}

//Violating SRP(Single Responsibility Principle) because shopping cart is handling multiple responsibilities
class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p){
        products.add(p);
    }

    public List<Product> getProducts(){
        return products;
    }

    public double calculateTotal(){
        double total = 0;
        for(Product p : products){
            total += p.price;
        }

        return total;
    }

    //Violating SRP: Prints Invoice should have a different class
    public void printInvoice(){
        System.out.println("Invoice:");
        for(Product p : products){
            System.out.println(p.name + " - $" + p.price);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    //Violating SRP: Save to database should have a different class
    public void saveToDatabase(){
        //save to database
    }
}

public class SRP_violated {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 1000));
        cart.addProduct(new Product("Mobile", 500));
        cart.printInvoice();
        cart.saveToDatabase();
    }
}