package net.atmacacode.libraryManagement.business.concretes;

import net.atmacacode.libraryManagement.business.abstracts.IAuthorService;
import net.atmacacode.libraryManagement.core.exception.NotFoundException;
import net.atmacacode.libraryManagement.core.utilies.Msg;
import net.atmacacode.libraryManagement.dao.AuthorRepo;
import net.atmacacode.libraryManagement.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {
    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(Long id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepo.findAll(pageable);
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(Long id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}
