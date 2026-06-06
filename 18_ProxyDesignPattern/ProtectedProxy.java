interface IDocumentReader {
    void unlockPDF(String filePath, String password);
}

class RealDocumentReader implements IDocumentReader {
    @Override
    public void unlockPDF(String filePath, String password) {
        System.out.println("[RealDocumentReader] Unlocking PDF at: " + filePath);
        System.out.println("[RealDocumentReader] PDF unlocked successfully with password: " + password);
        System.out.println("[RealDocumentReader] Displaying PDF content...");
    }
}

class User {
    public String name;
    public boolean premiumMembership;

    public User(String name, boolean isPremium){
        this.name = name;
        this.premiumMembership = isPremium;
    }
}

class DocumentProxy implements IDocumentReader {
    private RealDocumentReader realDocumentReader;
    private User user;

    public DocumentProxy(User user) {
        this.user = user;
        this.realDocumentReader = new RealDocumentReader();
    }

    @Override
    public void unlockPDF(String filePath, String password) {
        if (user.premiumMembership) {
            realDocumentReader.unlockPDF(filePath, password);
        } else {
            System.out.println("[DocumentProxy] User does not have premium membership. Access denied.");
        }
    }
}

public class ProtectedProxy {
    public static void main(String[] args){
        User user1 = new User("John Doe", true);
        User user2 = new User("Jane Doe", false);

        System.out.println("User 1: " + user1.name + " - " + user1.premiumMembership);
        System.out.println("User 2: " + user2.name + " - " + user2.premiumMembership);

        IDocumentReader docReader = new DocumentProxy(user1);
        docReader.unlockPDF("path/to/file.pdf", "password123");

        docReader = new DocumentProxy(user2);
        docReader.unlockPDF("path/to/file.pdf", "password123");
    }
}