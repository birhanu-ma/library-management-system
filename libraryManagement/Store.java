package libraryManagement;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    Book bookManager;
    private Map<String, User> users;
    private Map<Integer, Borrow> borrows;

    public Store() {
        this.bookManager = new Book();
        this.users = new HashMap<>();
        this.borrows = new HashMap<>();
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public BookDetail getBook(String bookId) {
        return bookManager.getBook(bookId);
    }

    public Borrow getBorrow(int borrowId) {
        if (borrows.containsKey(borrowId)) {
            return borrows.get(borrowId);
        }
        return null; 
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.userId)) {
            return false;
        }
        users.put(user.userId, user);
        return true;
    }

    public boolean addBook(String bookId, String title, int totalCopies,int shelfNo) {
        return bookManager.addBook(bookId, title, totalCopies,shelfNo);
    }

    public boolean addBorrow(Borrow borrow) {
        if (borrows.containsKey(borrow.borrowId)) {
            return false;
        }
        borrows.put(borrow.borrowId, borrow);
        return true;
    }


    public boolean removeUser(String userId) {
        return users.remove(userId) != null;
    }

    public boolean removeBook(String bookId) {
        if (bookManager.getBook(bookId) != null) {
            bookManager.deleteBook(bookId);
            return true;
        }
        return false;
    }

    public boolean removeBorrow(int borrowId) {
        return borrows.remove(borrowId) != null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<BookDetail> getAllBooks() {
        return bookManager.getAllBooks();
    }

    public List<Borrow> getAllBorrows() {
        return new ArrayList<>(borrows.values());
    }

    public List<Borrow> getUserBorrows(String userId) {
        return borrows.values()
                .stream()
                .filter(borrow -> borrow.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Borrow> getUserUnreturnedBorrow(String userId) {
        return getUserBorrows(userId).stream()
                .filter(borrow -> borrow.getReturnDate() == null)
                .collect(Collectors.toList());
    }
    public void borrowBook(String bookId){
        bookManager.borrowBook(bookId);
    }
    public void returnBook(String bookId){
        bookManager.returnBook(bookId);
    }
}
