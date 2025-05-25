package libraryManagement;
public class BorrowIfAllowed {
  private final String userId;
  private final String bookId;
  private final int duration;
  private final Store store;
  private final UpdateUserStatus updater;

  public BorrowIfAllowed(String userId, String bookId, int duration, Store store) {
    this.userId = userId;
    this.bookId = bookId;
    this.duration = duration;
    this.store = store;
    this.updater = new UpdateUserStatus(userId, store);
  }

  public String borrow() {
    User user = store.getUser(userId);
    BookDetail book = store.getBook(bookId);

    if (user == null || book == null) {
      return "user or book not found";
    }

    updater.updateStatus();

    if ("SUSPENDED".equals(store.getUser(userId).userStatus.name())) {

      return "User is suspended";

    }

    if (book.availCopies <= 0) {
      return "No available copies.";

    }
    if (store.getUserUnreturnedBorrow(userId).size() >= 3) {
      return "Borrow limit reached.";
    }
    store.addBorrow(new Borrow(userId, bookId, duration));
    store.borrowBook(bookId);
    return "success";
  }
}