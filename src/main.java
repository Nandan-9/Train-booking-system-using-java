import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static final Map<String, String> userAccounts = new HashMap<>();
    public static void main(String[] args) {
        userAccounts.put("Dev@gmail.com", "A D Dev Nandan");
        userAccounts.put("reach.viserion@gmail.com", "Harigovind C B");
        userAccounts.put("reach.gopika@gmail.com", "S Gopika");
        userAccounts.put("sheen@gmail.com", "Sheen Paul");
        Scanner scanner = new Scanner(System.in);
        TrainBookingSystem.drawBox(" User Login ");
        System.out.print("Enter your Gmail: ");
        String gmail;
        try {
            gmail = scanner.nextLine();
            TrainBookingSystem.validateEmail(gmail);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email format. Please enter a valid Gmail address.");
            scanner.close();
            TrainBookingSystem.drawEndBox();
            return;
        }
        if (userAccounts.containsKey(gmail)) {
            String name = userAccounts.get(gmail);
            System.out.println("\nWelcome, " + name + "!");
            System.out.println("You are logged in with Gmail: " + gmail);
            System.out.println();
            TrainBookingSystem.drawBox(" Station Prompt ");
            System.out.print("Please enter the name of the station: ");
            String stationName = scanner.nextLine();
            System.out.println("\nThank you for providing the station name.");
            System.out.println("You have selected the station: " + stationName);
            Train[] trains =         TrainBookingSystem.createTrains();
            TrainBookingSystem.runTrainBookingSystem(trains, scanner);
        } else {
            System.out.println("\nError: Account with Gmail " + gmail + " does not exist.");
            TrainBookingSystem.drawEndBox();
        }
    }

}
