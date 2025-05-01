import java.util.Scanner;

public class LibraryPro {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Create Scanner object to get input
        login(input); // Call the login method
    }

    // Login method
    public static void login(Scanner input) {

        // Hardcoded correct username and password
        String USERNAME = "admin";
        String PASSWORD = "1234";

        while (true) {
            System.out.print("Enter Username: ");
            String user = input.nextLine(); // Get input for username

            System.out.print("Enter Password: ");
            String pass = input.nextLine(); // Get input for password

            // Check if both are correct
            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login successful!");
                break; // Stop the loop if login is successful
            } else {
                System.out.println("Wrong username or password. Try again.\n");
            }
        }
    }
}
