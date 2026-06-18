interface Cloneable {
    Cloneable clone();
}

class NPC implements Cloneable {
    public String name;
    public int health;
    public int attack;
    public int defense;

    public NPC(String name, int health, int attack, int defense){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;

        System.out.println("Creating " + name + " with health " + health + ", attack " + attack + ", and defense " + defense);
    }

    public NPC(NPC other){
        name = other.name;
        health = other.health;
        attack = other.attack;
        defense = other.defense;

        System.out.println("Cloning " + name + " with health " + health + ", attack " + attack + ", and defense " + defense);
    }

    public Cloneable clone(){
        return new NPC(this);
    }

    public void describe(){
        System.out.println(name + " has " + health + " health, " + attack + " attack, and " + defense + " defense.");
    }

    public void setName(String n){
        name = n;
    }

    public void setHealth(int h){
        health = h;
    }

    public void setAttack(int a){
        attack = a;
    }

    public void setDefense(int d){
        defense = d;
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        NPC alien = new NPC("Alien", 30, 5, 2);
        NPC clone1 = (NPC) alien.clone();
        clone1.describe();

        NPC clone2 = (NPC) alien.clone(); 
        clone2.setName("Powerful Alien");
        clone2.setHealth(50);
        clone2.setAttack(10);
        clone2.setDefense(5);
        clone2.describe();

        alien = null;
        clone1 = null;
        clone2 = null;
    }
}