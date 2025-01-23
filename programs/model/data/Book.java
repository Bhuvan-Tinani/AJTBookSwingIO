package com.book.model.data;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
       private String bookId, bookName, authorNames, publication;
       private int priceOfBook, totalQuantityToOrder, totalCost;

       Date dateOfPublication;

       public Book() {
       }

       public Book(String bookId, String bookName, String authorNames, String publication, int priceOfBook,
                     int totalQuantityToOrder, int totalCost, Date dateOfPublication) {
              this.bookId = bookId;
              this.bookName = bookName;
              this.authorNames = authorNames;
              this.publication = publication;
              this.priceOfBook = priceOfBook;
              this.totalQuantityToOrder = totalQuantityToOrder;
              this.totalCost = totalCost;
              this.dateOfPublication = dateOfPublication;
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

       public Date getDateOfPublication() {
              return dateOfPublication;
       }

       public void setDateOfPublication(Date dateOfPublication) {
              this.dateOfPublication = dateOfPublication;
       }

}
