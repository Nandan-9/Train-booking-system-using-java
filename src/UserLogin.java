
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class UserLogin {
    private static final Map<String, String> userAccounts = new HashMap<>();
    public static void main(String[] args) {
        // Sample Accounts.
        userAccounts.put("example@gmail.com", "John Doe");
        userAccounts.put("user123@gmail.com", "Alice Smith");
        Scanner scanner = new Scanner(System.in);
        drawBox(" User Login ");
        System.out.print("Enter your Gmail: ");
        String gmail = null;
        try {
            gmail = scanner.nextLine();
            validateEmail(gmail); // Method for email validation
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email format. Please enter a valid Gmail address.");
            scanner.close();
            drawEndBox();
            return;
        }
        if (userAccounts.containsKey(gmail)) {
            String name = userAccounts.get(gmail);
            System.out.println("\nWelcome, " + name + "!");
            System.out.println("You are logged in with Gmail: " + gmail);
            System.out.println();
            drawBox(" Station Prompt ");
            System.out.print("Please enter the name of the station: ");
            String stationName = scanner.nextLine();
            System.out.println("\nThank you for providing the station name.");
            System.out.println("You have selected the station: " + stationName);
        } else {
            System.out.println("\nError: Account with Gmail " + gmail + " does not exist.");
        }
        scanner.close();
        drawEndBox();
    }
    private static void validateEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    private static void drawBox(String message) {
        int messageLength = message.length();
        int boxWidth = messageLength + 4;
        System.out.println("+" + "-".repeat(boxWidth) + "+");
        System.out.println("|  " + message + "  |");
        System.out.println("+" + "-".repeat(boxWidth) + "+");
    }
    private static void drawEndBox() {
        System.out.println("\n" + "+---------------------------+");
        System.out.println("|        End of Program     |");
        System.out.println("+---------------------------+");
    }
}