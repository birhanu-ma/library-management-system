package libraryManagement;
import java.util.ArrayList;
import java.util.List;

class Node {
    String bookId;
    String bookTitle;
    int totalCopies;
    int availCopies;
    int shelfNo;
    Node left, right;

    Node(String bookId, String bookTitle, int totalCopies, int availCopies,int shelfNo) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.totalCopies = totalCopies;
        this.availCopies = availCopies;
        this.shelfNo = shelfNo;
    }
}

public class Book {
    private Node root;

    public Book() {
        root = null;
    }

    public boolean addBook(String bookId, String bookTitle, int totalCopies, int shelfNo) {
        Node newNode = new Node(bookId, bookTitle, totalCopies,totalCopies ,shelfNo);
        root = insert(root, newNode);
        return true;
    }

    private Node insert(Node root, Node newNode) {
        if (root == null)
            return newNode;
        if (newNode.bookId.compareTo(root.bookId) < 0)
            root.left = insert(root.left, newNode);
        else
            root.right = insert(root.right, newNode);
        return root;
    }

    public BookDetail getBook(String bookId) {
        Node  bookNode  = search(root, bookId);
        return new BookDetail(bookNode.bookId,bookNode.bookTitle,bookNode.totalCopies, bookNode.availCopies,bookNode.shelfNo);
    }

    private Node search(Node root, String bookId) {
        if (root == null || root.bookId.equals(bookId))
            return root;
        return bookId.compareTo(root.bookId) < 0 ? search(root.left, bookId) : search(root.right, bookId);
    }

    public void deleteBook(String bookId) {
        root = delete(root, bookId);
    }



    private Node delete(Node root, String bookId) {
        if (root == null)
            return null;

        if (bookId.compareTo(root.bookId) < 0) {
            root.left = delete(root.left, bookId);
        } else if (bookId.compareTo(root.bookId) > 0) {
            root.right = delete(root.right, bookId);
        } else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            Node minNode = getMin(root.right);
            root.bookId = minNode.bookId;
            root.bookTitle = minNode.bookTitle;
            root.totalCopies = minNode.totalCopies;
            root.availCopies = minNode.availCopies;
            root.shelfNo = minNode.shelfNo;
            root.right = delete(root.right, minNode.bookId);
        }
        return root;
    }

    private Node getMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public List<BookDetail> getAllBooks() {
        List<BookDetail> books = new ArrayList<>();
        inorder(root, books);
        return books;
    }

    private void inorder(Node node, List<BookDetail> books) {
        if (node != null) {
            inorder(node.left, books);
            BookDetail book  = new BookDetail(node.bookId, node.bookTitle, node.totalCopies,node.availCopies, node.shelfNo);
            books.add(book);
            inorder(node.right, books);
        }
    }

    public boolean borrowBook(String bookId) {
        Node book = search(root, bookId);
        if (book != null && book.availCopies > 0) {
            book.availCopies--;
            
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId) {
        Node book = search(root, bookId);
        if (book != null && book.availCopies < book.totalCopies) {
            book.availCopies++;
            return true;
        }
        return false;
    }
}
