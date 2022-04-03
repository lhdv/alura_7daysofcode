public class IMDBTopMovies {
    public static void main(String[] args) {

        System.out.println("=== IMDB CLI ===");

        if (args.length < 1) {
            System.out.println("[ERROR] API Key param must be provided. Exiting...");
            System.exit(-1);
        }

        String apiEndpoint = getAPIEndpoint("https://imdb-api.com/en/API/Top250Movies", args[0]);

        System.out.println(apiEndpoint);
    }

    private static String getAPIEndpoint (String uri, String key) {
        String apiKey = key.trim();
        String apiEndPoint = String.format(uri + "/%s", apiKey);        

        return apiEndPoint;
    }
}
