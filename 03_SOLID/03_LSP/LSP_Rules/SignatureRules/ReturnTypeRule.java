class Animal {
    // some common animal methods
}

class Cat extends Animal {
    // some cat methods
}

class Parent {
    public Animal getAnimal(){
        System.out.println("Parent method called");
        return new Animal();
    }
}

class Child extends Parent {
    public Animal getAnimal(){
        System.out.println("Child method called");
        return new Cat();
    }
}

class Client {
    private Parent p;

    public Client(Parent p){
        this.p = p;
    }

    public void takeAnimal() {
        p.getAnimal();
    }
}

public class ReturnTypeRule {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        Client client = new Client(child);
        client.takeAnimal();
    }
}