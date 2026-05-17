class BankAccount {
    protected double balance;

    public BankAccount(double b){
        if(b < 0) throw new IllegalArgumentException("Balance can't be negative");
        this.balance = b;
    }

    public void withdraw(double amount){
        if(balance - amount < 0){
            throw new RuntimeException("Insufficient funds");
        }
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

class CheatAccount extends BankAccount {
    public CheatAccount(double b){
        super(b);
    }

    @Override
    public void withdraw(double amount){
        // LSP break! because of negative balance
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

public class ClassInvariants {
    public static void main(String[] args){
        BankAccount b = new BankAccount(100);
        b.withdraw(50);
        CheatAccount c = new CheatAccount(100);
        c.withdraw(50);
    }
}