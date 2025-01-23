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
import java.io.IOException;
import java.util.Date;

public class AddPageCtrl implements ActionListener, DocumentListener {
       private JFrame addPageFrame;
       private JTextField bookIdField;
       private JButton backButton, submitBtn;
       private JTextField bookNameField;
       private JTextField authorNamesField;
       private JTextField publicationField;
       private JTextField priceOfBookField;
       private JTextField totalQuantityField;
       private JTextField totalCostField;
       private JTextField dateOfPublicationField;
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
              }
       }

       private void saveData() {
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
}
