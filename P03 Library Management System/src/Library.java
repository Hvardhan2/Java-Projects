import java.util.ArrayList;

/**
 * Represents a library that manages a collection of books.
 */
public class Library {
  private ArrayList<Book> books = new ArrayList<>(); // ArrayList to store books

  /**
   * Returns the total number of books in the library.
   *
   * @return the total number of books.
   */
  public int getTotalBooks() {
    return books.size();
  }

  /**
   * Returns the list of all books in the library.
   *
   * @return an ArrayList containing all books.
   */
  public ArrayList<Book> getAllBooks() {
   
    return books;
  }

  /**
   * Adds a new book to the library while maintaining the order of the books by year of publication.
   *
   * @param book the book to be added to the library.
   */
  public void addBook(Book book) {
    int i = 0;
    while (i < books.size() && books.get(i).getYearOfPublication() < book.getYearOfPublication()) {
      i++;
    }
    books.add(i, book); // Adds book at the correct position
    }

  /**
   * Removes a book from the library by its title.
   *
   * @param title the title of the book to be removed.
   * @return true if the book was successfully removed; false otherwise.
   */
  public boolean removeBookByTitle(String title) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equals(title)) {
        books.remove(i);
        return true;
      }
    }
    return false;
    }

  /**
   * Finds and returns a list of books by a specific author.
   *
   * @param author the author whose books are to be found.
   * @return an ArrayList of books written by the specified author.
   */
  public ArrayList<Book> findBooksByAuthor(String author) {
    ArrayList<Book> booksByAuthor = new ArrayList<>();
    for (Book book : books) {
      if (book.getAuthor().equals(author)) {
        booksByAuthor.add(book);
      }
    }
    return booksByAuthor;
    }

  /**
   * Updates the title of a book in the library.
   *
   * @param oldTitle the current title of the book to be updated.
   * @param newTitle the new title to assign to the book.
   * @return true if the title was updated successfully; false otherwise.
   */
  public boolean updateBookTitle(String oldTitle, String newTitle) {
    for (Book book : books) {
      if (book.getTitle().equals(oldTitle)) {
        book.setTitle(newTitle);
        return true;
      }
    }
    return false;
    }

  /**
   * Updates the author of a book in the library by its title.
   *
   * @param title     the title of the book whose author is to be updated.
   * @param newAuthor the new author to assign to the book.
   * @return true if the author was updated successfully; false otherwise.
   */
  public boolean updateBookAuthor(String title, String newAuthor) {
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        book.setAuthor(newAuthor);
        return true;
      }
    }
    return false;
    }

  /**
   * Prints the title and author of every book in the library.
   */
  public void printAllBooks() {
    for (Book book : books) {
      System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
    }
    }
}
