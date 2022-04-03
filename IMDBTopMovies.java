public class IMDBTopMovies {
    public static void main(String[] args) {

        System.out.println("=== IMDB CLI ===");

        if (args.length < 1) {
            System.out.println("[ERROR] API Key param must be provided. Exiting...");
            System.exit(-1);
        }

        String apiKey = args[0].trim();
        String apiEndPoint = String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey);

        System.out.println(apiEndPoint);
    }
}
