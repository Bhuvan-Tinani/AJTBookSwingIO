package com.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.book.view.AddPageBook;
import com.book.view.ViewListBooks;
import com.book.view.ViewPageBook;

public class HomePageCtrl implements ActionListener {
       private JFrame homeFrame;
       private JButton addBtn, listbtn, viewBtn;

       public JButton getViewBtn() {
              return viewBtn;
       }

       public void setViewBtn(JButton viewBtn) {
              this.viewBtn = viewBtn;
       }

       public JButton getListbtn() {
              return listbtn;
       }

       public void setListbtn(JButton listbtn) {
              this.listbtn = listbtn;
       }

       public HomePageCtrl(JFrame homeFrame, JButton addBtn) {
              this.homeFrame = homeFrame;
              this.addBtn = addBtn;
       }

       public void actionPerformed(ActionEvent e) {
              if (e.getSource() == addBtn) {
                     AddPageBook addPage = new AddPageBook();
                     this.homeFrame.dispose();
              } else if (e.getSource() == listbtn) {
                     ViewListBooks vsb = new ViewListBooks();
                     vsb.setVisible(true);
                     this.homeFrame.dispose();
              } else if (e.getSource() == viewBtn) {
                     ViewPageBook vpb = new ViewPageBook();
                     vpb.setVisible(true);
                     this.homeFrame.dispose();
              }
       }

}
