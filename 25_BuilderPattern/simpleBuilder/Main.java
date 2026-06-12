package simpleBuilder;

public class Main {
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.HttpRequestBuilder()
        .withUrl("https://api.example.com")
        .withMethod("POST")
        .withTimeout(60)
        .withHeader("Content-Type", "application/json")
        .withQueryParams("param1", "value1")
        .withQueryParams("param2", "value2")
        .withBody("{\"name\": \"John\", \"age\": 30}")
        .build();

        request.execute();
    }
}