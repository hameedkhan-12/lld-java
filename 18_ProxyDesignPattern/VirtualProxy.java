interface IImage {
    void display();
}

class RealImage implements IImage {
    private String filePath;

    public RealImage(String file){
        this.filePath = file;
        System.out.println("[RealImage] Loading image from disk: " + filePath);
    }

    @Override
    public void display() {
        System.out.println("[RealImage] Displaying image: " + filePath);
    }
}

class ImageProxy implements IImage {
    private RealImage realImage;
    private String filePath;

    public ImageProxy(String filePath) {
        this.filePath = filePath;
        this.realImage = null;
    }

    @Override
    public void display(){
        if(realImage == null){
            realImage = new RealImage(filePath);
        }
        realImage.display();
    }
}

public class VirtualProxy {
    public static void main(String[] args) {
        IImage image = new ImageProxy("image.jpg");
        image.display();
    }
}