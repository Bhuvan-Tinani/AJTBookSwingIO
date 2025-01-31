package book_collection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SaveBookData {
       Book book;
       OutputStream fos;
       ObjectOutputStream oos;
       File f;
       static final String FILENAME = "book.dat";

       public SaveBookData(Book book) throws IOException {
              this.book = book;
              File f = new File(FILENAME);
              if (f.createNewFile()) {
                     System.out.println("New File created");
              }
              this.fos = new FileOutputStream(f);
              this.oos = new ObjectOutputStream(fos);
              saveBook();
       }

       private void saveBook() throws IOException {
              oos.writeObject(book);
              oos.flush();
              oos.close();
       }
}
