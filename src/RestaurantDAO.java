import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO {
    // the database URL, username, and password can be configured here
    private static final String DB_URL = "jdbc:mysql://localhost/restaurant_db";
    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "password";


    public void saveRestaurant(Restaurant restaurant)  {
        // establish a connection to the database
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){

            // create a prepared statement to insert the restaurant into the database
            String sql = "INSERT INTO restaurants (name, rating, review) VALUES (?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for(Rating rating: restaurant.getRatings()) {
                stmt.setString(1, restaurant.getName());
                stmt.setDouble(2, rating.getRating());
                stmt.setString(2, rating.getReview());
            }
            // execute the insert statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Restaurant getRestaurantByName(String name) throws SQLException {
        // establish a connection to the database
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            // create a prepared statement to select the restaurant from the database
            String sql = "SELECT * FROM restaurants WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Restaurant restaurant = new Restaurant(rs.getString("name"));
                restaurant.addRating(new Rating(rs.getInt("rating"),rs.getString("review")));
                while (rs.next()){
                    restaurant.addRating(new Rating(rs.getInt("rating"),rs.getString("review")));
                }
                return restaurant;
            }
            return null;
        }
    }
    public List<Restaurant> getRestaurants() throws SQLException {
        // establish a connection to the database
        List<Restaurant> restaurants=new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // create a prepared statement to select the restaurant from the database
            String sql = "SELECT * FROM restaurants";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Restaurant restaurant = new Restaurant(rs.getString("name"));
                restaurant.addRating(new Rating(rs.getInt("rating"),rs.getString("review")));
                while (rs.next()){
                    restaurant.addRating(new Rating(rs.getInt("rating"),rs.getString("review")));
                }
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

}
