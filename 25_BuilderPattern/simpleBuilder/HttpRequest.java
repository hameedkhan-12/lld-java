package simpleBuilder;

import java.util.*;

public class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private String body;
    private int timeout;

    HttpRequest(){
        headers = new HashMap<>();
        queryParams = new HashMap<>();
        body = "";
    }

    public void execute(){
        System.out.println("Executing " + method + " request to " + url);

        if(!queryParams.isEmpty()){
            System.out.println("Query Parameters: ");
            for(Map.Entry<String,String> param : queryParams.entrySet()){
                System.out.println(param.getKey() + ": " + param.getValue());
            }
        }

        if(!headers.isEmpty()){
            System.out.println("Headers: ");
            for(Map.Entry<String,String> header : headers.entrySet()){
                System.out.println(header.getKey() + ": " + header.getValue());
            }
        }

        if(body != null && !body.isEmpty()){
            System.out.println("Body: " + body);
        }

        System.out.println("Timeout: " + timeout);
        System.out.println("Request executed successfully.");
    }
public static class HttpRequestBuilder {
    private HttpRequest req;

    public HttpRequestBuilder() {
        req = new HttpRequest();
    }

    public HttpRequestBuilder withUrl(String url) {
        req.url = url;
        return this;
    }

    public HttpRequestBuilder withMethod(String method) {
        req.setMethod(method);
        return this;
    }

    public HttpRequestBuilder withTimeout(int timeout) {
        req.setTimeout(timeout);
        return this;
    }

    public HttpRequestBuilder withHeader(String key, String value) {
        req.headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder withQueryParams(String key, String value) {
        req.queryParams.put(key, value);
        return this;
    }

    public HttpRequestBuilder withBody(String body) {
        req.body = body;
        return this;
    }

    public HttpRequestBuilder withTimeout(int timeout) {
        req.timeout = timeout;
        return this;
    }

    public HttpRequest build() {
        if(req.url == null || req.url.isEmpty()){
            throw new RuntimeException("URL cannot be empty.");
        }

        return req;
    }
}
}
