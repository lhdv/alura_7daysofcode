import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class IMDBTopMovies {
    public static void main(String[] args) {
        
        System.out.println("=== IMDB CLI ===");

        if (args.length < 1) {
            System.out.println("[ERROR] API Key param must be provided. Exiting...");
            System.exit(-1);
        }

        String apiEndpoint = getAPIEndpoint("https://imdb-api.com/en/API/Top250Movies", args[0]);

        System.out.println("[INFO] Calling API EndPoint: "+ apiEndpoint);

        System.out.println(callAPI(apiEndpoint));
    }
    
    private static String getAPIEndpoint (String uri, String key) {
        String apiKey = key.trim();
        String apiEndPoint = String.format(uri + "/%s", apiKey);        
        
        return apiEndPoint;
    }
    
    private static String callAPI(String uri) {
        HttpClient client = null;
        HttpRequest request = null;
        HttpResponse<String> response = null;
        
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();
        
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("[ERROR] Exception when calling API: " + e.getMessage());
        }

        return response.body();
    }
}
