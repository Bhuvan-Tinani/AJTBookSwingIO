package com.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.book.model.BookIoOperation;
import com.book.model.data.Book;
import com.book.model.utils.Constant;
import com.book.model.utils.Validation;
import com.book.view.HomePageBook;

public class ViewPageCtrl implements ActionListener, ItemListener, DocumentListener {
       private JPanel formPanel;
       private JComboBox<String> searchDropdown;
       private JComboBox<String> searchField;
       private JButton searchButton;
       private JTextField bookIdField;
       private JTextField bookNameField;
       private JTextField authorNamesField;
       private JTextField publicationField;
       private JTextField priceField;
       private JTextField quantityField;
       private JTextField totalCostField;
       private JTextField dateOfPublicationField;
       private JButton updateButton;
       private JButton deleteButton;
       private JFrame viewBookframe;
       private JButton backButton;
       private Book book;
       BookIoOperation bookOperation;

       public ViewPageCtrl(JFrame viewBookFrame) {
              this.viewBookframe = viewBookFrame;
              this.bookOperation = BookIoOperation.getBookOperation();
       }

       public void setFormPanel(JPanel formPanel) {
              this.formPanel = formPanel;
       }

       public void setSearchDropdown(JComboBox<String> searchDropdown) {
              this.searchDropdown = searchDropdown;
       }

       public void setSearchField(JComboBox<String> searchField) {
              this.searchField = searchField;
       }

       public void setSearchButton(JButton searchButton) {
              this.searchButton = searchButton;
       }

       public void setBookIdField(JTextField bookIdField) {
              this.bookIdField = bookIdField;
       }

       public void setBookNameField(JTextField bookNameField) {
              this.bookNameField = bookNameField;
       }

       public void setAuthorNamesField(JTextField authorNamesField) {
              this.authorNamesField = authorNamesField;
       }

       public void setPublicationField(JTextField publicationField) {
              this.publicationField = publicationField;
       }

       public void setPriceField(JTextField priceField) {
              this.priceField = priceField;
       }

       public void setQuantityField(JTextField quantityField) {
              this.quantityField = quantityField;
       }

       public void setTotalCostField(JTextField totalCostField) {
              this.totalCostField = totalCostField;
              this.totalCostField.setEditable(false);
       }

       public void setDateOfPublicationField(JTextField dateOfPublicationField) {
              this.dateOfPublicationField = dateOfPublicationField;
       }

       public void setUpdateButton(JButton updateButton) {
              this.updateButton = updateButton;
       }

       public void setDeleteButton(JButton deleteButton) {
              this.deleteButton = deleteButton;
       }

       public void setViewBookframe(JFrame viewBookframe) {
              this.viewBookframe = viewBookframe;
       }

       public void setBackButton(JButton backButton) {
              this.backButton = backButton;
       }

       @Override
       public void actionPerformed(ActionEvent e) {
              if (e.getSource() == this.searchButton) {
                     viewData();
              } else if (e.getSource() == this.backButton) {
                     this.viewBookframe.dispose();
                     HomePageBook homeFrame = new HomePageBook();
              } else if (e.getSource() == this.deleteButton) {
                     deleteData();
              }
       }

       public void deleteData() {
              List<Book> books = bookOperation.getBooks();
              if (book != null && books.size() > 0) {
                     books = Constant.deleteBook(books, book);
                     try {
                            bookOperation.writeBooks(books);
                            clearFields();
                            formPanel.setVisible(false);
                            Constant.showMessage("Book deleted successfully", viewBookframe);
                            getDataFromField();
                     } catch (IOException e) {
                            Constant.showMessage("Error deleting the book: " + e.getMessage(), viewBookframe);
                     }
              }
       }

       public void viewData() {
              String fieldName = (String) searchDropdown.getSelectedItem();
              String fieldData = (String) searchField.getSelectedItem();
              List<Book> books = bookOperation.getBooks();
              Book book = Constant.getBookDataFromField(books, fieldName, fieldData);
              if (book != null) {
                     bookIdField.setText(book.getBookId());
                     bookNameField.setText(book.getBookName());
                     authorNamesField.setText(book.getAuthorNames());
                     publicationField.setText(book.getPublication());
                     priceField.setText(String.valueOf(book.getPriceOfBook()));
                     quantityField.setText(String.valueOf(book.getTotalQuantityToOrder()));
                     totalCostField.setText(String.valueOf(book.getTotalCost()));
                     dateOfPublicationField.setText(Validation.dateFormatString(book.getDateOfPublication()));
                     formPanel.setVisible(true);
                     this.book = book;
              } else {
                     clearFields();
              }
       }

       public void getDataFromField() {
              List<Book> books = bookOperation.getBooks();
              String field = (String) searchDropdown.getSelectedItem();
              String[] fieldData = Constant.bookFieldCollection(books, field);
              if (fieldData != null && fieldData.length > 0) {
                     searchField.removeAllItems();
                     for (String data : fieldData) {
                            searchField.addItem(data);
                     }
              }
       }

       @Override
       public void itemStateChanged(ItemEvent e) {
              if (e.getStateChange() == ItemEvent.SELECTED) {
                     Object item = e.getItem();
                     getDataFromField();
              }
       }

       private void clearFields() {
              bookIdField.setText("");
              bookNameField.setText("");
              authorNamesField.setText("");
              publicationField.setText("");
              priceField.setText("");
              quantityField.setText("");
              totalCostField.setText("");
              dateOfPublicationField.setText("");
       }

       @Override
       public void insertUpdate(DocumentEvent e) {
              try {
                     if (e.getDocument() == quantityField.getDocument()) {
                            int price = Integer.parseInt(priceField.getText());
                            int quantity = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
                            totalCostField.setText(String.valueOf(Constant.calculateTotalPrice(price, quantity)));
                     } else if (e.getDocument() == priceField.getDocument()) {
                            int price = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
                            int quantity = Integer.parseInt(quantityField.getText());
                            totalCostField.setText(String.valueOf(Constant.calculateTotalPrice(price, quantity)));
                     }
              } catch (BadLocationException ex) {
                     System.out.println(ex.getMessage());
              } catch (NumberFormatException ex) {
                     // Constant.showMessage("Please enter a valid number", addPageFrame);
                     System.out.println(ex.getMessage());
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
              // TODO Auto-generated method stub
              // throw new UnsupportedOperationException("Unimplemented method
              // 'changedUpdate'");
       }

}
