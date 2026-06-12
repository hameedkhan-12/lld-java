package builderWithDirector;

public class HttpRequestDirector {
    public static HttpRequest createGetRequest(String url){
        return new HttpRequest.HttpRequestBuilder().withUrl(url).withMethod("GET").build();
    }

    public static HttpRequest createJsonPostRequest(String url, String jsonBody){
        return new HttpRequest.HttpRequestBuilder()
        .withUrl(url)
        .withMethod("POST")
        .withHeader("Content-Type", "application/json")
        .withHeader("Accept", "application/json")
        .withBody(jsonBody)
        .build();
    }
}