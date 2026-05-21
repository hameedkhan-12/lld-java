import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

class DocumentEditor {
    private List<String> documentElements;
    private String rendererDocument;

    public DocumentEditor(){
        documentElements = new ArrayList<>();
        rendererDocument = "";
    }
    public void addText(String text){
        documentElements.add(text);
    }

    public void addImage(String imagePath){
        documentElements.add(imagePath);
    }

    public String rendererDocument(){
        if(rendererDocument.isEmpty()){
            StringBuilder result = new StringBuilder();
            for(String element : documentElements){
                if(element.length() > 4 && (element.endsWith(".jpg") || element.endsWith(".png"))){
                    result.append("[Image: ]").append(element).append("]\n");
                }else {
                    result.append(element).append("\n");
                }
            }
            rendererDocument = result.toString();
        }
        return rendererDocument;
    }

    public void saveToFile() {
        try{
            FileWriter writer = new FileWriter("document.txt");
            writer.write(rendererDocument());
            writer.close();
            System.out.println("Document saved to file.");
        }catch(IOException e){
            System.out.println("Error saving document to file: " + e.getMessage());
        }
    }
}

public class DocEditorClient {
    public static void main(String[] args){
        DocumentEditor editor = new DocumentEditor();
        editor.addText("Hello, world!");
        editor.addImage("picture.jpg");
        editor.addText("This is a document editor.");

        System.out.println(editor.rendererDocument());
        editor.saveToFile();
    }
}