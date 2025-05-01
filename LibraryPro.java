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
                    returnBook(input);
                    break;
                case "5":
                    viewReportsMenu(input);
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
        System.out.print("Enter Due Date (yyyy-MM-dd): ");
        String dueDate = input.nextLine();
        if (!isValidDateFormat(dueDate)) {
            System.out.println("❌ Invalid date format. Please use yyyy-MM-dd.\n");
            return;
        }
        books[bookIdx][4] = String.valueOf(qty - 1);
        issuedBooks[issuedCount][0] = bookID;
        issuedBooks[issuedCount][1] = memberID;
        issuedBooks[issuedCount][2] = dueDate;
        issuedCount++;
        System.out.println("✅ Book issued successfully! Due Date: " + dueDate + "\n");
    }

    // ===================== Return Book =====================
    public static void returnBook(Scanner input) {
        System.out.println("\n--- 📕 Return Book ---");
        System.out.print("Enter Member ID: ");
        String memberID = input.nextLine();
        int memberIdx = findMemberIndexByID(memberID);
        if (memberIdx == -1) {
            System.out.println("Invalid Member ID.\n");
            return;
        }
        System.out.print("Enter Book ID: ");
        String bookID = input.nextLine();
        int bookIdx = findBookIndexByID(bookID);
        if (bookIdx == -1) {
            System.out.println("Book ID does not exist.\n");
            return;
        }
        int issuedIdx = findIssuedBookIndex(bookID, memberID);
        if (issuedIdx == -1) {
            System.out.println("No record found for the given Member ID and Book ID.\n");
            return;
        }

        String dueDateStr = issuedBooks[issuedIdx][2];
        System.out.print("Enter current date (yyyy-MM-dd): ");
        String currentDateStr = input.nextLine();
        if (!isValidDateFormat(currentDateStr)) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.\n");
            return;
        }

        int dueDays = dateToDays(dueDateStr);
        int currentDays = dateToDays(currentDateStr);
        if (dueDays == -1 || currentDays == -1) {
            System.out.println("Error parsing dates. Cannot calculate fine.\n");
            return;
        }

        int overdueDays = currentDays - dueDays;
        int fine = 0;
        if (overdueDays > 0) {
            fine = overdueDays * 50; // 50 LKR per day
        }

        // Increase book quantity by 1
        int qty = Integer.parseInt(books[bookIdx][4]);
        books[bookIdx][4] = String.valueOf(qty + 1);

        // Remove issued book record by shifting
        for (int i = issuedIdx; i < issuedCount - 1; i++) {
            for (int j = 0; j < 3; j++) {
                issuedBooks[i][j] = issuedBooks[i + 1][j];
            }
        }
        for (int j = 0; j < 3; j++) issuedBooks[issuedCount - 1][j] = null;
        issuedCount--;

        System.out.println("Book returned successfully.");
        if (fine > 0) {
            System.out.println("Overdue fine: " + fine + " LKR (" + overdueDays + " days overdue)");
        } else {
            System.out.println("No overdue fine.");
        }
        System.out.println();
    }

    // ===================== View Reports =====================
    public static void viewReportsMenu(Scanner input) {
        while (true) {
            System.out.println("\n=== 📊 View Reports ===");
            System.out.println("1. Overdue Books");
            System.out.println("2. Books Issued Per Member");
            System.out.println("3. Back to Home");
            System.out.print("Select an option (1-3): ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    reportOverdueBooks(input);
                    break;
                case "2":
                    reportBooksIssuedPerMember();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }

    // Report 1: Overdue Books
    public static void reportOverdueBooks(Scanner input) {
        if (issuedCount == 0) {
            System.out.println("\nNo books are currently issued.\n");
            return;
        }
        System.out.print("Enter current date (yyyy-MM-dd) to check overdue books: ");
        String currentDateStr = input.nextLine();
        if (!isValidDateFormat(currentDateStr)) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.\n");
            return;
        }
        int currentDays = dateToDays(currentDateStr);
        if (currentDays == -1) {
            System.out.println("Error parsing current date.\n");
            return;
        }
        boolean foundOverdue = false;
        System.out.println("\n--- 📋 Overdue Books ---");
        System.out.printf("%-10s %-12s %-12s %-12s %-10s\n", "Book ID", "Member ID", "Due Date", "Days Overdue", "Fine (LKR)");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < issuedCount; i++) {
            String bookID = issuedBooks[i][0];
            String memberID = issuedBooks[i][1];
            String dueDateStr = issuedBooks[i][2];
            int dueDays = dateToDays(dueDateStr);
            if (dueDays == -1) continue;
            int overdueDays = currentDays - dueDays;
            if (overdueDays > 0) {
                foundOverdue = true;
                int fine = overdueDays * 50;
                System.out.printf("%-10s %-12s %-12s %-12d %-10d\n", bookID, memberID, dueDateStr, overdueDays, fine);
            }
        }
        if (!foundOverdue) {
            System.out.println("No overdue books found.\n");
        } else {
            System.out.println();
        }
    }

    // Report 2: Books Issued Per Member
    public static void reportBooksIssuedPerMember() {
        if (memberCount == 0) {
            System.out.println("\nNo members registered.\n");
            return;
        }
        if (issuedCount == 0) {
            System.out.println("\nNo books are currently issued.\n");
            return;
        }
        System.out.println("\n--- 📋 Books Issued Per Member ---");
        System.out.printf("%-12s %-25s %-10s\n", "Member ID", "Name", "Books Issued");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < memberCount; i++) {
            String memberID = members[i][0];
            String name = members[i][1];
            int count = 0;
            for (int j = 0; j < issuedCount; j++) {
                if (issuedBooks[j][1].equals(memberID)) {
                    count++;
                }
            }
            System.out.printf("%-12s %-25s %-10d\n", memberID, name, count);
        }
        System.out.println();
    }

    // ===================== Helpers =====================

    public static int findIssuedBookIndex(String bookID, String memberID) {
        for (int i = 0; i < issuedCount; i++) {
            if (issuedBooks[i][0].equals(bookID) && issuedBooks[i][1].equals(memberID)) {
                return i;
            }
        }
        return -1;
    }

    // Validate phone number (digits only, length 7-15)
    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{7,15}");
    }

    // Validate email format (simple regex)
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    // Validate date format yyyy-MM-dd (simple check)
    public static boolean isValidDateFormat(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Convert date yyyy-MM-dd to total days (approximate)
    public static int dateToDays(String date) {
        String[] parts = date.split("-");
        if (parts.length != 3) return -1;
        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            if (month < 1 || month > 12 || day < 1 || day > 31) return -1;
            return year * 365 + month * 30 + day;
        } catch (Exception e) {
            return -1;
        }
    }

    // Optional: Clear console method (not mandatory)
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
