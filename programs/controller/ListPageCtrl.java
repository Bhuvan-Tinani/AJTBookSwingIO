package com.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.book.model.BookIoOperation;
import com.book.model.data.Book;
import com.book.model.utils.Constant;
import com.book.model.utils.Validation;
import com.book.view.HomePageBook;

public class ListPageCtrl implements ActionListener {
       private DefaultTableModel bookTableModel;
       BookIoOperation bookOperation;
       private JFrame listPageFrame;
       private JButton backButton;

       public JButton getBackButton() {
              return backButton;
       }

       public void setBackButton(JButton backButton) {
              this.backButton = backButton;
       }

       public ListPageCtrl(JFrame listPageFrame, DefaultTableModel bookTableModel) {
              this.bookTableModel = bookTableModel;
              this.bookOperation = BookIoOperation.getBookOperation();
              this.listPageFrame = listPageFrame;
              setTableData();
       }

       public void setBookTableModel(DefaultTableModel bookTableModel) {
              this.bookTableModel = bookTableModel;
       }

       public void setTableData() {
              // System.out.println("hello");
              removeData();
              List<Book> books = bookOperation.getBooks();
              // System.out.println(books);
              if (books != null && books.size() > 0) {
                     // Clear existing data in the table model
                     bookTableModel.setRowCount(0);

                     // Add books data to the table model
                     for (Book book : books) {
                            bookTableModel.addRow(new Object[] {
                                          book.getBookId(),
                                          book.getBookName(),
                                          book.getAuthorNames(),
                                          book.getPublication(),
                                          book.getPriceOfBook(),
                                          book.getTotalQuantityToOrder(),
                                          book.getTotalCost(),
                                          Validation.dateFormatString(book.getDateOfPublication())
                            });
                     }
              } else {
                     Constant.showMessage("No data found", listPageFrame);
                     HomePageBook homeFrame = new HomePageBook();
                     listPageFrame.dispose();
              }
       }

       private void removeData() {
              if (bookTableModel != null) {
                     bookTableModel.setRowCount(0);
              }
       }

       @Override
       public void actionPerformed(ActionEvent e) {
              if (e.getSource() == backButton) {
                     this.listPageFrame.dispose();
                     HomePageBook homeFrame = new HomePageBook();
              }
       }
}
