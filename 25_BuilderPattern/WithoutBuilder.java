import java.util.*;

class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private String body;
    private int timeout;

    public HttpRequest(String url){
        this.url = url;
        this.method = "GET";
        this.timeout = 30;
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    public HttpRequest(String url, String method){
        this.url = url;
        this.method = method;
        this.timeout = 30;
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    public HttpRequest(String url, String method, int timeout){
        this.url = url;
        this.method = method;
        this.timeout = timeout;
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    public HttpRequest(String url, String method, int timeout, Map<String, String> headers, Map<String, String> queryParams){
        this.url = url;
        this.method = method;
        this.timeout = timeout;
        this.body = body;
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    public HttpRequest(String url, String method, int timeout, Map<String, String> headers, Map<String, String> queryParams, String body){
        this.url = url;
        this.method = method;
        this.timeout = timeout;
        this.body = body;
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    public void addHeader(String key, String value){
        this.headers.put(key, value);
    }

    public void addQueryParam(String key, String value){
        this.queryParams.put(key, value);
    }

    public void setBody(String body){
        this.body = body;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public void setTimeout(int timeout){
        this.timeout = timeout;
    }

    public void execute(){
        System.out.println("Executing " + method + " request to " + url);

        if(!queryParams.isEmpty()){
            System.out.println("Query parameters:");
            for(Map.Entry<String, String> entry : queryParams.entrySet()){
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        if(!headers.isEmpty()){
            System.out.println("Headers:");
            for(Map.Entry<String, String> entry : headers.entrySet()){
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        System.out.println("Body: " + body);
        if(body != null && !body.isEmpty()){
            System.out.println("Body length: " + body);
        }

        System.out.println("Timeout: " + timeout);
        System.out.println("Request executed successfully.");
    }
}

public class WithoutBuilder {
    public static void main(String[] args) {
        HttpRequest request1 = new HttpRequest("https://api.example.com");
        HttpRequest request2 = new HttpRequest("https://api.example.com", "POST");
        HttpRequest request3 = new HttpRequest("https://api.example.com", "POST", 60);
        HttpRequest request4 = new HttpRequest("https://api.example.com");
        request4.setMethod("POST");
        request4.addHeader("Content-Type", "application/json");
        request4.addQueryParam("param1", "value1");
        request4.addQueryParam("param2", "value2");
        request4.setBody("{\"name\": \"John\", \"age\": 30}");

        request4.execute();
    }
}