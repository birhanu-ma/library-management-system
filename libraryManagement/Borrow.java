package libraryManagement;
import java.time.LocalDateTime;

public class Borrow {
    int borrowId;
    String userId;
    String bookId;
    LocalDateTime borrowDate;
    LocalDateTime dueDate;
    LocalDateTime returnDate;

    private static int idCounter = 1;

    public Borrow(String userId, String bookId, int day) {
        this.borrowId = idCounter++;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = LocalDateTime.now();
        this.dueDate = LocalDateTime.now().plusDays(day);
        this.returnDate = null;
    }


    public int getBorrowId() {
        return borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }
}
