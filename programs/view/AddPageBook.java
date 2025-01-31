package com.book.view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import com.book.controller.AddPageCtrl;
import com.book.view.GenerateListBooks;

public class AddPageBook extends JFrame {
       // UI Components
       private JTextField bookIdField;
       private JTextField bookNameField;
       private JTextField authorNamesField;
       private JTextField publicationField;
       private JTextField priceOfBookField;
       private JTextField totalQuantityField;
       private JTextField totalCostField;
       private JTextField dateOfPublicationField;
       private JButton backButton;
       private JButton submitButton, deleteButton, updateButton;
       private JComboBox<String> modeDropdown;

       // Custom colors
       private final Color PRIMARY_COLOR = new Color(41, 128, 185); // Blue
       private final Color SECONDARY_COLOR = Color.BLACK; // Changed to black for better visibility
       private final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Light Gray
       private final Color FIELD_BACKGROUND = Color.WHITE;
       private final Color DISABLED_BACKGROUND = new Color(240, 240, 240); // Light gray for disabled fields

       // Controller
       private AddPageCtrl controller;

       public AddPageBook() {
              setTitle("Add New Book");
              setSize(900, 800);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null);
              getContentPane().setBackground(BACKGROUND_COLOR);
              // setLayout(new BorderLayout(20, 20));

              // Main content panel with padding
              JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
              mainPanel.setBackground(BACKGROUND_COLOR);
              // mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

              // Add components
              mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
              mainPanel.add(createFormPanel(), BorderLayout.CENTER);
              mainPanel.add(createSouthPanel(), BorderLayout.SOUTH);
              // mainPanel.add(GenerateListBooks.generateViewPanel(), BorderLayout.SOUTH);

              add(mainPanel);
              // add(GenerateListBooks.generateViewPanel());

              // Initialize controller
              initializeController();

              // Make frame visible
              setVisible(true);
       }

       private JPanel createTitlePanel() {
              JPanel titlePanel = new JPanel(new BorderLayout());
              titlePanel.setBackground(BACKGROUND_COLOR);

              // Title label
              JLabel titleLabel = new JLabel("Book Management");
              titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
              titleLabel.setForeground(SECONDARY_COLOR);

              // Dropdown for mode selection
              modeDropdown = new JComboBox<>(new String[] { "Add Mode", "Update Mode" });
              modeDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 16));
              modeDropdown.setPreferredSize(new Dimension(150, 30));
              modeDropdown.setBackground(FIELD_BACKGROUND);
              modeDropdown.setForeground(SECONDARY_COLOR);
              titlePanel.add(titleLabel, BorderLayout.WEST);
              titlePanel.add(modeDropdown, BorderLayout.EAST);

              return titlePanel;
       }

       private JPanel createFormPanel() {
              JPanel formContainer = new JPanel(new BorderLayout(10, 10));
              formContainer.setBackground(BACKGROUND_COLOR);

              // Create titled border
              /*
               * TitledBorder titledBorder = BorderFactory.createTitledBorder(
               * BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
               * "Book Details");
               * titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
               * titledBorder.setTitleColor(PRIMARY_COLOR);
               */

              // Form fields panel
              JPanel formPanel = new JPanel(new GridBagLayout());
              formPanel.setBackground(BACKGROUND_COLOR);
              // formPanel.setBorder(BorderFactory.createCompoundBorder(titledBorder,
              // BorderFactory.createEmptyBorder(10, 10, 10, 10)));

              GridBagConstraints gbc = new GridBagConstraints();
              gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing

              // Initialize text fields
              bookIdField = new JTextField();
              bookNameField = new JTextField();
              authorNamesField = new JTextField();
              publicationField = new JTextField();
              priceOfBookField = new JTextField();
              totalQuantityField = new JTextField();
              totalCostField = new JTextField();
              dateOfPublicationField = new JTextField();

              // Disable bookId and totalCost fields
              bookIdField.setEditable(false);
              bookIdField.setBackground(DISABLED_BACKGROUND);
              bookIdField.setForeground(SECONDARY_COLOR);

              totalCostField.setEditable(false);
              totalCostField.setBackground(DISABLED_BACKGROUND);
              totalCostField.setForeground(SECONDARY_COLOR);

              // Set default values for price and quantity
              priceOfBookField.setText("0");
              totalQuantityField.setText("0");

              addFormField(formPanel, "Book ID:", bookIdField, gbc, 0, 0);
              addFormField(formPanel, "Book Name:", bookNameField, gbc, 1, 0);
              addFormField(formPanel, "Author Names:", authorNamesField, gbc, 0, 1);
              addFormField(formPanel, "Publication:", publicationField, gbc, 1, 1);
              addFormField(formPanel, "Price:", priceOfBookField, gbc, 0, 2);
              addFormField(formPanel, "Total Quantity:", totalQuantityField, gbc, 1, 2);
              addFormField(formPanel, "Total Cost:", totalCostField, gbc, 0, 3);
              addFormField(formPanel, "DoP:", dateOfPublicationField, gbc, 1, 3);

              formContainer.add(formPanel, BorderLayout.CENTER);
              return formContainer;
       }

       private void addFormField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc, int gridx,
                     int gridy) {
              JLabel label = new JLabel(labelText, JLabel.RIGHT);
              label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
              label.setForeground(SECONDARY_COLOR);

              styleTextField(field);

              // Add label
              gbc.gridx = gridx * 2; // Ensure labels and fields occupy separate columns
              gbc.gridy = gridy;
              gbc.anchor = GridBagConstraints.LINE_END; // Align label to the right
              gbc.fill = GridBagConstraints.NONE; // Don't stretch the label
              panel.add(label, gbc);

              // Add text field
              gbc.gridx++; // Move to the next cell for the text field
              gbc.anchor = GridBagConstraints.LINE_START; // Align text field to the left
              gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to stretch horizontally
              gbc.weightx = 1.0; // Allow the text field to take extra space
              panel.add(field, gbc);

              // Reset weightx for subsequent components
              gbc.weightx = 0;
       }

       private void styleTextField(JTextField field) {
              // Increase the size of the text field
              field.setPreferredSize(new Dimension(300, 40)); // Width: 300, Height: 40
              field.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Slightly larger font size for better visibility

              // Don't override background for disabled fields
              if (field.isEditable()) {
                     field.setBackground(FIELD_BACKGROUND);
              }

              field.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(PRIMARY_COLOR, 1, true),
                            BorderFactory.createEmptyBorder(5, 7, 5, 7) // Padding inside the text field
              ));
       }

       private JPanel createButtonPanel() {
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
              buttonPanel.setBackground(BACKGROUND_COLOR);

              backButton = new JButton("Back");
              submitButton = new JButton("Submit");
              deleteButton = new JButton("Delete");
              updateButton = new JButton("Update");

              styleButton(backButton, PRIMARY_COLOR);
              styleButton(submitButton, new Color(46, 204, 113)); // Green color for submit
              styleButton(deleteButton, PRIMARY_COLOR);
              styleButton(updateButton, PRIMARY_COLOR);
              buttonPanel.add(backButton);
              buttonPanel.add(submitButton);
              buttonPanel.add(deleteButton);
              buttonPanel.add(updateButton);

              return buttonPanel;
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
              button.addMouseListener(new java.awt.event.MouseAdapter() {
                     public void mouseEntered(java.awt.event.MouseEvent evt) {
                            button.setBackground(backgroundColor.darker());
                     }

                     public void mouseExited(java.awt.event.MouseEvent evt) {
                            button.setBackground(backgroundColor);
                     }
              });
       }

       private JPanel createSouthPanel() {
              JPanel southPanel = new JPanel();
              southPanel.setLayout(new BorderLayout()); // Nesting components vertically

              // Add button panel to the north of this panel
              southPanel.add(createButtonPanel(), BorderLayout.NORTH);

              // Add view panel below the button panel
              southPanel.add(GenerateListBooks.generateViewPanel(this), BorderLayout.CENTER);

              return southPanel;
       }

       private void initializeController() {
              // Initialize controller with all components
              controller = new AddPageCtrl(this, bookIdField, backButton, submitButton);

              // Set additional fields in controller
              controller.setBookNameField(bookNameField);
              controller.setAuthorNamesField(authorNamesField);
              controller.setPublicationField(publicationField);
              controller.setPriceOfBookField(priceOfBookField);
              controller.setTotalQuantityField(totalQuantityField);
              controller.setTotalCostField(totalCostField);
              controller.setDateOfPublicationField(dateOfPublicationField);
              controller.setModeDropdown(modeDropdown);
              controller.setDeleteButton(deleteButton);
              controller.setUpdateButton(updateButton);
              deleteButton.addActionListener(controller);
              updateButton.addActionListener(controller);
              JTable table = GenerateListBooks.getTable();
              controller.setViewBookTable(table);
              table.addMouseListener(controller);

              // Add action listeners
              backButton.addActionListener(controller);
              submitButton.addActionListener(controller);
              modeDropdown.addItemListener(controller);

              // Add document listeners for real-time validation
              priceOfBookField.getDocument().addDocumentListener(controller);
              totalQuantityField.getDocument().addDocumentListener(controller);
              dateOfPublicationField.getDocument().addDocumentListener(controller);
       }
}
