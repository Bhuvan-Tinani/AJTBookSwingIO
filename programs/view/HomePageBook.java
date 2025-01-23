package com.book.view;

import javax.swing.*;

import com.book.controller.HomePageCtrl;

import java.awt.*;

public class HomePageBook extends JFrame {
       private JButton addBookButton;
       private JButton listBooksButton;
       private JButton viewBookButton;
       HomePageCtrl controller;

       public HomePageBook() {
              setTitle("Book Collection");
              setSize(700, 500);
              setLocationRelativeTo(null);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              addComponents();
              setVisible(true);
       }

       private void addComponents() {
              setLayout(new BorderLayout(10, 10));
              JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
              mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

              // Header section
              JLabel heading = new JLabel("Book Collection", SwingConstants.CENTER);
              heading.setFont(new Font("Arial", Font.BOLD, 36));
              heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
              mainPanel.add(heading, BorderLayout.NORTH);

              // Button panel
              JPanel buttonPanel = new JPanel(new GridBagLayout());
              GridBagConstraints gbc = new GridBagConstraints();
              gbc.insets = new Insets(10, 10, 10, 10);
              gbc.fill = GridBagConstraints.BOTH;

              addBookButton = createButton("Add Book", buttonPanel, gbc, 0);
              listBooksButton = createButton("List of Books", buttonPanel, gbc, 1);
              viewBookButton = createButton("View Book", buttonPanel, gbc, 2);

              mainPanel.add(buttonPanel, BorderLayout.CENTER);
              add(mainPanel, BorderLayout.CENTER);
              controller = new HomePageCtrl(this, addBookButton);
              controller.setListbtn(listBooksButton);
              addBookButton.addActionListener(controller);
              listBooksButton.addActionListener(controller);
              controller.setViewBtn(viewBookButton);
              viewBookButton.addActionListener(controller);
       }

       private JButton createButton(String text, JPanel panel, GridBagConstraints gbc, int yPos) {
              gbc.gridx = 0;
              gbc.gridy = yPos;
              JButton button = new JButton(text);
              button.setFont(new Font("Arial", Font.PLAIN, 18));
              button.setPreferredSize(new Dimension(200, 50));
              // button.addActionListener(this);
              panel.add(button, gbc);
              return button;
       }
}
