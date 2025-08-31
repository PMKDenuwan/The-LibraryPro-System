import java.util.*;

class LibraryPro{
    static int bookCount = 0;
    static int memberCount = 0;
    //Array - Books
    static String[][] books = new String[5][0];
    //Array - Members
    static String[][] members = new String[4][0];
	//Array - Issued Books
	static String[][] issuedBooks = new String[3][0];

    // Check Book Id and add books
    public static void addBooks(Scanner input){
		clearConsole();
		String bookId, title, author, genre;
		int quantity;
		input.nextLine();
		System.out.print("Enter Book ID: ");
		bookId = input.nextLine();
		while(true){
			boolean found = false;
			for(int i=0;i<books[0].length;i++){
				if(books[0][i].equals(bookId)){
					found = true;
				}
			}
			if(found == true){
				System.out.println("This Book ID already exists!");
				System.out.print("Enter a new book ID: ");
				bookId = input.nextLine();
			}else{
				break;
			}
		}
		System.out.print("Enter Title: ");
		title = input.nextLine();
		System.out.print("Enter Author: ");
		author = input.nextLine();
		System.out.print("Enter Genre: ");
		genre = input.nextLine();
		System.out.print("Enter Quantity: ");
		quantity =input.nextInt();
		input.nextLine();
        // Check books quantity
		while(true){
			if(quantity<0){
				System.out.print("Please Enter valid quantity: ");
				quantity = input.nextInt();
				input.nextLine();
			}else{
				break;
			}
		}
        // Create a tempory array and copy values from book array 
		String[][] temp = new String[5][books[0].length+1];
		for (int j=0;j<books.length;j++){
			for(int i=0;i<books[j].length;i++){
				temp[j][i] = books[j][i];
			}
		}

        // Add values to the last elements
		temp[0][temp[0].length-1] = bookId;
		temp[1][temp[1].length-1] = title;
		temp[2][temp[2].length-1] = author;
		temp[3][temp[3].length-1] = genre;
		temp[4][temp[4].length-1] = Integer.toString(quantity);

        // Copy tempory array to the original book array
		books = temp;
		bookCount++;
		System.out.println("Book added successfully.\n");
	}

    // Updates Books
    public static void updateBooks(Scanner input){
		clearConsole();
		String bookId;
		int newQuantity;
		input.nextLine();
		System.out.print("Enter Book ID to update: ");
		bookId = input.next();

		// Find index of Entered book ID
		int index = -1;
		for(int i=0;i<books[0].length;i++){
			if(books[0][i].equals(bookId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("This Book doesn't exists!\n");
		}else{
			System.out.print("Enter new Title: ");
			books[1][index] = input.nextLine();
			System.out.print("Enter new Author: ");
			books[2][index] = input.nextLine();
			System.out.print("Enter new Genre: ");
			books[3][index] = input.nextLine();
			System.out.print("Enter new Quantity: ");
			newQuantity =input.nextInt();
			while(true){
				if(newQuantity<0){
					System.out.print("Please Enter valid quantity: ");
					newQuantity = input.nextInt();
				}else{
					break;
				}
			}
			books[4][index] = Integer.toString(newQuantity);
			System.out.println("Book updated successfully.\n");
		}
	}

    // Delete Books
    public static void deleteBooks(Scanner input){
		clearConsole();
		String bookId;
		input.nextLine();
		System.out.print("Enter Book ID to delete: ");
		bookId = input.next();

		// Find index of Entered book ID
		int index = -1;
		for(int i=0;i<books[0].length;i++){
			if(books[0][i].equals(bookId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("This Book doesn't exists!\n");
		}else{
			String[][] temp = new String[5][books[0].length-1];
			for(int j=0;j<books.length;j++){
				for(int i=0;i<temp[j].length;i++){
					if(i >= index){
						temp[j][i] = books[j][i+1];
					}else{
						temp[j][i] = books[j][i];
					}
				}
			}
			books = temp;
            bookCount--;
			System.out.println("Book deleted successfully.\n");
		}
	}

    // Find Books
    public static void searchBooks(Scanner input){
		clearConsole();
		String bookId;
		input.nextLine();
		System.out.print("Enter Book ID to search: ");
		bookId = input.next();

		// Find index of Entered book ID
		int index = -1;
		for(int i=0;i<books[0].length;i++){
			if(books[0][i].equals(bookId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("Book not found.\n");
		}else{
			System.out.println("\nBook Details:");
			System.out.println("ID: " + books[0][index]);
			System.out.println("Title: " + books[1][index]);
			System.out.println("Author: " + books[2][index]);
			System.out.println("Genre: " + books[3][index]);
			System.out.println("Quantity: " + books[4][index] + "\n");
		}
	}

    // View All Books
    public static void viewAllBooks(){
		clearConsole();
		if(bookCount == 0){
			System.out.println("Catalog is empty.\n");
		}else{
			System.out.println("\t\t\t\t\t--- All Books ---");
			System.out.println("Book ID\t\t\tTitle\t\t\tAuthor\t\t\tGenre\t\t\tQuantity");
			System.out.println();
			for(int j=0;j<books[0].length;j++){
				for(int i=0;i<books.length;i++){
					System.out.print(books[i][j]+"\t\t\t");
				}
				System.out.println();
			}
		}
	}

    // Check Member Id and add members
    public static void addMembers(Scanner input){
		clearConsole();
		String memberId, name, contactNumber, email;
		input.nextLine();
		System.out.print("Enter member ID: ");
		memberId = input.nextLine();
		while(true){
			boolean found = false;
			for(int i=0;i<members[0].length;i++){
				if(members[0][i].equals(memberId)){
					found = true;
				}
			}
			if(found == true){
				System.out.println("This member ID already exists!");
				System.out.print("Enter a new member ID: ");
				memberId = input.nextLine();
			}else{
				break;
			}
		}
		System.out.print("Enter Name: ");
		name = input.nextLine();
		
		// Check phone number
		while(true){
			System.out.print("Enter Contact Number: ");
			String temp = input.nextLine();
			boolean isDigit = true;
			if (temp.length() != 10){
				System.out.println("Please enter valid phone number.");
			}else{
				for (int i = 0; i < temp.length(); i++) {
					if (!Character.isDigit(temp.charAt(i))) {
						isDigit = false;
					}
				}
				if(isDigit){
					contactNumber = temp;
					break;
				}else{
					System.out.println("Please Enter valid phone number.");
				}
			}
		}
		// Check email
		while(true){
			System.out.print("Enter Email Address: ");
			String temp = input.nextLine();
			if(temp.contains("@") && temp.contains(".") && temp.indexOf("@") < temp.lastIndexOf(".")){
				email = temp;
				break;
			}else{
				System.out.println("Please Enter valid e-mail address.");
			}
		}
		
        // Create a tempory array and copy values from member array 
		String[][] temp = new String[4][members[0].length+1];
		for (int j=0;j<members.length;j++){
			for(int i=0;i<members[j].length;i++){
				temp[j][i] = members[j][i];
			}
		}

        // Add values to the last elements
		temp[0][temp[0].length-1] = memberId;
		temp[1][temp[1].length-1] = name;
		temp[2][temp[2].length-1] = contactNumber;
		temp[3][temp[3].length-1] = email;

        // Copy tempory array to the original members array
		members = temp;
		memberCount++;
		System.out.println("Member added successfully.\n");
	}

    // Update Members
    public static void updateMembers(Scanner input){
		clearConsole();
		String memberId;
		input.nextLine();
		System.out.print("Enter Member ID to update: ");
		memberId = input.next();

		// Find index of Entered member ID
		int index = -1;
		for(int i=0;i<members[0].length;i++){
			if(members[0][i].equals(memberId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("Member not found.\n");
		}else{
			System.out.print("Enter new Name: ");
			members[1][index] = input.nextLine();

			// Check contact number
			while(true){
				boolean isDigit = true;
				System.out.print("Enter new Contact Number: ");
				String tempC = input.nextLine();
				if(tempC.length() != 10){
					System.out.println("Please enter valid phone number");
				}else{
					for (int i = 0; i < tempC.length(); i++) {
						if (!Character.isDigit(tempC.charAt(i))) {
							isDigit = false;
						}
					}
					if(isDigit){
						members[2][index] = tempC;
						break;
					}else{
						System.out.println("Please enter valid phone number");
					}
				}
			}
			// Check E-mail address
			while(true){
				System.out.print("Enter new Email: ");
				String tempE = input.nextLine();
				if(tempE.contains("@") && tempE.contains(".") && tempE.indexOf("@") < tempE.lastIndexOf(".")){
					members[3][index] = tempE;
					break;
				}else{
					System.out.println("Please enter valid e-mail address.");
				}
			}
			System.out.println("Member updated successfully.\n");
		}
	}

    // Delete Members
    public static void deleteMembers(Scanner input){
		clearConsole();
		String memberId;
		input.nextLine();
		System.out.print("Enter member ID to delete: ");
		memberId = input.next();

		// Find index of Entered member ID
		int index = -1;
		for(int i=0;i<members[0].length;i++){
			if(members[0][i].equals(memberId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("Member not found.\n");
		}else{
			String[][] temp = new String[5][members[0].length-1];
			for(int j=0;j<members.length;j++){
				for(int i=0;i<temp[j].length;i++){
					if(i >= index){
						temp[j][i] = members[j][i+1];
					}else{
						temp[j][i] = members[j][i];
					}
				}
			}
			members = temp;
            memberCount--;
			System.out.println("Member deleted successfully.\n");
		}
	}

    // Find members
    public static void searchMembers(Scanner input){
		clearConsole();
		String memberId;
		input.nextLine();
		System.out.print("Enter member ID to search: ");
		memberId = input.next();

		// Find index of Entered member ID
		int index = -1;
		for(int i=0;i<members[0].length;i++){
			if(members[0][i].equals(memberId)){
				index = i;
			}
		}
		if(index < 0){
			System.out.println("Member not found.\n");
		}else{
			System.out.println("\nMember Details:");
			System.out.println("ID: " + members[0][index]);
			System.out.println("Name: " + members[1][index]);
			System.out.println("Contact: " + members[2][index]);
			System.out.println("Email: " + members[3][index] + "\n");
		}
	}

    // View All Members
    public static void viewAllMembers(){
		clearConsole();
		if(memberCount == 0){
			System.out.println("Catalog is empty.\n");
		}else{
			System.out.println("\t\t\t\t\t--- All Members ---");
			System.out.println("Member ID\t\t\tName\t\t\tContact Number\t\t\tEmail");
			System.out.println();
			for(int j=0;j<members[0].length;j++){
				for(int i=0;i<members.length;i++){
					System.out.print(members[i][j]+"\t\t\t");
				}
				System.out.println();
			}
		}
	}

	// Issue Books
	public static void issueBooks(Scanner input){
		clearConsole();
		while(true){
			int bookIndex = 0;
			System.out.print("Enter member ID: ");
			String memberId = input.next();
			boolean found = false;
			for(int i=0;i<members[0].length;i++){
				if(members[0][i].equals(memberId)){
					found = true;
				}
			}
			if(found){
				System.out.print("Enter book ID: ");
				String bookId = input.next();
				boolean foundBook = false;
				for(int j=0;j<books[0].length;j++){
					if(books[0][j].equals(bookId)){
						foundBook = true;
						bookIndex = j;
					}
				}
				if(foundBook){
					if(Integer.parseInt(books[4][bookIndex])>0){
						input.nextLine();
						System.out.print("Enter Due Date (YYYY-MM-DD): ");
						String tempDueDate = input.nextLine();
						String dueDate;
						if(tempDueDate.contains("-") && tempDueDate.charAt(7) == '-' && tempDueDate.length() == 10){
							dueDate = tempDueDate;
						}else{
							System.out.println("Enter due date in valid format.\n");
							continue;
						}
						int quantity = Integer.parseInt(books[4][bookIndex]);
						quantity-=1;
						books[4][bookIndex] = Integer.toString(quantity);
						String[][] temp = new String[3][issuedBooks[0].length+1];
						for(int i=0;i<temp.length;i++){
							for(int j=0;j<issuedBooks[0].length;j++){
								temp[i][j] = issuedBooks[i][j];
							}
						}
						temp[0][temp[0].length-1] = memberId;
						temp[1][temp[0].length-1] = bookId;
						temp[2][temp[0].length-1] = dueDate;
						issuedBooks = temp;
						clearConsole();
						System.out.println("Book issued successfully.");
						System.out.println("1. Issue Another Book");
						System.out.println("2. Back to Home");
						System.out.print("Select an option: ");
						int option = input.nextInt();
						input.nextLine();
						switch (option) {
							case 1:
								clearConsole();
								break;
							case 2:
								clearConsole();
								return;
							default:
								clearConsole();
								break;
						}
					}else{
						clearConsole();
						System.out.println("This book not available!");
						System.out.println("1. Try Again");
						System.out.println("2. Back to Home");
						System.out.print("Select an option: ");
						int option = input.nextInt();
						input.nextLine();
						switch (option) {
							case 1:
								clearConsole();
								break;
							case 2:
								clearConsole();
								return;
							default:
								clearConsole();
								break;
						}
					}
				}else{
					clearConsole();
					System.out.println("Invalid Book ID.");
					System.out.println("1. Try Again");
					System.out.println("2. Back to Home");
					System.out.print("Select an option: ");
					int option = input.nextInt();
					switch (option) {
						case 1:
							clearConsole();
							break;
						case 2:
							clearConsole();
							return;
						default:
							clearConsole();
							break;
					}
				}
			}else{
				clearConsole();
				System.out.println("Invalid Member ID.");
				System.out.println("1. Try Again");
				System.out.println("2. Back to Home");
				System.out.print("Select an option: ");
				int option = input.nextInt();
				switch (option) {
					case 1:
						clearConsole();
						break;
					case 2:
						clearConsole();
						return;
					default:
						clearConsole();
						break;
				}
			}
		}
	}

	// Return Books
	public static void returnBooks(Scanner input){
		clearConsole();
		if(issuedBooks[0].length !=0){
		while(true){
			System.out.print("Enter Member ID: ");
			int indexBooksArray = 0;
			int indexIssuedBooksArray = 0;
			String memberId = input.next();
			boolean foundMemberId = false;
			for(int i=0;i<members[0].length;i++){
				if(members[0][i].equals(memberId)){
					foundMemberId = true;
				}
			}
			if(foundMemberId){
				
				System.out.print("Enter Book ID: ");
				String bookId = input.next();
				boolean foundBookId = false;
				for(int i=0;i<books[0].length;i++){
					if(books[0][i].equals(bookId)){
						indexBooksArray = i;
						foundBookId = true;
					}
				}
				if(foundBookId){
					boolean found = false;
					for(int i=0;i<issuedBooks[0].length;i++){
						if(issuedBooks[0][i].equals(memberId) && issuedBooks[1][i].equals(bookId)){
							found = true;
							indexIssuedBooksArray = i;
						}
					}
					if(found){
						int quantity = Integer.parseInt(books[4][indexBooksArray]);
						quantity+=1;
						books[4][indexBooksArray] = Integer.toString(quantity);
						String[][] temp = new String[3][issuedBooks[0].length-1];
						for(int j=0;j<temp.length;j++){
							for(int i=0;i<temp[0].length;i++){
								if(i < indexIssuedBooksArray){
									temp[j][i] = issuedBooks[j][i];
								}else{
									temp[j][i] = issuedBooks[j][i+1];
								}
							}
						}
						issuedBooks = temp;
						System.out.println("Book returned successfully.");
						System.out.println("1. Return Another Book");
						System.out.println("2. Back to Home");
						System.out.print("Select an option: ");
						int option = input.nextInt();
						input.nextLine();
						switch (option) {
							case 1:
								clearConsole();
								break;
							case 2:
								clearConsole();
								return;
							default :
								clearConsole();
								break;
						}
					}else{
						System.out.println("No record found for the given Member ID and BookID.\n");
						break;
					}
				}else{
					System.out.println("Invalid Book ID");
				}
			}else{
				System.out.println("Invalid Member ID");
			}
		}	
		}else{
			System.out.println("No issued books.\n");
			return;
		}
	}

	// Overdue Books
	public static void overDueBooks(Scanner input){
		clearConsole();
		//Array - Overdue Books
		String[][] overDueBooks = new String[5][0];
		while(true){
			System.out.print("Enter date of today (YYYY-MM-DD): ");
			String dateToday = input.nextLine();
			if(dateToday.contains("-") && dateToday.charAt(7) == '-' && dateToday.length() == 10){
				// Get today month
				char tm1 = dateToday.charAt(5);
				char tm2 = dateToday.charAt(6);
				String tm = ""+tm1+tm2;
				int todayMonth = Integer.parseInt(tm);

				// Get today date
				char td1 = dateToday.charAt(8);
				char td2 = dateToday.charAt(9);
				String td = ""+td1+td2;
				int today = Integer.parseInt(td);

				int daysOverdue = 0;
				int overDueBookCount = 0;

				for(int i=0;i<issuedBooks[0].length;i++){
					String dueDate = issuedBooks[2][i];

					// Get overdue month
					char dm1 = dueDate.charAt(5);
					char dm2 = dueDate.charAt(6);
					String dm = ""+dm1+dm2;
					int monthDue = Integer.parseInt(dm);

					// Get overdue date
					char dd1 = dueDate.charAt(8);
					char dd2 = dueDate.charAt(9);
					String dd = ""+dd1+dd2;
					int dateDue = Integer.parseInt(dd);

					int monthOverdue;
					if(today<dateDue){
						todayMonth-=1;
						today += 30;
						daysOverdue = today-dateDue;
						monthOverdue = todayMonth - monthDue;
						daysOverdue = daysOverdue + monthOverdue*30;
					}else{
						daysOverdue = today-dateDue;
						monthOverdue = todayMonth - monthDue;
						daysOverdue = daysOverdue + monthOverdue*30;
					}
					if(daysOverdue > 0 ){
						String memberId = issuedBooks[0][i];
						String bookId = issuedBooks[1][i];
						double fineAmount = daysOverdue * 50;
						
						String[][] temp = new String[5][overDueBooks[0].length+1];
						for(int j=0;j<overDueBooks.length;j++){
							for(int k=0;k<overDueBooks[0].length;k++){
								temp[j][k] = overDueBooks[j][k];
							}
						}
						temp[0][temp[0].length-1] = bookId;
						temp[1][temp[0].length-1] = memberId;
						temp[2][temp[0].length-1] = dueDate;
						temp[3][temp[0].length-1] = Integer.toString(daysOverdue);
						temp[4][temp[0].length-1] = Double.toString(fineAmount);
						overDueBooks = temp;
						overDueBookCount++;
					}
				}
				if(overDueBookCount !=0 ){
					System.out.println("BookID\t\tMember Id\tDue date\tDays overdue\t\tFine");
					for(int i=0;i<overDueBooks[0].length;i++){
						for(int j=0;j<overDueBooks.length;j++){
							System.out.print(overDueBooks[j][i]+"\t\t");
						}
						System.out.println("\b\b\b\b\b\b\b\b\bLKR");
					}
					return;
				}else{
					clearConsole();
					System.out.println("No overdue books.\n");
					return;
				}
			}else{
				System.out.println("Enter date in valid format.\n");
				continue;
			}
		}
	}

	// Book Issued Per Member
	public static void booksIssuedPerMember(){
		clearConsole();
		//Arrays - booksIssuedPerMember
		String[] uniqueMember = new String[issuedBooks[0].length];
		int[] uniqueBookCount = new int[issuedBooks[0].length];
		if(issuedBooks[0].length == 0){
			System.out.println("No issued books.\n");
		}else{
			int uniqueCount = 0;
			for(int i=0;i<issuedBooks[0].length;i++){
				boolean found = false;
				int index = 0;
				for(int j=0;j<uniqueCount;j++){
					if(issuedBooks[0][i].equals(uniqueMember[j])){
						found = true;
						index = j;
					}
				}
				if(!found){
					uniqueMember[uniqueCount] = issuedBooks[0][i];
					uniqueBookCount[uniqueCount] = 1;
					uniqueCount++;
					
				}else{
					uniqueBookCount[index]++;
				}
			}
			System.out.println("MemberID\tBook Count");
			for(int i=0;i<uniqueCount;i++){
				System.out.print(uniqueMember[i] + "\t\t");
				System.out.println(uniqueBookCount[i]);
			}
		}
	}

    // Clear console
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
        // Handle the exception
        System.err.println(e.getMessage());
        }
       }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Username and password for login to the system
        final String username = "admin";
        final String password = "admin123";

        // Enter Username and Password for Login to the System
        while(true){
            String loginUsername;
            String loginPassword;
            System.out.print("Enter Username: ");
            loginUsername = input.nextLine();
            System.out.print("Enter Password: ");
            loginPassword = input.nextLine();
    
            // Check Credentials
            if(loginUsername.equals(username) && loginPassword.equals(password)){
                break;
            }else{
                System.out.println("Invalid credentials. Try again.");
                continue;
            }
        }
        clearConsole();
        // Home page
        while(true){
            System.out.println("--- Library Management System ---");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. view Reports");
            System.out.println("6. Exit System");
            System.out.print("Select an option: ");
            int optionHomePage = input.nextInt();

            switch (optionHomePage) {
                case 1:
                    // Manage Books
                    clearConsole();
                    while(true){
                        System.out.println("--- Manage Books ---");
                        System.out.println("1. Add Book");
                        System.out.println("2. Update Book");
                        System.out.println("3. Delete Book");
                        System.out.println("4. Search Book");
                        System.out.println("5. View All Books");
                        System.out.println("6. Back to Home");
                        System.out.print("Select an option: ");
                        int optionManageBooks = input.nextInt();

                        switch (optionManageBooks) {
                        case 1:
                            // Add Books
                            addBooks(input);
                            break;
                        case 2:
                            // Update Books
                            updateBooks(input);
                            break;
                        case 3:
                            // Delete Books
                            deleteBooks(input);
                            break;
                        case 4:
                            // Find Books
                            searchBooks(input);
                            break;
                        case 5:
                            // View All Books
                            viewAllBooks();
                            break;
                        case 6:
                            // Back to Home
                            clearConsole();
                            break;
                        default:
                            clearConsole();
                            System.out.println("Enter valid number");
                            break;
                        }
                        if(optionManageBooks == 6){
                            break;
                        }
                    }
                    break;
                case 2:
                    // Manage Members
                    clearConsole();
                    while(true){
                        System.out.println("--- Manage Members ---");
                        System.out.println("1. Add Member");
                        System.out.println("2. Update Member");
                        System.out.println("3. Delete Member");
                        System.out.println("4. Search Member");
                        System.out.println("5. View All Member");
                        System.out.println("6. Back to Home");
                        System.out.print("Select an option: ");
                        int optionManageMembers = input.nextInt();

                        switch (optionManageMembers) {
                        case 1:
                            // Add Members
                            addMembers(input);
                            break;
                        case 2:
                            // Update Books
                            updateMembers(input);
                            break;
                        case 3:
                            // Delete Books
                            deleteMembers(input);
                            break;
                        case 4:
                            // Find Books
                            searchMembers(input);
                            break;
                        case 5:
                            // View All Books
                            viewAllMembers();
                            break;
                        case 6:
                            // Back to Home
                            clearConsole();
                            break;
                        default:
                            clearConsole();
                            System.out.println("Enter valid number");
                            break;
                        }
                        if(optionManageMembers == 6){
                            break;
                        }
                    }
                    break;
                case 3:
					// Issue Books
					issueBooks(input);
                    break;
                case 4:
					// Return Books
					returnBooks(input);
                    break;
                case 5:
					// Reports
					System.out.println("\n--- Reports ---");
					System.out.println("1. Overdue Books");
					System.out.println("2. Books Issued Per Member");
					System.out.print("Select an option: ");
					int option = input.nextInt();
					input.nextLine();
					switch (option) {
						case 1:
							// Overdue Books
							overDueBooks(input);
							break;
						case 2:
							// Book Issued Per Member
							booksIssuedPerMember();
							break;
						default:
							System.out.println("\nEnter valid number.\n");
							break;
					}
                    break;
                case 6:
                    clearConsole();
                    break;
                default :
                    clearConsole();
                    System.out.println("Enter valid number");
                    break;
            }
            if(optionHomePage == 6){
                break;
            }
        }
        input.close();
    }
}