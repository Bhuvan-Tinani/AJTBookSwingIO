package com.book.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.book.controller.ListPageCtrl;

import java.awt.*;

public class GenerateListBooks {

       // Static table model and table (if needed for global access)
       private static JTable table;
       private static DefaultTableModel model;
       private static ListPageCtrl ctrl;

       public static JPanel generateViewPanel(JFrame frame) {
              // Create a panel with BorderLayout
              JPanel panel = new JPanel(new BorderLayout());
              panel.setBackground(new Color(236, 240, 241)); // Light gray background

              // Initialize the table model with column names
              model = new DefaultTableModel();
              model.addColumn("Book ID");
              model.addColumn("Book Name");
              model.addColumn("Author Names");
              model.addColumn("Publication");
              model.addColumn("Price");
              model.addColumn("Total Quantity");
              model.addColumn("Total Cost");
              model.addColumn("Date of Publication");

              // Initialize the JTable
              table = new JTable(model);

              // Customize table appearance
              table.setFillsViewportHeight(true);
              table.setRowHeight(30); // Increase row height for better visibility
              table.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set font for table content
              table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14)); // Header font
              table.getTableHeader().setBackground(new Color(41, 128, 185)); // Header background color
              table.getTableHeader().setForeground(Color.WHITE); // Header text color

              // Add the table to a scroll pane
              JScrollPane scrollPane = new JScrollPane(table);
              panel.add(scrollPane, BorderLayout.CENTER);
              ctrl = new ListPageCtrl(frame, model);
              // reloadData();

              return panel;
       }

       public static void reloadData() {
              // System.out.println(ctrl);
              if (ctrl != null) {
                     ctrl.setTableData();
              }
       }

       // Static method to add data to the table
       public static void addBook(String bookId, String bookName, String authorNames, String publication,
                     double price, int totalQuantity, double totalCost, String dateOfPublication) {
              if (model != null) {
                     model.addRow(new Object[] { bookId, bookName, authorNames, publication, price, totalQuantity,
                                   totalCost, dateOfPublication });
              }
       }
}
