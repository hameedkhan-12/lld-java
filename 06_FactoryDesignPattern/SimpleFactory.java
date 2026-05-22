interface Burger {
    void prepare();
}

class BasicBurger implements Burger {
    @Override
    public void prepare(){
        System.out.println("Preparing Basic Burger");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare(){
        System.out.println("Preparing Standard Burger");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare(){
        System.out.println("Preparing Premium Burger");
    }
}

class BurgerFactory {
    public Burger createBurger(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicBurger();
        }else if(type.equalsIgnoreCase("standard")){
            return new StandardBurger();
        }else if(type.equalsIgnoreCase("premium")){
            return new PremiumBurger();
        }
        return null;
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        String type = "standard";
        BurgerFactory factory = new BurgerFactory();
        Burger burger = factory.createBurger(type);
        burger.prepare();
    }
}