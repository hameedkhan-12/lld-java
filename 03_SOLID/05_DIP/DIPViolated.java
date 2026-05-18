class MySQLDatabase {
    public void saveToSQL(String data) {
        System.out.println("Saving data to MySQL database: " + data);
    }
}

class MongoDb {
    public void saveToMongo(String data){
        System.out.println("Saving data to MongoDB: " + data);
    }
}

class UserService {
    private final MySQLDatabase mySQLDatabase = new MySQLDatabase();
    private final MongoDb mongoDb = new MongoDb();

    public void storeUserToSQL (String user){
        mySQLDatabase.saveToSQL(user);
    }

    public void storeUserToMongo(String user){
        mongoDb.saveToMongo(user);
    }
}

public class DIPViolated {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.storeUserToSQL("John Doe");
        userService.storeUserToMongo("John Doe");
    }
}