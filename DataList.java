
import java.util.List;

public class DataList {
  Store store;

  public DataList(Store store) {
    this.store = store;
  }

  public void addDemoUser() {
    for (User user : users) {
      store.addUser(user);
    }
  }
  
  public void addDemoBook(){
    for(BookDetail book : books){
      store.addBook(book.bookId,book.bookTitle,book.availCopies,book.shelfNo);
    }
  }
  List<User> users = List.of(
      new User("U001", "Smith"),
      new User("U002", "Bob"),
      new User("U003", "Carol"),
      new User("U004", "David"),
      new User("U005", "Eve"),
      new User("U006", "Frank"),
      new User("U007", "Grace"),
      new User("U008", "Henry"));

      List<BookDetail> books = List.of(
        new BookDetail("B001", "The Time Keeper", 5,5, 1),
        new BookDetail("B002", "Clean Code", 7, 7,2),
        new BookDetail("B003", "1984", 4,4, 3),
        new BookDetail("B004", "The Hobbit", 6,6, 4),
        new BookDetail("B005", "To Kill a Mockingbird", 5,5,1)
    );
}