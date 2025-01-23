package com.book.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.book.model.data.Book;
import com.book.model.utils.Constant;

public class BookIoOperation {
       private static BookIoOperation bookIoOperation;
       Book book;
       OutputStream fos;
       ObjectOutputStream oos;
       ObjectInputStream ois;
       InputStream fis;
       File f;
       List<Book> books;

       private BookIoOperation() throws IOException {
              this.f = new File(Constant.BOOK_FILE);
              if (f.createNewFile()) {
                     this.books = new ArrayList<>();
              } else {
                     readBookObject();
              }
       }

       @SuppressWarnings("unchecked")
       private void readBookObject() {
              checkbooksNULL();
              try {
                     this.fis = new FileInputStream(f);
                     this.ois = new ObjectInputStream(fis);
                     this.books = (List<Book>) ois.readObject();
                     this.ois.close();
                     this.fis.close();
                     releaseFileStream();
                     releaseObjectStream();
              } catch (Exception e) {
                     System.out.println(e.getMessage());
              }
       }

       public List<Book> getBooks() {
              this.books = null;
              readBookObject();
              return books;
       }

       private void checkbooksNULL() {
              if (books == null) {
                     this.books = new ArrayList<>();
              }
       }

       private void releaseFileStream() {
              this.fos = null;
              this.fis = null;
       }

       private void releaseObjectStream() {
              this.oos = null;
              this.ois = null;
       }

       public void saveBookObject(Book book) throws IOException {
              checkbooksNULL();
              books.add(book);
              this.fos = new FileOutputStream(f);
              this.oos = new ObjectOutputStream(fos);
              oos.writeObject(books);
              oos.flush();
              this.oos.close();
              this.fos.close();
              releaseFileStream();
              releaseObjectStream();
       }

       public static BookIoOperation getBookOperation() {
              if (bookIoOperation == null) {
                     try {
                            bookIoOperation = new BookIoOperation();
                     } catch (IOException e) {
                            e.printStackTrace();
                     }
              }
              return bookIoOperation;
       }

       public void writeBooks(List<Book> books) throws IOException {
              this.fos = new FileOutputStream(f);
              this.oos = new ObjectOutputStream(fos);
              oos.writeObject(books);
              oos.flush();
              this.oos.close();
              this.fos.close();
              releaseFileStream();
              releaseObjectStream();
       }

}
