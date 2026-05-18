interface Database {
    void save(String data);
}

class MySQLDatabase implements Database{
    @Override
    public void save(String data){
        System.out.println("Saving data to MySQL database: " + data);
    }
}

class MongoDb implements Database{
    @Override
    public void save(String data){
        System.out.println("Saving data to MongoDB: " + data);
    }
}

class UserService {
    private final Database db;
    public UserService(Database database){
        this.db = database;
    }

    public void storeUser(String user){
        db.save(user);
    }
}

public class DIPFollowed {
    public static void main(String[] args){
        MySQLDatabase mysql = new MySQLDatabase();
        MongoDb mongo = new MongoDb();
        UserService userService = new UserService(mysql);
        userService.storeUser("John Doe");

        userService = new UserService(mongo);
        userService.storeUser("John Dooooe");
        UserService service2 = new UserService(mongo);
        service2.storeUser("John Doe");
    }
}