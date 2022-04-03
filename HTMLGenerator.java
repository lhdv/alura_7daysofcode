import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
    private Writer htmlWriter;

    public HTMLGenerator(Writer htmlWriter) {
        this.htmlWriter = htmlWriter;
    }    

    public void generate(List<Movie> movies) throws IOException{

        htmlWriter.write(htmlHeader());

        for (int i = 1; i <= movies.size(); i++) {
            htmlWriter.write(htmlBody(movies.get(i-1)));

            if (i%3 == 0) {
                htmlWriter.write("</div><div class='row'>");
            }
        }

        htmlWriter.write(htmlFooter());
    }

    private String htmlBody(Movie m) {
        return """
<div class="col">
    <div class="card" style="width: 18rem;">
""" + 
String.format("<img src='%s' class='card-img-top' alt='...'>", m.getimgURL())
    +
"""
        <div class="card-body">
""" + 
String.format("<h5 class='card-title'>%s</h5>", m.getTitle())
+
String.format("<p class='card-text'>Year: %d / Rating: %f</p>", m.getYear(), m.getRating())
+
"""
        </div>
    </div>
</div>            
        """;
    }

    private String htmlHeader() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <h1>Top 250 IMDB Movies</h1> 
        <div class="row">
        """;
    }
    
    private String htmlFooter() {
        return """
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
        """;
    }
}
