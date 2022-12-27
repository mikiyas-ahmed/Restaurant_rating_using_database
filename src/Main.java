import java.sql.SQLException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantDAO restaurantDAO= new RestaurantDAO();
        // Main loop
        while (true) {
            System.out.println("\n1. Add Restaurant Rating");
            System.out.println("2. View restaurants Rating");
            System.out.println("3. Exit");
            System.out.print("Enter a choice: ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("\nEnter Restaurant name: ");
                scanner.nextLine();
                String name= scanner.nextLine();
                System.out.print("Enter rating (1-5): ");
                int rating = scanner.nextInt();
                System.out.print("Enter review: ");
                scanner.nextLine();
                String review= scanner.nextLine();
                Restaurant restaurant= new Restaurant(name);
                restaurant.addRating(new Rating(rating,review));
                restaurantDAO.saveRestaurant(restaurant);
            }   else if (choice == 2) {
                System.out.println("\nRestaurant name   | Rating");
                try {
                    for (Restaurant restaurant : restaurantDAO.getRestaurants()) {
                        System.out.println(restaurant);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("press enter key to continue");
                scanner.nextLine();
                scanner.nextLine();
            } else if (choice == 3) {
                break;
            }
        }
    }
}
