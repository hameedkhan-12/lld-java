interface IReports {
    String getJsonData(String data);
}

class XmlDataProvider {
    String getXmlData (String data){
        int sep = data.indexOf(":");
        String name = data.substring(0, sep);
        String id = data.substring(sep + 1);

        return "<user>"
         + "<name>" + name + "</name>" 
         + "<id>" + id + "</id>" + 
         "</user>";
    }
}

class XmlDataProviderAdapter implements IReports {
    private XmlDataProvider xmlProvider;

    public XmlDataProvider(XmlDataProvider xmlProvider) {
        this.xmlProvider = xmlProvider;
    }

    public String getJsonData(String data){
        String xml = xmlProvider.getXmlData(data);

        int startName = xml.indexOf("<name>") + 6;
        int endName = xml.indexOf("</name>");
        String name = xml.substring(startName, endName);

        int startId = xml.indexOf("<id>") + 4;
        int endId = xml.indexOf("</id>");
        String id = xml.substring(startId, endId);

        return "{\"name\":\"" + name + "\", \"id\":\"" + id + "\"}";
    }
}

class Client {
    public void getReport(IReports report, String rawData){
        System.out.println("Processed JSON: " + report.getJsonData(rawData));
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        XmlDataProvider xmlProvider = new XmlDataProvider();

        IReports adapter = new XmlDataProviderAdapter(xmlProvider);  

        String rawData = "Alice:42";

        Client client = new Client();

        client.getReport(adapter, rawData);      
    }
}