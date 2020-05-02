package book;

import book.model.Book;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BookGenerator {
    private static final Faker faker = new Faker();

    public Book getRandomBook() {
        Book book = new Book();
        book.setIsbn13(faker.code().isbn13());
        book.setAuthor(faker.book().author());
        book.setTitle(faker.book().title());
        book.setFormat(faker.options().option(Book.Format.HARDBACK, Book.Format.PAPERBACK));
        book.setPublisher(faker.book().publisher());
        book.setPublicationDate(toLocalDate(faker.date().birthday()));
        book.setPages((int) Math.abs(faker.number().randomNumber(3, false) + 1));
        book.setAvailable(faker.bool().bool());
        return book;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
