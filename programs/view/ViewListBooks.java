package com.book.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.book.controller.ListPageCtrl;

public class ViewListBooks extends JFrame {
       private JTable bookTable;
       private DefaultTableModel bookTableModel;
       private ListPageCtrl listPageCtrl;
       private JButton backButton;

       public ViewListBooks() {
              setTitle("Book List");
              setSize(1000, 600);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null);
              setLayout(new BorderLayout());

              bookTableModel = new DefaultTableModel();
              bookTable = new JTable(bookTableModel);

              bookTableModel.addColumn("Book ID");
              bookTableModel.addColumn("Book Name");
              bookTableModel.addColumn("Author Names");
              bookTableModel.addColumn("Publication");
              bookTableModel.addColumn("Price");
              bookTableModel.addColumn("Total Quantity");
              bookTableModel.addColumn("Total Cost");
              bookTableModel.addColumn("Date of Publication");

              JScrollPane scrollPane = new JScrollPane(bookTable);
              add(scrollPane, BorderLayout.CENTER);

              backButton = new JButton("< Back");

              JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
              topPanel.add(backButton);
              add(topPanel, BorderLayout.NORTH);
              listPageCtrl = new ListPageCtrl(this, bookTableModel);
              listPageCtrl.setBackButton(backButton);
              backButton.addActionListener(listPageCtrl);

       }
}
