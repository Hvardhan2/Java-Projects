
public class Book {
  private String title;
  private String author;
  private int yearOfPublication;
  private String publisher;
  private int numberOfPages;


public Book(String title, String author, int yearOfPublication, 
  String publisher,int numberOfPages) throws IllegalArgumentException{
  this.title = title;
  this.author = author;

  this.publisher = publisher;
  this.numberOfPages = numberOfPages;
  if (yearOfPublication<0 || yearOfPublication>2024) {
    throw new IllegalArgumentException(
        "year of publication is either negative  or greater than" + " current year");
  }
  else {
    this.yearOfPublication = yearOfPublication;
  }
  if (numberOfPages < 0) {
    throw new IllegalArgumentException("number of pages can't be negative");
  } else {
    this.numberOfPages = numberOfPages;
  }
}

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }



  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  public int getYearOfPublication() {
    return this.yearOfPublication;
  }

  public void setYearOfPublication(int yearOfPublication) throws IllegalArgumentException {
    if (yearOfPublication < 0 || yearOfPublication > 2024) {
      throw new IllegalArgumentException(
          "year of publication is either negative  or greater than current year");
    } else {
    this.yearOfPublication = yearOfPublication;
  }
}


  public String getPublisher() {
    return this.publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getNumberOfPages() {
    return this.numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) throws IllegalArgumentException {
    if (numberOfPages < 0) {
      throw new IllegalArgumentException("number of pages can't be negative");
    } else {
      this.numberOfPages = numberOfPages;
    }
  }

}
