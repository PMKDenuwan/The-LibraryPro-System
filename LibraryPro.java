import java.util.Scanner;

public class LibraryPro {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Create Scanner object to get input
        login(input); // Call the login method
        showHomeMenu(input);
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

    // Home Menu method
    public static void showHomeMenu(Scanner input) {
        while (true) {
            System.out.println("=== 📚 LibraryPro - Home Page ===");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. View Reports");
            System.out.println("6. Exit System");
            System.out.print("Select an option (1-6): ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("👉 Manage Books selected.");
                    break;
                case "2":
                    System.out.println("👉 Manage Members selected.");
                    break;
                case "3":
                    System.out.println("👉 Issue Books selected.");
                    break;
                case "4":
                    System.out.println("👉 Return Books selected.");
                    break;
                case "5":
                    System.out.println("👉 View Reports selected.");
                    break;
                case "6":
                    System.out.println("👋 Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }
}
