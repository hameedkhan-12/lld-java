import java.util.ArrayList;
import java.util.List;

interface FileSystemItem {
    void is(int indent);
    void openAll(int indent);
    int getSize();
    FileSystemItem cd(String name);
    String getName();
    boolean isFolder();
}

class File implements FileSystemItem {
    private String name;
    private int size;

    public File(String n, int s){
        name = n;
        size = s;
    }

    @Override
    public void ls(int indent){
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name + " (" + size + " bytes)");
    }

    @Override
    public void openAll(int indent){
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + "Opening " + name);
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public FileSystemItem cd(String name){
        System.out.println("Cannot cd into a file");
        return null;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isFolder(){
        return false;
    }
}

class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> items;

    public Folder(String n){
        name = n;
        items = new ArrayList<>();
    }

    public void add(FileSystemItem item){
        items.add(item);
    }

    @Override
    public void ls(int indent){
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
        for (FileSystemItem item : items){
            if(child.isFolder()){
                System.out.println(indentSpaces + "+ " + child.getName());
            }else{
                System.out.println(indentSpaces + child.getName());
            }
        }
    }

    @Override
    public void openAll(int indent){
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + "Opening " + name);
        for (FileSystemItem item : items){
            item.openAll(indent + 4);
        }
    }

    @Override
    public int getSize(){
        int size = 0;
        for (FileSystemItem item : items){
            size += item.getSize();
        }
        return size;
    }

    @Override
    public FileSystemItem cd(String name){
        for (FileSystemItem item : items){
            if(item.isFolder() && item.getName().equals(name)){
                return (Folder) item;
            }
        }
        return null;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isFolder(){
        return true;
    }
}

public class CompositePattern {
    public static void main(String[] args){
        Folder root = new Folder("root");
        root.add(new File("file1.txt", 1));
        root.add(new File("file2.txt", 1));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf", 1));
        docs.add(new File("notes.txt", 1));
        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File("image1.jpg", 1));
        images.add(new File("image2.jpg", 1));
        root.add(images);

        root.ls(0);
        docs.ls(0);

        root.openAll(0);

        FileSystemItem cwd = root.cd("docs");
        if(cwd != null){
            cwd.ls(0);
        }else {
            System.out.println("Folder not found");
        }

        System.out.println("Size of root: " + root.getSize());
    }
}