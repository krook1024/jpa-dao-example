package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);

        int num = (args.length == 0) ? 1000 : Integer.parseInt(args[0]);

        generateRandomBooks(bookDao, num);

        printAllBooks(bookDao);
    }

    private static void printAllBooks(BookDao bookDao) {
        List<Book> list = bookDao.findAll();
        list.forEach(System.out::println);
    }

    private static void generateRandomBooks(BookDao bookDao, int num) {
        IntStream.range(0, num).mapToObj(i -> new BookGenerator().getRandomBook()).forEach(bookDao::persist);
    }
}
