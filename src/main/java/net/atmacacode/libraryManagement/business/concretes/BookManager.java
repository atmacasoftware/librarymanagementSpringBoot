package net.atmacacode.libraryManagement.business.concretes;

import net.atmacacode.libraryManagement.business.abstracts.IBookService;
import net.atmacacode.libraryManagement.core.exception.NotFoundException;
import net.atmacacode.libraryManagement.core.utilies.Msg;
import net.atmacacode.libraryManagement.dao.BookRepo;
import net.atmacacode.libraryManagement.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book category) {
        return this.bookRepo.save(category);
    }

    @Override
    public Book get(Long id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(Long id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }
}
