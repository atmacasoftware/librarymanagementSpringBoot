package net.atmacacode.libraryManagement.business.abstracts;

import net.atmacacode.libraryManagement.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);

    Category get(Long id);

    Page<Category> cursor(int page, int pageSize);

    Category update(Category category);

    boolean delete(Long id);
}
