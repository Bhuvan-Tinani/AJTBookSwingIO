package com.book;

import javax.swing.SwingUtilities;

import com.book.view.HomePageBook;

public class BookMain {
       public static void main(String[] args) {
              SwingUtilities.invokeLater(HomePageBook::new);
       }
}
