package book_collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddBook extends JFrame implements ActionListener {
       private JButton BackButton;
       private JButton submitButton;
       private JTextField bookIdField;
       private JTextField bookNameField;
       private JTextField authorNamesField;
       private JTextField publicationField;
       private JTextField dateOfPublicationField;
       private JTextField priceOfBookField;
       private JTextField totalQuantityField;
       private JTextField totalCostField;

       public AddBook() {
              setTitle("Book Entry Form");
              setSize(600, 550);
              setLocationRelativeTo(null);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              addComponent();
              setVisible(true);
       }

       private void addComponent() {
              JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
              mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

              // Header section
              JPanel headerPanel = new JPanel(new BorderLayout());
              BackButton = new JButton("< Back");
              BackButton.addActionListener(this);
              headerPanel.add(BackButton, BorderLayout.WEST);

              JLabel headingLabel = new JLabel("Add a Book", SwingConstants.CENTER);
              headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
              headingLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
              headerPanel.add(headingLabel, BorderLayout.CENTER);

              mainPanel.add(headerPanel, BorderLayout.NORTH);

              // Form section
              JPanel formPanel = new JPanel(new GridBagLayout());
              formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
              GridBagConstraints gbc = new GridBagConstraints();
              gbc.insets = new Insets(5, 5, 5, 5);
              gbc.fill = GridBagConstraints.HORIZONTAL;

              bookIdField = addLabelAndField("Book ID:", formPanel, gbc, 0);
              bookNameField = addLabelAndField("Book Name:", formPanel, gbc, 1);
              authorNamesField = addLabelAndField("Author Names:", formPanel, gbc, 2);
              publicationField = addLabelAndField("Publication:", formPanel, gbc, 3);
              dateOfPublicationField = addLabelAndField("Date of Publication:", formPanel, gbc, 4);
              priceOfBookField = addLabelAndField("Price of Book:", formPanel, gbc, 5);
              totalQuantityField = addLabelAndField("Total Quantity to Order:", formPanel, gbc, 6);
              totalCostField = addLabelAndField("Total Cost:", formPanel, gbc, 7);

              mainPanel.add(formPanel, BorderLayout.CENTER);

              // Submit button section
              submitButton = new JButton("Submit");
              submitButton.setFont(new Font("Arial", Font.BOLD, 18));
              submitButton.addActionListener(this);
              JPanel buttonPanel = new JPanel();
              buttonPanel.add(submitButton);
              mainPanel.add(buttonPanel, BorderLayout.SOUTH);

              add(mainPanel);
       }

       private JTextField addLabelAndField(String label, JPanel panel, GridBagConstraints gbc, int yPos) {
              gbc.gridx = 0;
              gbc.gridy = yPos;
              gbc.gridwidth = 1;
              JLabel jLabel = new JLabel(label);
              jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
              panel.add(jLabel, gbc);

              gbc.gridx = 1;
              gbc.gridwidth = 2;
              JTextField textField = new JTextField();
              textField.setPreferredSize(new Dimension(200, 30));
              panel.add(textField, gbc);
              return textField;
       }

       public void actionPerformed(ActionEvent e) {
              if (e.getSource() == BackButton) {
                     this.dispose();
                     new BookEntry();
              } else if (e.getSource() == submitButton) {
                     try {
                            String bookId = bookIdField.getText();
                            String bookName = bookNameField.getText();
                            String authorNames = authorNamesField.getText();
                            String publication = publicationField.getText();
                            String dateOfPublication = dateOfPublicationField.getText();
                            int priceOfBook = Integer.parseInt(priceOfBookField.getText());
                            int totalQuantityToOrder = Integer.parseInt(totalQuantityField.getText());
                            int totalCost = Integer.parseInt(totalCostField.getText());
                            Book book = new Book(bookId, bookName, authorNames, publication, dateOfPublication,
                                          priceOfBook, totalQuantityToOrder, totalCost);
                            saveData(book);
                     } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this,
                                          "Please enter valid numbers for price, quantity, and cost.", "Input Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              }
       }

       private void saveData(Book book) {
              try {
                     SaveBookData saveBookData = new SaveBookData(book);
                     showMessage("Book Saved to collection");
                     clearData();
              } catch (IOException e) {
                     showMessage(e.getMessage());
              }
       }

       public void showMessage(String message) {
              JOptionPane.showMessageDialog(this, message);
       }

       private void clearData() {
              bookIdField.setText("");
              bookNameField.setText("");
              authorNamesField.setText("");
              publicationField.setText("");
              dateOfPublicationField.setText("");
              priceOfBookField.setText("");
              totalQuantityField.setText("");
              totalCostField.setText("");
       }

       public static void main(String[] args) {
              SwingUtilities.invokeLater(AddBook::new);
       }
}