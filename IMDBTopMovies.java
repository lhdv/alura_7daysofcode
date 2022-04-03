import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IMDBTopMovies {
    public static void main(String[] args) {
       
        System.out.println("=== IMDB CLI ===");

        if (args.length < 1) {
            System.out.println("[ERROR] API Key param must be provided. Exiting...");
            System.exit(-1);
        }

        String apiEndpoint = getAPIEndpoint("https://imdb-api.com/en/API/Top250Movies", args[0]);

        System.out.println("[INFO] Calling API EndPoint: "+ apiEndpoint);

        String json = callAPI(apiEndpoint);
        List<Movie> movieList = getMoviesFromJson(json);

        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("index.html"));
            HTMLGenerator htmlFileWriter = new HTMLGenerator(bufWriter);
            htmlFileWriter.generate(movieList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static String getJsonValue(String rawJson) {
        String[] result = rawJson.split("\":\"");
        return result[1];
    }

    private static List<String> getRecordsFromJson(String json) {
        return Arrays.asList(json.split("},"));
    }

    private static List<String> parseTitles(List<String> recs) {
        return parseFields(recs, 2);
    }

    private static List<String> parseImages(List<String> recs) {
        return parseFields(recs, 5);
    }

    private static List<String> parseFields(List<String> recs, int fieldNum) {
        ArrayList<String> result = new ArrayList<String>();

        for (String r : recs) {
            String[] fields = r.split("\",\"");
            result.add(getJsonValue(fields[fieldNum]));
        }

        return result;
    }

    private static List<Movie> getMoviesFromJson(String json) {
        ArrayList<Movie> result = new ArrayList<Movie>();

        List<String> records = getRecordsFromJson(json);

        for (String r : records) {
            String[] fields = r.split("\",\"");
            result.add(new Movie(getJsonValue(fields[2]), 
                getJsonValue(fields[5]), 
                Integer.parseInt(getJsonValue(fields[4])), 
                Float.parseFloat(getJsonValue(fields[7])),
                Integer.parseInt(getJsonValue(fields[1])))
            );
        }
        
        return result;
    }

}
