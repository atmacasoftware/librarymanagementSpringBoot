package net.atmacacode.libraryManagement.business.abstracts;

import net.atmacacode.libraryManagement.entities.Author;
import org.springframework.data.domain.Page;


public interface IAuthorService {
    Author save(Author author);

    Author get(Long id);

    Page<Author> cursor(int page, int pageSize);

    Author update(Author author);

    boolean delete(Long id);
}
