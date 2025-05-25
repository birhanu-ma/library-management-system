package libraryManagement;
import java.time.LocalDateTime;
import java.util.*;

public class UpdateUserStatus {
    private String userId;
    private Store store;

    public UpdateUserStatus(String userId, Store store) {
        this.userId = userId;
        this.store = store;
    }

    // O(k) where k = borrows of a single user (better than O(n))
    public void updateStatus() {
        User user = store.getUser(userId);
        if (user == null)
            return;

        int lateReturns = 0;
        LocalDateTime now = LocalDateTime.now();
        List<Borrow> userBorrows = store.getUserBorrows(userId);

        for (Borrow borrow : userBorrows) {
            LocalDateTime dueDate = borrow.getDueDate();
            LocalDateTime returnDate = borrow.getReturnDate();
            if (returnDate == null && dueDate.isBefore(now)) {
                lateReturns++; // Currently overdue
            } else if (returnDate != null && dueDate.isBefore(returnDate)) {
                lateReturns++; // Was returned late
            }
        }

        if (lateReturns >= 5) {
            store.getUser(userId).userStatus = UserStatus.SUSPENDED;

        } else if (lateReturns >= 3) {
            store.getUser(userId).userStatus = UserStatus.WARNING;

        } else {
            store.getUser(userId).userStatus = UserStatus.ACTIVE;

        }
    }
}