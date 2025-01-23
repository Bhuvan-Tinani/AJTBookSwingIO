package com.book.view;

import javax.swing.*;
import javax.swing.border.*;

import com.book.controller.ViewPageCtrl;

import java.awt.*;
import java.awt.event.*;

public class ViewPageBook extends JFrame {
       private JComboBox<String> searchDropdown;
       private JComboBox<String> searchField; // Changed to JComboBox
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
       private JButton backButton;
       private JPanel formPanel;
       private ViewPageCtrl viewPage;

       // Custom colors
       private final Color PRIMARY_COLOR = new Color(41, 128, 185); // Blue
       private final Color SECONDARY_COLOR = new Color(44, 62, 80); // Dark Blue
       private final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Light Gray
       private final Color FIELD_BACKGROUND = Color.WHITE;
       private final Color DELETE_COLOR = new Color(231, 76, 60); // Red

       public ViewPageBook() {
              setTitle("Book Management System");
              setSize(800, 700);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null);
              getContentPane().setBackground(BACKGROUND_COLOR);
              setLayout(new BorderLayout(20, 20));

              // Main content panel with padding
              JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
              mainPanel.setBackground(BACKGROUND_COLOR);
              mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

              // Title Panel with Back Button
              JPanel titlePanel = createTitlePanel();
              mainPanel.add(titlePanel, BorderLayout.NORTH);

              // Search Panel
              JPanel searchPanel = createSearchPanel();
              mainPanel.add(searchPanel, BorderLayout.CENTER);

              // Form Panel
              formPanel = createFormPanel();
              mainPanel.add(formPanel, BorderLayout.SOUTH);

              add(mainPanel);

              // Initially hide the form panel
              toggleFormVisibility(false);
              viewPage = new ViewPageCtrl(this);
              viewPage.setFormPanel(formPanel);
              viewPage.setSearchDropdown(searchDropdown);
              viewPage.setSearchField(searchField);
              viewPage.setSearchButton(searchButton);
              viewPage.setBookIdField(bookIdField);
              viewPage.setBookNameField(bookNameField);
              viewPage.setAuthorNamesField(authorNamesField);
              viewPage.setPublicationField(publicationField);
              viewPage.setPriceField(priceField);
              viewPage.setQuantityField(quantityField);
              viewPage.setTotalCostField(totalCostField);
              viewPage.setDateOfPublicationField(dateOfPublicationField);
              viewPage.setUpdateButton(updateButton);
              viewPage.setDeleteButton(deleteButton);
              viewPage.setBackButton(backButton);
              viewPage.getDataFromField();
              searchDropdown.addItemListener(viewPage);
              searchButton.addActionListener(viewPage);
              priceField.getDocument().addDocumentListener(viewPage);
              quantityField.getDocument().addDocumentListener(viewPage);
              deleteButton.addActionListener(viewPage);
              backButton.addActionListener(viewPage);
       }

       private JPanel createTitlePanel() {
              JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
              titlePanel.setBackground(BACKGROUND_COLOR);

              // Back Button
              backButton = new JButton("Back");
              backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
              backButton.setBackground(PRIMARY_COLOR);
              backButton.setForeground(Color.WHITE);
              backButton.setPreferredSize(new Dimension(100, 35));
              backButton.setFocusPainted(false);
              backButton.setBorderPainted(false);
              backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

              // Add hover effect for Back button
              backButton.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseEntered(MouseEvent e) {
                            backButton.setBackground(PRIMARY_COLOR.darker());
                     }

                     @Override
                     public void mouseExited(MouseEvent e) {
                            backButton.setBackground(PRIMARY_COLOR);
                     }
              });

              // Title Label
              JLabel titleLabel = new JLabel("Book Management System");
              titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
              titleLabel.setForeground(SECONDARY_COLOR);

              titlePanel.add(backButton); // Add the Back button to the title panel
              titlePanel.add(titleLabel);

              return titlePanel;
       }

       private JPanel createSearchPanel() {
              JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
              searchPanel.setBackground(BACKGROUND_COLOR);

              // Create a smaller rounded border with title
              TitledBorder titledBorder = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(PRIMARY_COLOR, 1, true),
                            "Search Book");
              titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
              titledBorder.setTitleColor(PRIMARY_COLOR);
              searchPanel.setBorder(BorderFactory.createCompoundBorder(
                            titledBorder,
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

              String[] searchOptions = { "Book ID", "Book Name", "Author Name", "Publication" };
              searchDropdown = new JComboBox<>(searchOptions);
              styleComboBox(searchDropdown);
              searchDropdown.setPreferredSize(new Dimension(120, 25)); // Smaller combo box

              // Create a JComboBox for searchField instead of JTextField
              searchField = new JComboBox<>(searchOptions);
              styleComboBox(searchField);
              searchField.setPreferredSize(new Dimension(200, 25));

              searchButton = new JButton("Search");
              styleButton(searchButton, PRIMARY_COLOR);
              searchButton.setPreferredSize(new Dimension(90, 30)); // Smaller button

              searchPanel.add(searchDropdown);
              searchPanel.add(searchField); // Add the JComboBox here
              searchPanel.add(searchButton);

              return searchPanel;
       }

       private JPanel createFormPanel() {
              JPanel formContainer = new JPanel(new BorderLayout(10, 10));
              formContainer.setBackground(BACKGROUND_COLOR);

              // Form fields panel
              formPanel = new JPanel(new GridLayout(4, 4, 15, 15));
              formPanel.setBackground(BACKGROUND_COLOR);
              formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

              addFormField(formPanel, "Book ID:", bookIdField = new JTextField());
              addFormField(formPanel, "Book Name:", bookNameField = new JTextField());
              addFormField(formPanel, "Author Names:", authorNamesField = new JTextField());
              addFormField(formPanel, "Publication:", publicationField = new JTextField());
              addFormField(formPanel, "Price:", priceField = new JTextField());
              addFormField(formPanel, "Total Quantity:", quantityField = new JTextField());
              addFormField(formPanel, "Total Cost:", totalCostField = new JTextField());
              addFormField(formPanel, "Date of Publication:", dateOfPublicationField = new JTextField());

              formContainer.add(formPanel, BorderLayout.CENTER);

              // Buttons panel
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
              buttonPanel.setBackground(BACKGROUND_COLOR);

              updateButton = new JButton("Update");
              deleteButton = new JButton("Delete");

              styleButton(updateButton, PRIMARY_COLOR);
              styleButton(deleteButton, DELETE_COLOR);

              buttonPanel.add(updateButton);
              buttonPanel.add(deleteButton);

              formContainer.add(buttonPanel, BorderLayout.SOUTH);

              return formContainer;
       }

       private void addFormField(JPanel panel, String labelText, JTextField field) {
              JLabel label = new JLabel(labelText, JLabel.RIGHT);
              label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
              label.setForeground(SECONDARY_COLOR);

              styleTextField(field);

              panel.add(label);
              panel.add(field);
       }

       private void styleTextField(JTextField field) {
              field.setPreferredSize(new Dimension(200, 30));
              field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
              field.setBackground(FIELD_BACKGROUND);
              field.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(PRIMARY_COLOR, 1, true),
                            BorderFactory.createEmptyBorder(5, 7, 5, 7)));
       }

       private void styleButton(JButton button, Color backgroundColor) {
              button.setPreferredSize(new Dimension(120, 35));
              button.setBackground(backgroundColor);
              button.setForeground(Color.WHITE);
              button.setFont(new Font("Segoe UI", Font.BOLD, 14));
              button.setFocusPainted(false);
              button.setBorderPainted(false);
              button.setCursor(new Cursor(Cursor.HAND_CURSOR));

              // Add hover effect
              button.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseEntered(MouseEvent e) {
                            button.setBackground(backgroundColor.darker());
                     }

                     @Override
                     public void mouseExited(MouseEvent e) {
                            button.setBackground(backgroundColor);
                     }
              });
       }

       private void styleComboBox(JComboBox<String> comboBox) {
              comboBox.setPreferredSize(new Dimension(150, 30));
              comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
              comboBox.setBackground(FIELD_BACKGROUND);
              ((JComponent) comboBox.getRenderer()).setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 7));
       }

       // Method to toggle form visibility
       public void toggleFormVisibility(boolean isVisible) {
              formPanel.setVisible(isVisible);
              revalidate();
              repaint();
       }
}
