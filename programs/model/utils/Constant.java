package com.book.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.book.model.data.Book;

public class Constant {
       public static final String BOOK_FILE = "book.dat";

       public static String getBookId() {
              LocalDateTime now = LocalDateTime.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
              return now.format(formatter);
       }

       public static void showMessage(String message, JFrame frame) {
              JOptionPane.showMessageDialog(frame, message);
       }

       public static int calculateTotalPrice(int price, int qty) {
              return price * qty;
       }

       public static Date parseDate(String date) throws ParseException {
              SimpleDateFormat osdf = new SimpleDateFormat("dd-MM-yyyy");
              osdf.setLenient(false);
              return osdf.parse(date);
       }

       public static String[] bookFieldCollection(List<Book> books, String field) {
              switch (field) {
                     case "Book ID":
                            return books.stream()
                                          .map(Book::getBookId)
                                          .toArray(String[]::new);

                     case "Book Name":
                            return books.stream()
                                          .map(Book::getBookName)
                                          .toArray(String[]::new);

                     case "Author Name":
                            return books.stream()
                                          .map(Book::getAuthorNames)
                                          .toArray(String[]::new);

                     case "Publication":
                            return books.stream()
                                          .map(Book::getPublication)
                                          .toArray(String[]::new);

                     default:
                            return null;
              }
       }

       public static Book getBookDataFromField(List<Book> books, String field, String data) {
              for (Book book : books) {
                     switch (field) {
                            case "Book ID":
                                   if (book.getBookId().equals(data)) {
                                          return book;
                                   }
                                   break;

                            case "Book Name":
                                   if (book.getBookName().equalsIgnoreCase(data)) {
                                          return book;
                                   }
                                   break;

                            case "Author Name":
                                   if (book.getAuthorNames().equalsIgnoreCase(data)) {
                                          return book;
                                   }
                                   break;

                            case "Publication":
                                   if (book.getPublication().equalsIgnoreCase(data)) {
                                          return book;
                                   }
                                   break;

                            default:
                                   throw new IllegalArgumentException("Invalid field: " + field);
                     }
              }
              return null;
       }

       public static List<Book> deleteBook(List<Book> books, Book book) {
              int i;
              int flag = -1;
              for (i = 0; i < books.size(); i++) {
                     if (books.get(i).getBookId().equals(book.getBookId())) {
                            break;
                     }
              }
              books.remove(i);
              return books;
       }

       public static List<Book> updateBooks(List<Book> books, Book book) {
              for (int i = 0; i < books.size(); i++) {
                     if (books.get(i).getBookId().equals(book.getBookId())) {
                            books.set(i, book);
                            break;
                     }
              }
              return books;
       }

}
