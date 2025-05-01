import java.util.Scanner;
import java.util.regex.Pattern;

public class LibraryPro {

    // Book: [Book ID, Title, Author, Genre, Quantity]
    static String[][] books = new String[100][5];
    static int bookCount = 0;

    // Member: [Member ID, Name, Contact Number, Email]
    static String[][] members = new String[100][4];
    static int memberCount = 0;

    // Issued Book: [Book ID, Member ID, Due Date]
    static String[][] issuedBooks = new String[100][3];
    static int issuedCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        login(input);
        showHomeMenu(input);
    }

    // Login method
    public static void login(Scanner input) {
        String USERNAME = "admin";
        String PASSWORD = "1234";
        while (true) {
            System.out.print("Enter Username: ");
            String user = input.nextLine();
            System.out.print("Enter Password: ");
            String pass = input.nextLine();
            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Wrong username or password. Try again.\n");
            }
        }
    }

    // Home Menu
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
                    manageBooksMenu(input);
                    break;
                case "2":
                    manageMembersMenu(input);
                    break;
                case "3":
                    issueBook(input);
                    break;
                case "4":
                    System.out.println("👉 Return Books selected. (Not implemented)");
                    break;
                case "5":
                    System.out.println("👉 View Reports selected. (Not implemented)");
                    break;
                case "6":
                    System.out.println("👋 Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }

    // ===================== Manage Books =====================
    public static void manageBooksMenu(Scanner input) {
        while (true) {
            System.out.println("\n=== 📚 Manage Books ===");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. View All Books");
            System.out.println("6. Back to Home");
            System.out.print("Select an option (1-6): ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addBook(input);
                    break;
                case "2":
                    updateBook(input);
                    break;
                case "3":
                    deleteBook(input);
                    break;
                case "4":
                    searchBook(input);
                    break;
                case "5":
                    viewAllBooks();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }

    public static void addBook(Scanner input) {
        System.out.println("\n--- 📘 Add New Book ---");
        System.out.print("Enter Book ID: ");
        String bookID = input.nextLine();
        if (findBookIndexByID(bookID) != -1) {
            System.out.println("❌ Book ID already exists. Try again.\n");
            return;
        }
        System.out.print("Enter Title: ");
        String title = input.nextLine();
        System.out.print("Enter Author: ");
        String author = input.nextLine();
        System.out.print("Enter Genre: ");
        String genre = input.nextLine();
        System.out.print("Enter Quantity: ");
        String quantity = input.nextLine();
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
        books[bookCount][0] = bookID;
        books[bookCount][1] = title;
        books[bookCount][2] = author;
        books[bookCount][3] = genre;
        books[bookCount][4] = quantity;
        bookCount++;
        System.out.println("✅ Book added successfully!\n");
    }

    public static void updateBook(Scanner input) {
        System.out.println("\n--- ✏️ Update Book ---");
        System.out.print("Enter Book ID to update: ");
        String bookID = input.nextLine();
        int index = findBookIndexByID(bookID);
        if (index == -1) {
            System.out.println("❌ Book ID not found. Returning to menu.\n");
            return;
        }
        System.out.println("Current details:");
        System.out.println("Book ID: " + books[index][0]);
        System.out.println("Title: " + books[index][1]);
        System.out.println("Author: " + books[index][2]);
        System.out.println("Genre: " + books[index][3]);
        System.out.println("Quantity: " + books[index][4]);
        System.out.print("Enter new Title: ");
        String title = input.nextLine();
        System.out.print("Enter new Author: ");
        String author = input.nextLine();
        System.out.print("Enter new Genre: ");
        String genre = input.nextLine();
        System.out.print("Enter new Quantity: ");
        String quantity = input.nextLine();
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
        books[index][1] = title;
        books[index][2] = author;
        books[index][3] = genre;
        books[index][4] = quantity;
        System.out.println("✅ Book updated successfully!\n");
    }

    public static void deleteBook(Scanner input) {
        System.out.println("\n--- 🗑️ Delete Book ---");
        System.out.print("Enter Book ID to delete: ");
        String bookID = input.nextLine();
        int index = findBookIndexByID(bookID);
        if (index == -1) {
            System.out.println("❌ Book ID not found. Returning to menu.\n");
            return;
        }
        for (int i = index; i < bookCount - 1; i++) {
            for (int j = 0; j < 5; j++) {
                books[i][j] = books[i + 1][j];
            }
        }
        for (int j = 0; j < 5; j++) books[bookCount - 1][j] = null;
        bookCount--;
        System.out.println("✅ Book deleted successfully!\n");
    }

    public static void searchBook(Scanner input) {
        System.out.println("\n--- 🔍 Search Book ---");
        System.out.print("Enter Book ID to search: ");
        String bookID = input.nextLine();
        int index = findBookIndexByID(bookID);
        if (index == -1) {
            System.out.println("❌ Book ID not found.\n");
            return;
        }
        System.out.println("Book found:");
        System.out.println("Book ID: " + books[index][0]);
        System.out.println("Title: " + books[index][1]);
        System.out.println("Author: " + books[index][2]);
        System.out.println("Genre: " + books[index][3]);
        System.out.println("Quantity: " + books[index][4]);
        System.out.println();
    }

    public static void viewAllBooks() {
        System.out.println("\n--- 📚 All Books in Catalog ---");
        if (bookCount == 0) {
            System.out.println("No books in the catalog.\n");
            return;
        }
        System.out.printf("%-10s %-25s %-20s %-15s %-8s\n", "Book ID", "Title", "Author", "Genre", "Quantity");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < bookCount; i++) {
            System.out.printf("%-10s %-25s %-20s %-15s %-8s\n",
                    books[i][0], books[i][1], books[i][2], books[i][3], books[i][4]);
        }
        System.out.println();
    }

    public static int findBookIndexByID(String bookID) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i][0].equals(bookID)) return i;
        }
        return -1;
    }

    // ===================== Manage Members =====================
    public static void manageMembersMenu(Scanner input) {
        while (true) {
            System.out.println("\n=== 👥 Manage Members ===");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Search Member");
            System.out.println("5. View All Members");
            System.out.println("6. Back to Home");
            System.out.print("Select an option (1-6): ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addMember(input);
                    break;
                case "2":
                    updateMember(input);
                    break;
                case "3":
                    deleteMember(input);
                    break;
                case "4":
                    searchMember(input);
                    break;
                case "5":
                    viewAllMembers();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }

    public static void addMember(Scanner input) {
        System.out.println("\n--- 🆕 Add New Member ---");
        String memberID;
        while (true) {
            System.out.print("Enter Member ID: ");
            memberID = input.nextLine();
            if (findMemberIndexByID(memberID) != -1) {
                System.out.println("❌ Member ID already exists. Please enter a unique Member ID.");
            } else {
                break;
            }
        }
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        String contactNumber;
        while (true) {
            System.out.print("Enter Contact Number: ");
            contactNumber = input.nextLine();
            if (isValidPhoneNumber(contactNumber)) break;
            else System.out.println("❌ Invalid phone number format. Please enter a valid phone number.");
        }
        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = input.nextLine();
            if (isValidEmail(email)) break;
            else System.out.println("❌ Invalid email format. Please enter a valid email address.");
        }
        members[memberCount][0] = memberID;
        members[memberCount][1] = name;
        members[memberCount][2] = contactNumber;
        members[memberCount][3] = email;
        memberCount++;
        System.out.println("✅ Member added successfully!\n");
    }

    public static void updateMember(Scanner input) {
        System.out.println("\n--- ✏️ Update Member ---");
        System.out.print("Enter Member ID to update: ");
        String memberID = input.nextLine();
        int index = findMemberIndexByID(memberID);
        if (index == -1) {
            System.out.println("❌ Member ID not found. Returning to menu.\n");
            return;
        }
        System.out.println("Current details:");
        System.out.println("Member ID: " + members[index][0]);
        System.out.println("Name: " + members[index][1]);
        System.out.println("Contact Number: " + members[index][2]);
        System.out.println("Email: " + members[index][3]);
        System.out.print("Enter new Name: ");
        String name = input.nextLine();
        String contactNumber;
        while (true) {
            System.out.print("Enter new Contact Number: ");
            contactNumber = input.nextLine();
            if (isValidPhoneNumber(contactNumber)) break;
            else System.out.println("❌ Invalid phone number format. Please enter a valid phone number.");
        }
        String email;
        while (true) {
            System.out.print("Enter new Email: ");
            email = input.nextLine();
            if (isValidEmail(email)) break;
            else System.out.println("❌ Invalid email format. Please enter a valid email address.");
        }
        members[index][1] = name;
        members[index][2] = contactNumber;
        members[index][3] = email;
        System.out.println("✅ Member updated successfully!\n");
    }

    public static void deleteMember(Scanner input) {
        System.out.println("\n--- 🗑️ Delete Member ---");
        System.out.print("Enter Member ID to delete: ");
        String memberID = input.nextLine();
        int index = findMemberIndexByID(memberID);
        if (index == -1) {
            System.out.println("❌ Member ID not found. Returning to menu.\n");
            return;
        }
        for (int i = index; i < memberCount - 1; i++) {
            for (int j = 0; j < 4; j++) {
                members[i][j] = members[i + 1][j];
            }
        }
        for (int j = 0; j < 4; j++) members[memberCount - 1][j] = null;
        memberCount--;
        System.out.println("✅ Member deleted successfully!\n");
    }

    public static void searchMember(Scanner input) {
        System.out.println("\n--- 🔍 Search Member ---");
        System.out.print("Enter Member ID to search: ");
        String memberID = input.nextLine();
        int index = findMemberIndexByID(memberID);
        if (index == -1) {
            System.out.println("❌ Member ID not found.\n");
            return;
        }
        System.out.println("Member found:");
        System.out.println("Member ID: " + members[index][0]);
        System.out.println("Name: " + members[index][1]);
        System.out.println("Contact Number: " + members[index][2]);
        System.out.println("Email: " + members[index][3]);
        System.out.println();
    }

    public static void viewAllMembers() {
        System.out.println("\n--- 👥 All Registered Members ---");
        if (memberCount == 0) {
            System.out.println("No members registered in the system.\n");
            return;
        }
        System.out.printf("%-12s %-25s %-15s %-30s\n", "Member ID", "Name", "Contact Number", "Email");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < memberCount; i++) {
            System.out.printf("%-12s %-25s %-15s %-30s\n",
                    members[i][0], members[i][1], members[i][2], members[i][3]);
        }
        System.out.println();
    }

    public static int findMemberIndexByID(String memberID) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i][0].equals(memberID)) return i;
        }
        return -1;
    }

    // ===================== Issue Book =====================
    public static void issueBook(Scanner input) {
        System.out.println("\n--- 📖 Issue Book ---");
        System.out.print("Enter Member ID: ");
        String memberID = input.nextLine();
        int memberIdx = findMemberIndexByID(memberID);
        if (memberIdx == -1) {
            System.out.println("❌ Member ID does not exist. Cannot issue book.\n");
            return;
        }
        System.out.print("Enter Book ID: ");
        String bookID = input.nextLine();
        int bookIdx = findBookIndexByID(bookID);
        if (bookIdx == -1) {
            System.out.println("❌ Book ID does not exist. Cannot issue book.\n");
            return;
        }
        int qty = Integer.parseInt(books[bookIdx][4]);
        if (qty <= 0) {
            System.out.println("❌ Book is not available for issue (quantity is 0).\n");
            return;
        }
        System.out.print("Enter Due Date (e.g., 2025-05-10): ");
        String dueDate = input.nextLine();
        // Reduce quantity by 1
        books[bookIdx][4] = String.valueOf(qty - 1);
        // Record issuance
        issuedBooks[issuedCount][0] = bookID;
        issuedBooks[issuedCount][1] = memberID;
        issuedBooks[issuedCount][2] = dueDate;
        issuedCount++;
        System.out.println("✅ Book issued successfully! Due Date: " + dueDate + "\n");
    }

    // ===================== Validation Helpers =====================
    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{7,15}");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    // ===================== Console Clear Helper =====================
    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Linux")) {
                System.out.print("\033\143");
            } else if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
