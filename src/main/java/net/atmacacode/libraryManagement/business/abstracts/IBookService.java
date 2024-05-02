package net.atmacacode.libraryManagement.business.abstracts;

import net.atmacacode.libraryManagement.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);

    Book get(Long id);

    Page<Book> cursor(int page, int pageSize);

    Book update(Book book);

    boolean delete(Long id);
}
