import java.util.ArrayList;
import java.util.List;

interface DepositOnlyAccount {
    void deposit(double amount);
}

interface WithdrawaableAccount extends DepositOnlyAccount {
    void withdraw(double amount);
}

class SavingAccount implements WithdrawaableAccount {
    private double balance;

    public SavingAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + " in Savings Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance>=amount){
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Savings Account. New Balance: " + balance);
        }else{
            System.out.println("Insufficient funds in Savings Account!");
        }
    }
}

class CurrentAccount implements WithdrawaableAccount {
    private double balance;

    public CurrentAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + " in Current Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance>=amount){
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Current Account. New Balance: " + balance);
        }else{
            System.out.println("Insufficient funds in Current Account!");
        }
    }
}

class FixedTermAccount implements DepositOnlyAccount {
    private double balance;

    public FixedTermAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + " in Fixed Term Account. New Balance: " + balance);
    }
}

class BankClient {
    private List<DepositOnlyAccount> depositOnlyAccounts;
    private List<WithdrawaableAccount> withdrawableAccounts;

    public BankClient(List<WithdrawaableAccount> withdrawableAccounts, List<DepositOnlyAccount> depositOnlyAccounts) {
        this.withdrawableAccounts = withdrawableAccounts;
        this.depositOnlyAccounts = depositOnlyAccounts;
    }

    public void processTransactions(){
        for(WithdrawaableAccount acc : withdrawableAccounts){
            acc.deposit(1000);
            acc.withdraw(500);
        }

        for(DepositOnlyAccount acc : depositOnlyAccounts){
            acc.deposit(1000);
        }
    }
}

public class LSPFollowed {
    public static void main(String[] args){
        List<WithdrawaableAccount> withdrawableAccounts = new ArrayList<>(); // LSP Followed
        withdrawableAccounts.add(new SavingAccount());
        withdrawableAccounts.add(new CurrentAccount());

        List<DepositOnlyAccount> depositOnlyAccounts = new ArrayList<>(); // LSP Followed
        depositOnlyAccounts.add(new SavingAccount());
        depositOnlyAccounts.add(new FixedTermAccount());

        BankClient client = new BankClient(withdrawableAccounts, depositOnlyAccounts);
        client.processTransactions();
    }
}