package com.book.controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.book.model.BookIoOperation;
import com.book.model.data.Book;
import com.book.model.utils.Constant;
import com.book.model.utils.Validation;
import com.book.view.GenerateListBooks;
import com.book.view.HomePageBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class AddPageCtrl implements ActionListener, DocumentListener, ItemListener, MouseListener {
       private JFrame addPageFrame;
       private JTextField bookIdField;
       private JButton backButton, submitBtn, deleteButton, updateButton;

       public void setDeleteButton(JButton deleteButton) {
              this.deleteButton = deleteButton;
              deleteButton.setVisible(false);
       }

       public void setUpdateButton(JButton updateButton) {
              this.updateButton = updateButton;
              deleteButton.setVisible(false);
       }

       private JTextField bookNameField;
       private JTextField authorNamesField;
       private JTextField publicationField;
       private JTextField priceOfBookField;
       private JTextField totalQuantityField;
       private JTextField totalCostField;
       private JTextField dateOfPublicationField;
       private JComboBox<String> modeDropdown;
       private JTable viewBookTable;
       Book book;

       public void setViewBookTable(JTable viewBookTable) {
              this.viewBookTable = viewBookTable;
       }

       public void setModeDropdown(JComboBox<String> modeDropdown) {
              this.modeDropdown = modeDropdown;
       }

       private BookIoOperation bookIoOperation;

       public JTextField getDateOfPublicationField() {
              return dateOfPublicationField;
       }

       public void setDateOfPublicationField(JTextField dateOfPublicationField) {
              this.dateOfPublicationField = dateOfPublicationField;
       }

       public JTextField getBookNameField() {
              return bookNameField;
       }

       public void setBookNameField(JTextField bookNameField) {
              this.bookNameField = bookNameField;
       }

       public JTextField getAuthorNamesField() {
              return authorNamesField;
       }

       public void setAuthorNamesField(JTextField authorNamesField) {
              this.authorNamesField = authorNamesField;
       }

       public JTextField getPublicationField() {
              return publicationField;
       }

       public void setPublicationField(JTextField publicationField) {
              this.publicationField = publicationField;
       }

       public JTextField getPriceOfBookField() {
              return priceOfBookField;
       }

       public void setPriceOfBookField(JTextField priceOfBookField) {
              this.priceOfBookField = priceOfBookField;
       }

       public JTextField getTotalQuantityField() {
              return totalQuantityField;
       }

       public void setTotalQuantityField(JTextField totalQuantityField) {
              this.totalQuantityField = totalQuantityField;
       }

       public JTextField getTotalCostField() {
              return totalCostField;
       }

       public void setTotalCostField(JTextField totalCostField) {
              this.totalCostField = totalCostField;
       }

       public AddPageCtrl() {
       }

       public JFrame getAddPageFrame() {
              return addPageFrame;
       }

       public void setAddPageFrame(JFrame addPageFrame) {
              this.addPageFrame = addPageFrame;
       }

       public JTextField getBookIdField() {
              return bookIdField;
       }

       public void setBookIdField(JTextField bookIdField) {
              this.bookIdField = bookIdField;
       }

       public JButton getBackButton() {
              return backButton;
       }

       public void setBackButton(JButton backButton) {
              this.backButton = backButton;
       }

       public JButton getSubmitBtn() {
              return submitBtn;
       }

       public void setSubmitBtn(JButton submitBtn) {
              this.submitBtn = submitBtn;
       }

       public AddPageCtrl(JFrame addPageFrame, JTextField bookIdField, JButton backButton, JButton submitBtn) {
              this.addPageFrame = addPageFrame;
              this.bookIdField = bookIdField;
              this.backButton = backButton;
              this.submitBtn = submitBtn;
              this.submitBtn.setEnabled(false);
              this.bookIoOperation = BookIoOperation.getBookOperation();
              this.setBookId();
       }

       private void setBookId() {
              bookIdField.setText(Constant.getBookId());
              bookIdField.setEditable(false);
       }

       public void actionPerformed(ActionEvent e) {
              if (e.getSource() == backButton) {
                     this.addPageFrame.dispose();
                     HomePageBook homeFrame = new HomePageBook();
              } else if (e.getSource() == submitBtn) {
                     if (areFieldsValid()) {
                            saveData();
                            // Constant.showMessage("Book added successfully", addPageFrame);
                     }
              } else if (e.getSource() == deleteButton) {
                     if (areFieldsValid()) {
                            deleteBook();
                     }
              } else if (e.getSource() == updateButton) {
                     if (areFieldsValid()) {
                            updateBook();
                     }
              }
       }

       private void updateBook() {
              getBookData();
              List<Book> books = bookIoOperation.getBooks();
              if (this.book != null && books.size() > 0) {
                     books = Constant.updateBooks(books, book);
                     try {
                            bookIoOperation.writeBooks(books);
                            clearData();
                            modeDropdown.setSelectedIndex(0);
                            GenerateListBooks.reloadData();
                            Constant.showMessage("Book updated successfully", addPageFrame);
                     } catch (IOException e) {
                            Constant.showMessage("Error deleting the book: " + e.getMessage(), addPageFrame);
                     }
              }
       }

       private void deleteBook() {
              List<Book> books = bookIoOperation.getBooks();
              if (this.book != null && books.size() > 0) {
                     books = Constant.deleteBook(books, book);
                     try {
                            bookIoOperation.writeBooks(books);
                            clearData();
                            modeDropdown.setSelectedIndex(0);
                            GenerateListBooks.reloadData();
                            Constant.showMessage("Book deleted successfully", addPageFrame);
                     } catch (IOException e) {
                            Constant.showMessage("Error deleting the book: " + e.getMessage(), addPageFrame);
                     }
              }
       }

       private void getBookData() {
              try {
                     String bookId = bookIdField.getText().trim();
                     String bookName = bookNameField.getText().trim();
                     String authorNames = authorNamesField.getText().trim();
                     String publication = publicationField.getText().trim();
                     int priceOfBook = Integer.parseInt(priceOfBookField.getText().trim());
                     int totalQuantityToOrder = Integer.parseInt(totalQuantityField.getText().trim());
                     int totalCost = Integer.parseInt(totalCostField.getText().trim());
                     System.out.println(dateOfPublicationField.getText());
                     Date dateOfPublication = Constant.parseDate(dateOfPublicationField.getText().trim());

                     Book book = new Book(bookId, bookName, authorNames, publication, priceOfBook, totalQuantityToOrder,
                                   totalCost, dateOfPublication);
                     this.book = book;
              } catch (ParseException e) {
                     Constant.showMessage(e.getMessage(), addPageFrame);
              }

       }

       private void saveData() {
              try {
                     getBookData();
                     bookIoOperation.saveBookObject(book);
                     Constant.showMessage("Book added successfully", addPageFrame);
                     clearData();
                     GenerateListBooks.reloadData();

              } catch (NumberFormatException e) {
                     Constant.showMessage("Please enter valid numbers for price, quantity, and cost.", addPageFrame);
              } catch (IOException e) {
                     Constant.showMessage("Error saving the book: " + e.getMessage(), addPageFrame);
              } catch (Exception e) {
                     Constant.showMessage("Unexpected error: " + e.getMessage(), addPageFrame);
              }
       }

       @Override
       public void insertUpdate(DocumentEvent e) {
              try {
                     if (e.getDocument() == totalQuantityField.getDocument()) {
                            int price = Integer.parseInt(priceOfBookField.getText());
                            int quantity = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
                            totalCostField.setText(String.valueOf(Constant.calculateTotalPrice(price, quantity)));
                            this.submitBtn.setEnabled(true);
                     } else if (e.getDocument() == priceOfBookField.getDocument()) {
                            int price = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
                            int quantity = Integer.parseInt(totalQuantityField.getText());
                            totalCostField.setText(String.valueOf(Constant.calculateTotalPrice(price, quantity)));
                            this.submitBtn.setEnabled(true);
                     } else if (e.getDocument() == dateOfPublicationField.getDocument()) {
                            String publicationDate = e.getDocument().getText(0, e.getDocument().getLength());
                            if (Validation.validateDate(publicationDate)) {
                                   this.submitBtn.setEnabled(true);
                                   Constant.showMessage("Correct date format", addPageFrame);
                            }
                     }
              } catch (BadLocationException ex) {
                     System.out.println(ex.getMessage());
              } catch (NumberFormatException ex) {
                     // Constant.showMessage("Please enter a valid number", addPageFrame);
                     System.out.println(ex.getMessage());
                     this.submitBtn.setEnabled(false);
              }
       }

       @Override
       public void removeUpdate(DocumentEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'removeUpdate'");
       }

       @Override
       public void changedUpdate(DocumentEvent e) {
              //
       }

       private boolean areFieldsValid() {
              if (bookNameField.getText().trim().isEmpty()) {
                     Constant.showMessage("Book name is required", addPageFrame);
                     return false;
              }
              if (authorNamesField.getText().trim().isEmpty()) {
                     Constant.showMessage("Author names are required", addPageFrame);
                     return false;
              }
              if (publicationField.getText().trim().isEmpty()) {
                     Constant.showMessage("Publication is required", addPageFrame);
                     return false;
              }
              if (priceOfBookField.getText().trim().isEmpty()) {
                     Constant.showMessage("Price of the book is required", addPageFrame);
                     return false;
              }
              if (totalQuantityField.getText().trim().isEmpty()) {
                     Constant.showMessage("Total quantity is required", addPageFrame);
                     return false;
              }
              if (dateOfPublicationField.getText().trim().isEmpty()) {
                     Constant.showMessage("Date of publication is required", addPageFrame);
                     return false;
              }
              if (totalCostField.getText().trim().isEmpty()) {
                     Constant.showMessage("Total cost is required", addPageFrame);
                     return false;
              }
              return true;
       }

       private void clearData() {
              setBookId();
              bookNameField.setText("");
              authorNamesField.setText("");
              publicationField.setText("");
              priceOfBookField.setText("0");
              totalQuantityField.setText("0");
              dateOfPublicationField.setText("");
       }

       @Override
       public void itemStateChanged(ItemEvent e) {
              if (e.getStateChange() == ItemEvent.SELECTED) {
                     String mode = e.getItem().toString();
                     doModeAction(mode);
              }
       }

       private void doModeAction(String mode) {
              if (mode.equals("Update Mode")) {
                     bookIdField.setText("");
                     bookIdField.setEditable(true);
                     deleteButton.setVisible(true);
                     updateButton.setVisible(true);
                     submitBtn.setVisible(false);
                     backButton.setVisible(false);
              } else {
                     clearData();
                     deleteButton.setVisible(false);
                     updateButton.setVisible(false);
                     submitBtn.setVisible(true);
                     backButton.setVisible(true);
              }
       }

       private void setData() {
              bookIdField.setText(book.getBookId());
              bookNameField.setText(book.getBookName());
              authorNamesField.setText(book.getAuthorNames());
              publicationField.setText(book.getPublication());
              priceOfBookField.setText(book.getPriceOfBook() + "");
              totalQuantityField.setText(book.getTotalQuantityToOrder() + "");
              dateOfPublicationField.setText(Validation.dateFormatString(book.getDateOfPublication()));
       }

       @Override
       public void mouseClicked(MouseEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'mouseClicked'");
              JTable target = (JTable) e.getSource();
              int row = target.getSelectedRow();
              int column = target.getSelectedColumn();
              String bookId = (String) target.getModel().getValueAt(row, 0);
              List<Book> books = bookIoOperation.getBooks();
              Book book = Constant.getBookDataFromField(books, "Book ID", bookId);
              if (book != null) {
                     modeDropdown.setSelectedIndex(1);
                     this.book = book;
                     setData();
              }
       }

       @Override
       public void mousePressed(MouseEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'mousePressed'");
       }

       @Override
       public void mouseReleased(MouseEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'mouseReleased'");
       }

       @Override
       public void mouseEntered(MouseEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'mouseEntered'");
       }

       @Override
       public void mouseExited(MouseEvent e) {
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'mouseExited'");
       }
}