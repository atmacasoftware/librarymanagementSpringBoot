package net.atmacacode.libraryManagement.business.concretes;

import net.atmacacode.libraryManagement.business.abstracts.ICategoryService;
import net.atmacacode.libraryManagement.core.exception.NotFoundException;
import net.atmacacode.libraryManagement.core.utilies.Msg;
import net.atmacacode.libraryManagement.dao.CategoryRepo;
import net.atmacacode.libraryManagement.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(Long id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public boolean delete(Long id) {
        Category author = this.get(id);
        this.categoryRepo.delete(author);
        return true;
    }
}
