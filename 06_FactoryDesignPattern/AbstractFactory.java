interface Burger {
    void prepare();
}

class BasicBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Basic Burger");
    }
}

class StandardBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Standard Burger");
    }
}

class PremiumBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Premium Burger");
    }
}

class BasicWheatBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Basic Whear Burger");
    }
}

class StandardWheatBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Standard Wheat Burger");
    }
}

class PremiumWheatBurger implements Burger {
    public void prepare(){
        System.out.println("Preparing Premium Wheat Burger");
    }
}

interface GarlicBread {
    void prepare();
}

class BasicGarlicBread implements GarlicBread {
    public void prepare(){
        System.out.println("Preparing Basic Garlic Bread");
    }
}

class CheeseGarlicBread implements GarlicBread{
    public void prepare(){
        System.out.println("Preparing Cheese Garlic Bread");
    }
}

class BasicWheatGarlicBread implements GarlicBread{
    public void prepare(){
        System.out.println("Preparing Basic Wheat Garlic Bread");
    }
}

class CheeseWheatGarlicBread implements GarlicBread {
    public void prepare(){
        System.out.println("Preparing Cheese Wheat Garlic Bread");
    }
}

interface MealFactory {
    Burger createBurger(String type);
    GarlicBread createGarlicBread(String type);
}

class YumBurger implements MealFactory {
    public Burger createBurger(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicBurger();
        }else if(type.equalsIgnoreCase("standard")){
            return new StandardBurger();
        }else if(type.equalsIgnoreCase("premium")){
            return new PremiumBurger();
        }else {
            System.out.println("Invalid Burger Type");
            return null;
        }
    }

    public GarlicBread createGarlicBread(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicGarlicBread();
        }
        else if(type.equalsIgnoreCase("cheese")){
            return new CheeseGarlicBread();
        }
        else {
            System.out.println("Invalid Garlic Bread Type");
            return null;
        }
    }
}

class KingBurger implements MealFactory {
      public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }

    public GarlicBread createGarlicBread(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatGarlicBread();
        } else if (type.equalsIgnoreCase("cheese")) {
            return new CheeseWheatGarlicBread();
        } else {
            System.out.println("Invalid Garlic bread type!");
            return null;
        }
    }
}

public class AbstractFactory {
    public static void main(String[] args){
        String burgerType = "basic";
        String garlicBreadType = "cheese";
        
        MealFactory factory = new KingBurger();
        Burger burger = factory.createBurger(burgerType);
        GarlicBread garlicBread = factory.createGarlicBread(garlicBreadType);
        burger.prepare();
        garlicBread.prepare();
    }
}