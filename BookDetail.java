public class BookDetail {
    public String bookId;
    public String bookTitle;
    public int totalCopies;
    public int shelfNo;
    public int availCopies;

    public BookDetail(String bookId, String bookTitle, int totalCopies,int availCopies,int shelfNo) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.totalCopies = totalCopies;
        this.shelfNo = shelfNo;
        this.availCopies = availCopies;
    }
    public BookDetail(int availCopies){
        this.availCopies = availCopies;
    }
}
