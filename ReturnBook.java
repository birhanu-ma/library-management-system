import java.time.LocalDateTime;

public class ReturnBook {
    int borrowId;
    LocalDateTime returnDate;
    Store store;
    UpdateUserStatus update;

    public ReturnBook(int borrowId, int returnedDuration, Store store) {
        this.borrowId = borrowId;
        this.returnDate = LocalDateTime.now().plusDays(returnedDuration);
        this.store = store;
        if (store.getBorrow(borrowId) != null) {
            this.update = new UpdateUserStatus(store.getBorrow(borrowId).userId, store);
        }

    }

    public void rBook() {
        if (store.getBorrow(borrowId) == null) {
            System.out.println("record not found");
        } else {
            Borrow borrow = store.getBorrow(borrowId);
            borrow.returnDate = returnDate;
            store.returnBook(borrow.bookId);
            update.updateStatus();
        }

    }

}