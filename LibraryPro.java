import java.util.Scanner;

public class LibraryPro {

    // ✅ Declare these at the top (global variables)
    static String[][] books = new String[100][5]; // Book ID, Title, Author, Genre, Quantity
    static int bookCount = 0;


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
                    addBook(input);
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

    // Add Book method
public static void addBook(Scanner input) {
    System.out.println("\n--- 📘 Add New Book ---");

    System.out.print("Enter Book ID: ");
    String bookID = input.nextLine();

    // Check for duplicate Book ID
    for (int i = 0; i < bookCount; i++) {
        if (books[i][0].equals(bookID)) {
            System.out.println("❌ Book ID already exists. Try again.\n");
            return; // Stop the method here
        }
    }

    System.out.print("Enter Title: ");
    String title = input.nextLine();

    System.out.print("Enter Author: ");
    String author = input.nextLine();

    System.out.print("Enter Genre: ");
    String genre = input.nextLine();

    System.out.print("Enter Quantity: ");
    String quantity = input.nextLine();

    // Check if quantity is a valid number
    try {
        int q = Integer.parseInt(quantity);
        if (q <= 0) {
            System.out.println("❌ Quantity must be a positive number.\n");
            return;
        }
    } catch (Exception e) {
        System.out.println("❌ Quantity must be a valid number.\n");
        return;
    }

    // Store book in array
    books[bookCount][0] = bookID;
    books[bookCount][1] = title;
    books[bookCount][2] = author;
    books[bookCount][3] = genre;
    books[bookCount][4] = quantity;

    bookCount++; // Increase book count

    System.out.println("✅ Book added successfully!\n");
}

}
