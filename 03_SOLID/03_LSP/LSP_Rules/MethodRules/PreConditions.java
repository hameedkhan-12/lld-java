class User {
    public void setPassword(String password)  {
        if(password.length() < 8){
            throw new IllegalArgumentException("Pass must be 8 chars long");
        }
        System.out.println("Password set successfully");
    }
}

class AdminUser extends User {
    @Override
    public void setPassword(String password)  {
        if(password.length() < 6){
            throw new IllegalArgumentException("Pass must be 6 chars long");
        }
        System.out.println("Password set successfully");
    }
}

public class PreConditions {
    public static void main(String[] args){
        User user = new AdminUser();
        user.setPassword("Admin1");
    }
}