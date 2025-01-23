package com.book.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
       public static boolean validateDate(String date) {
              if (date.length() != 10) {
                     return false;
              }
              SimpleDateFormat osdf = new SimpleDateFormat("dd-MM-yyyy");
              osdf.setLenient(false);
              try {
                     osdf.parse(date);
                     return true;
              } catch (ParseException e) {
                     return false;
              }
       }

       public static String dateFormatString(Date date) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
              String formattedDate = dateFormat.format(date);
              return formattedDate;
       }
}
