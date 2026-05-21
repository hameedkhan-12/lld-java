import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

interface DocumentElement {
    public abstract String render();
}

class TextElement implements DocumentElement {
    private String text;
    public TextElement(String text) {
        this.text = text;
    }
    public String render() {
        return this.text;
    }
}

class ImageElement implements DocumentElement {
    private String imagePath;

    public ImageElement(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String render(){
        return "[Image: ]" + this.imagePath + "]\n";
    }
}

class NewLineElement implements DocumentElement {
    @Override
    public String render(){
        return "\n";
    }
}

class TabSpaceElement implements DocumentElement {
    @Override
    public String render(){
        return "\t";
    }
}

class Document {
    private List<DocumentElement> elements = new ArrayList<>();
    public void addElements(DocumentElement element){
        elements.add(element);
    }
    public String render(){
        StringBuilder result = new StringBuilder();
        for(DocumentElement element : elements){
            result.append(element.render());
        }
        return result.toString();
    }
}

interface Persistence {
    void save(String data);
}

class FileStorage implements Persistence {
    @Override
    public void save(String data){
        try{
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("Document saved to file.");
        }catch(IOException e){
            System.out.println("Error saving document to file: " + e.getMessage());
        }
    }
}

class DBStorage implements Persistence {
    private Document document;
    public DBStorage(Document document){
        this.document = document;
    }
    @Override
    public void save(String data){
        System.out.println("Saving document to DB: " + data);
    }
}

class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String rendererDocument = "";

    public DocumentEditor(Document document, Persistence storage){
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text){
        document.addElements(new TextElement(text));
    }
    public void addImage(String imagePath){
        document.addElements(new ImageElement(imagePath));
    }

    public void addNewLine(){
        document.addElements(new NewLineElement());
    }

    public void addTabSpace(){
        document.addElements(new TabSpaceElement());
    }

    public String rendererDocument(){
        if(rendererDocument.isEmpty()){
            rendererDocument = document.render();
        }
        return rendererDocument;
    }

    public void saveDocument(){
        storage.save(rendererDocument());
    }
}

public class DocEditorClient {
    public static void main(String[] args){
        Document document = new Document();
        Persistence storage = new FileStorage();
        DocumentEditor editor = new DocumentEditor(document, storage);
        editor.addText("Hello, world!");
        editor.addImage("picture.jpg");
        editor.addNewLine();
        editor.addTabSpace();
        editor.addText("This is a document editor.");
        editor.saveDocument();

        System.out.println(editor.rendererDocument());
    }
}