package book_collection;

import java.io.Serializable;

public class Book implements Serializable {
       private String bookId, bookName, authorNames, publication, dateOfPublication;
       private int priceOfBook, totalQuantityToOrder, totalCost;

       public Book() {
       }

       public Book(String bookId, String bookName, String authorNames, String publication, String dateOfPublication,
                     int priceOfBook, int totalQuantityToOrder, int totalCost) {
              this.bookId = bookId;
              this.bookName = bookName;
              this.authorNames = authorNames;
              this.publication = publication;
              this.dateOfPublication = dateOfPublication;
              this.priceOfBook = priceOfBook;
              this.totalQuantityToOrder = totalQuantityToOrder;
              this.totalCost = totalCost;
       }

       public String getBookId() {
              return bookId;
       }

       public void setBookId(String bookId) {
              this.bookId = bookId;
       }

       public String getBookName() {
              return bookName;
       }

       public void setBookName(String bookName) {
              this.bookName = bookName;
       }

       public String getAuthorNames() {
              return authorNames;
       }

       public void setAuthorNames(String authorNames) {
              this.authorNames = authorNames;
       }

       public String getPublication() {
              return publication;
       }

       public void setPublication(String publication) {
              this.publication = publication;
       }

       public String getDateOfPublication() {
              return dateOfPublication;
       }

       public void setDateOfPublication(String dateOfPublication) {
              this.dateOfPublication = dateOfPublication;
       }

       public int getPriceOfBook() {
              return priceOfBook;
       }

       public void setPriceOfBook(int priceOfBook) {
              this.priceOfBook = priceOfBook;
       }

       public int getTotalQuantityToOrder() {
              return totalQuantityToOrder;
       }

       public void setTotalQuantityToOrder(int totalQuantityToOrder) {
              this.totalQuantityToOrder = totalQuantityToOrder;
       }

       public int getTotalCost() {
              return totalCost;
       }

       public void setTotalCost(int totalCost) {
              this.totalCost = totalCost;
       }

}
