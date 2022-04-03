public class Movie {
    private String title;
    private String imgURL;
    private int year;
    private float rating;
    private int rank;
    
    public Movie(String title, String imgURL, int year, float rating, int rank) {
        this.title = title;
        this.imgURL = imgURL;
        this.year = year;
        this.rating = rating;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getimgURL() {
        return imgURL;
    }
    public void setimgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }  
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    } 
    

    @Override
    public String toString() {        
        return String.format("[Title: %s / Year: %d / Rating: %f / Rank: %d]", this.title, this.year, this.rating, this.rank);
    }
}
