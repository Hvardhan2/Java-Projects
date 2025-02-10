//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Library Management
// Course: CS 300 Fall 2024
//
// Author: Harshvardhan Singh Rathore
// Email: hvardhan2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources: ChatGPT in explaining algorithm I was confused about.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Tests methods of Book and Library classes.
 */
public class LibraryTester {
  /**
   * PROVIDED TESTER METHOD: example test method for testing the getTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "1984".equals(book.getTitle());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for testing the setTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  // public Book(String title, String author, int yearOfPublication,
  // String publisher,int numberOfPages)
  public static boolean testSetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setTitle("Animal Farm");
    return "Animal Farm".equals(book.getTitle());
  }

  public static boolean testGetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "George Orwell".equals(book.getAuthor());
  }


  public static boolean testSetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setAuthor("Harsh Rathore");
    return "Harsh Rathore".equals(book.getAuthor());
    // TODO
  }

  public static boolean testGetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);

    return 1949 == book.getYearOfPublication();
  }

  public static boolean testSetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setYearOfPublication(1999);
    return 1999 == book.getYearOfPublication();
  }

  public static boolean testGetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "Secker & Warburg".equals(book.getPublisher());
  }

  public static boolean testSetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setPublisher("Shoes & Socks");
    return "Shoes & Socks".equals(book.getPublisher());
  }

  public static boolean testGetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return (328 == book.getNumberOfPages());
  }

  public static boolean testSetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setNumberOfPages(298);
    return 298 == book.getNumberOfPages();
  }

  /**
   * PROVIDED TESTER METHOD: Retrieves the total number of books in the library.
   * 
   * @return the total number of books
   */
  public static boolean testGetTotalBooks() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    library.addBook(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    int expected = 2;
    int result = library.getTotalBooks();

    ArrayList<Book> expectedA = new ArrayList<>();
    expectedA.add(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    expectedA.add(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    if (expected != result) {
      return false;
    } else {

      return compareBooks(expectedA, library.getAllBooks());

    }
  }

  /**
   * PROVIDED TESTER METHOD: example test method for adding a single book to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddBook() {
    Library library = new Library();
    Book book = new Book("Test Book", "Test Author", 2023, "Publisher X", 100);
    library.addBook(book);

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    return compareBooks(expected, library.getAllBooks());
  }

  public static boolean testAddMultipleBooks() {
    Library library = new Library();
    library.addBook(new Book("Test Book1", "Test Author1", 2020, "Publisher X", 101));
    library.addBook(new Book("Test Book2", "Test Author2", 2021, "Publisher Y", 102));
    library.addBook(new Book("Test Book3", "Test Author3", 2022, "Publisher Z", 103));

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book1", "Test Author1", 2020, "Publisher X", 101));
    expected.add(new Book("Test Book2", "Test Author2", 2021, "Publisher Y", 102));
    expected.add(new Book("Test Book3", "Test Author3", 2022, "Publisher Z", 103));

    if (expected.size() != library.getTotalBooks()) {
      return false;
    } else {
      return compareBooks(expected, library.getAllBooks());
    }

    }


  /**
   * PROVIDED TESTER METHOD: example test method for removing a book by title from the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBookByTitle() {
    Library library = new Library();
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    boolean result = library.removeBookByTitle("Test Book");

    // checking result from removeBookByTitle("Test Book")
    if (result != true) {
      return false;
    }
    // checking resulted number of books
    if (library.getTotalBooks() != 0) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<>();
    // checking resulted library
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }




  public static boolean testFindBooksByAuthor() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2020, "Publisher X", 150));
    library.addBook(new Book("Book 2", "Author A", 2021, "Publisher Y", 250));
    library.addBook(new Book("Book 3", "Author B", 2022, "Publisher Z", 350));

    ArrayList<Book> booksByAuthor = library.findBooksByAuthor("Author A");
    ArrayList<Book> expected = new ArrayList<Book>();

    expected.add(new Book("Book 1", "Author A", 2020, "Publisher X", 150));
    expected.add(new Book("Book 2", "Author A", 2021, "Publisher Y", 250));
    return compareBooks(booksByAuthor, expected);



  }

  public static boolean testFindBooksByMultipleAuthors() {
    Library library = new Library();

    // Add multiple books by different authors
    library.addBook(new Book("Book 1", "Author A", 2020, "Publisher X", 150));
    library.addBook(new Book("Book 2", "Author A", 2021, "Publisher Y", 250));
    library.addBook(new Book("Book 3", "Author B", 2022, "Publisher Z", 350));
    library.addBook(new Book("Book 4", "Author C", 2023, "Publisher W", 400));

    // Authors we want to find books for
    ArrayList<String> authors = new ArrayList<>();
    authors.add("Author A");
    authors.add("Author C");

    // Collect books by multiple authors using findBooksByAuthor
    ArrayList<Book> booksByMultipleAuthors = new ArrayList<>();
    for (String author : authors) {
      booksByMultipleAuthors.addAll(library.findBooksByAuthor(author));
    }

    // Expected result
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Book 1", "Author A", 2020, "Publisher X", 150));
    expected.add(new Book("Book 2", "Author A", 2021, "Publisher Y", 250));
    expected.add(new Book("Book 4", "Author C", 2023, "Publisher W", 400));

    // Compare actual result with expected
    return compareBooks(booksByMultipleAuthors, expected);
  }



  public static boolean testUpdateBookTitle() {
    Library library = new Library();
    library.addBook(new Book("Old Title", "Author A", 2020, "Publisher X", 150));

    boolean result = library.updateBookTitle("Old Title", "New Title");

    // Verify that the title was updated successfully
    return result;
  }


    public static boolean testUpdateMultipleBookTitles() {
      Library library = new Library();
      // Adding multiple books with initial titles
      library.addBook(new Book("Old Title 1", "Author A", 2020, "Publisher X", 150));
      library.addBook(new Book("Old Title 2", "Author B", 2021, "Publisher Y", 200));
      library.addBook(new Book("Old Title 3", "Author C", 2022, "Publisher Z", 300));

      // Updating all books' titles
      boolean isFirstTitleUpdated = library.updateBookTitle("Old Title 1", "New Title 1");
      boolean isSecondTitleUpdated = library.updateBookTitle("Old Title 2", "New Title 2");
      boolean isThirdTitleUpdated = library.updateBookTitle("Old Title 3", "New Title 3");

      // Return true only if all titles were updated successfully
      return isFirstTitleUpdated && isSecondTitleUpdated && isThirdTitleUpdated;
  
  }

      public static boolean testUpdateBookAuthor() {
      Library library = new Library();
      // Adding a book with an initial author
      library.addBook(new Book("Title A", "Old Author", 2020, "Publisher X", 150));
      
      // Updating the book's author
      boolean isAuthorUpdated = library.updateBookAuthor("Title A", "New Author");
      
      // Return true if the author was updated successfully, false otherwise
      return isAuthorUpdated;

  }

  public static boolean testUpdateMultipleBookAuthors() {
    Library library = new Library();
    // Adding multiple books with initial authors
    library.addBook(new Book("Title A", "Old Author 1", 2020, "Publisher X", 150));
    library.addBook(new Book("Title B", "Old Author 2", 2021, "Publisher Y", 200));
    library.addBook(new Book("Title C", "Old Author 3", 2022, "Publisher Z", 300));

    // Updating all books' authors
    boolean isFirstAuthorUpdated = library.updateBookAuthor("Title A", "New Author 1");
    boolean isSecondAuthorUpdated = library.updateBookAuthor("Title B", "New Author 2");
    boolean isThirdAuthorUpdated = library.updateBookAuthor("Title C", "New Author 3");

    // Return true only if all authors were updated successfully
    return isFirstAuthorUpdated && isSecondAuthorUpdated && isThirdAuthorUpdated;
  }


  public static boolean testRemoveNonExistentBook() {
    Library library = new Library();
    // Adding a book to the library
    library.addBook(new Book("Title A", "Author A", 2020, "Publisher A", 150));

    // Attempting to remove a book that does not exist
    boolean isRemoved = library.removeBookByTitle("Non-existent Title");

    // Verify that the removal attempt returns false
    // Also verify the total number of books is still 1
    return !isRemoved && library.getTotalBooks() == 1;
  } // TODO

  public static boolean testRemoveOneOfManyBooks() {
    // Create a Library object
    Library library = new Library();

    // Add multiple books to the library
    library.addBook(new Book("Title A", "Old Author 1", 2020, "Publisher X", 150));
    library.addBook(new Book("Title B", "Old Author 2", 2021, "Publisher Y", 200));
    library.addBook(new Book("Title C", "Old Author 3", 2022, "Publisher Z", 300));

    // Remove one book by title
    library.removeBookByTitle("Title B");
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Title A", "Old Author 1", 2020, "Publisher X", 150));
    expected.add(new Book("Title C", "Old Author 3", 2022, "Publisher Z", 300));

    // Verify the total number of books after removal
    return compareBooks(expected, library.getAllBooks());
  }


  /**
   * Compares two lists of books for equality.
   * 
   * @param expected the expected list of books
   * @param result   the list of books to compare
   * @return true if both lists contain the same books, false otherwise
   */
  private static boolean compareBooks(ArrayList<Book> expected, ArrayList<Book> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      Book expectedBook = expected.get(i);
      Book resultBook = result.get(i);
      if (!expectedBook.getTitle().equals(resultBook.getTitle())
          || !expectedBook.getAuthor().equals(resultBook.getAuthor())
          || !(expectedBook.getPublisher().equals(resultBook.getPublisher()))
          || !(expectedBook.getNumberOfPages() == resultBook.getNumberOfPages())
          || !(expectedBook.getYearOfPublication() == resultBook.getYearOfPublication())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // test two functions in book.class

    System.out.println("Test getTitle: " + testGetTitle());
    System.out.println("Test setTitle: " + testSetTitle());
    System.out.println("Test getAuthor: " + testGetAuthor());
    System.out.println("Test setAuthor: " + testSetAuthor());
    System.out.println("Test getYearOfPublication: " + testGetYearOfPublication());
    System.out.println("Test setYearOfPublication: " + testSetYearOfPublication());
    System.out.println("Test getPublisher: " + testGetPublisher());
    System.out.println("Test setPublisher: " + testSetPublisher());
    System.out.println("Test getNumberOfPages: " + testGetNumberOfPages());
    System.out.println("Test setNumberOfPages: " + testSetNumberOfPages());
    System.out.println("Test getTotalBooks: " + testGetTotalBooks());
    System.out.println("Test addBook: " + testAddBook());
    System.out.println("Test addMultipleBooks: " + testAddMultipleBooks());
    System.out.println("Test removeBookByTitle: " + testRemoveBookByTitle());
    System.out.println("Test removeOneOfManyBooks: " + testRemoveOneOfManyBooks());
    System.out.println("Test findBooksByAuthor: " + testFindBooksByAuthor());
    System.out.println("Test findBooksByMultipleAuthors: " + testFindBooksByMultipleAuthors());
    System.out.println("Test updateBookTitle: " + testUpdateBookTitle());
    System.out.println("Test updateMultipleBookTitles: " + testUpdateMultipleBookTitles());
    System.out.println("Test updateBookAuthor: " + testUpdateBookAuthor());
    System.out.println("Test updateMultipleBookAuthors: " + testUpdateMultipleBookAuthors());
    System.out.println("Test removeNonExistentBook: " + testRemoveNonExistentBook());

  }
}
