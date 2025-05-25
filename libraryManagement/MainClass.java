package libraryManagement;

import java.util.Scanner;

public class MainClass {

    private static final int ADMIN_PASSWORD = 1234;
    private Scanner scanner;
    Store store;

    public MainClass() {
        scanner = new Scanner(System.in);
        store = new Store();
    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
        DataList data = new DataList(main.store);
        data.addDemoUser();
        data.addDemoBook();
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|                                                                                                    |");

        System.out.println(
                "|                                   WELCOME TO E-LIBRARY                                             |");
        System.out.println(
                "|                                                                                                    |");

        System.out.println(
                "------------------------------------------------------------------------------------------------------");

        while (true) {
            main.adminMenu();
        }
    }

    private void adminMenu() {
        System.out.print("Enter password to login: ");
        int pwdAdminEntered = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (ADMIN_PASSWORD != pwdAdminEntered) {
            System.out.println("Invalid Password");
            return;
        }

        System.out.println("You now have access to E-Library Database");

        while (true) {
            System.out.print("Enter 1 to select options and enter any other number to exit Admin: ");
            int userEntry = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (userEntry != 1) {
                break;
            }
            System.out.println(
                    "Options:\n 1. Add User \n 2. Delete Uer \n 3. Add Book\n 4. Delete Book\n 5. Issue Book \n 6. Return Book  \n 7. Display Books\n 8. Display Borrow record \n 9.Display Users \n Exit Admin");
            System.out.print("Enter your Option Preference: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    displayBooks();
                    break;
                case 8:
                    displayBorrowRecords();
                    break;

                case 9:
                    displayUsers();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    void addUser() {
        System.out.println("Enter userId");
        String userId = scanner.nextLine();
        System.out.println("Enter user Name");
        String userName = scanner.nextLine();
        User user = new User(userId, userName);
        if (store.addUser(user)) {
            System.out.println("User Added Successfully");
        } else {
            System.out.println("Opration Rejected");
        }
    }

    void addBook() {
        System.out.println("Enter bookId");
        String bookId = scanner.nextLine();
        System.out.println("Enter bookTitle");
        String title = scanner.nextLine();
        System.out.println("Enter no of  total copies");
        int totalCopies = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter shelf Number");
        int shelfNo = scanner.nextInt();
        scanner.nextLine();
        if (store.addBook(bookId, title, totalCopies,shelfNo)) {
            System.out.println("Book Added Successfully");
        } else {
            System.out.println("Opration Rejected");
        }
    }

    void removeUser() {
        System.out.println("Enter userId");
        String userId = scanner.nextLine();

        if (store.removeUser(userId)) {
            System.out.println("User Removed Successfully");
        } else {
            System.out.println("Opration Rejected");
        }
    }

    void removeBook() {
        System.out.println("Enter userId");
        String bookId = scanner.nextLine();

        if (store.removeBook(bookId)) {
            System.out.println("Book Removed Successfully");
        } else {
            System.out.println("Opration Rejected");
        }
    }

    void issueBook() {
        System.out.println("Enter userId");
        String userId = scanner.nextLine();
        System.out.println("Enter BookId");
        String bookId = scanner.nextLine();
        System.out.println("Enter Duration");
        int duration = scanner.nextInt();
        scanner.nextLine();
        String message = new BorrowIfAllowed(userId, bookId, duration, store).borrow();
        System.out.println(message);
    }

    void returnBook() {
        System.out.println("Enter BorrowId");
        int borrowId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Duration");
        int duration = scanner.nextInt();
        scanner.nextLine();
        new ReturnBook(borrowId, duration, store).rBook();

    }

    void displayUsers() {
        for (User user : store.getAllUsers()) {
            System.out.println(user.userId + "            " + user.userName + "     " + user.userStatus.name());
        }
    }

    void displayBooks() {
        for (BookDetail book : store.getAllBooks()) {
            System.out.println(book.bookId + "                      " + book.bookTitle
                    + "                total Copies:  " + book.totalCopies
                    + "                Available Copies: " + book.availCopies + "    shelfNo: " + book.shelfNo);
        }
    }

    void displayBorrowRecords() {
        for (Borrow borrow : store.getAllBorrows()) {
            System.out.println( borrow.borrowId + "    " + borrow.userId + "            " + borrow.bookId + "     IssueDate: " + borrow.borrowDate + "    DueDate: " + borrow.dueDate + "       returnDate: " +  borrow.returnDate);
        } 
    }

}
