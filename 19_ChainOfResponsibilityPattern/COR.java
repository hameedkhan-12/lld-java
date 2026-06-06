abstract class Approver {
    protected Approver nextApprover;
    public void setNext(Approver next){
        this.nextApprover = next;
    }

    public abstract void approve(int amount);
}

class Manager extends Approver {
    @Override
    public void approve(int amount){
        if(amount <= 1000){
            System.out.println("Manager approved the amount");
        }else if(nextApprover != null){
            nextApprover.approve(amount);
        }
    }
}

class Director extends Approver {
    @Override
    public void approve(int amount){
        if(amount <= 5000){
            System.out.println("Director approved the amount");
        }else if(nextApprover != null){
            nextApprover.approve(amount);
        }
    }
}

class CEO extends Approver {
    @Override
    public void approve(int amount){
        if(amount <= 10000){
            System.out.println("CEO approved the amount");
        }else if(nextApprover != null){
            nextApprover.approve(amount);
        }
    }
}

class COR {
    public static void main(String[] args) {
        Approver manager = new Manager();
        Approver director = new Director();
        Approver ceo = new CEO();
        manager.setNext(director);
        director.setNext(ceo);
        manager.approve(100);
        manager.approve(5000);
        manager.approve(10000);
    }
}