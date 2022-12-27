
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Rating> ratings;

    public Restaurant(String name) {
        this.name = name;
        this.ratings = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "name= " + name +"    rating= "+getAverageRating();
    }

    public String getName() {
        return name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.size() == 0) {
            return 0;
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return sum / ratings.size();
    }
}

