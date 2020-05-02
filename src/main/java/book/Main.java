package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);

        int num = (args.length == 0) ? 15 : Integer.parseInt(args[0]);

        generateRandomBooks(bookDao, num);

        printAllBooks(bookDao);
    }

    private static void printAllBooks(BookDao bookDao) {
        List<Book> list = bookDao.findAll();
        if ( ! list.isEmpty()) {
            for (Book book : list) {
                System.out.println(book);
            }
        }
    }

    private static void generateRandomBooks(BookDao bookDao, int num) {
        for (int i = 0; i < num; i++) {
            Book book = new BookGenerator().getRandomBook();
            bookDao.persist(book);
        }
    }
}
