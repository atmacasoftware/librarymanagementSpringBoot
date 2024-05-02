package net.atmacacode.libraryManagement.business.abstracts;

import net.atmacacode.libraryManagement.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);

    Publisher get(Long id);

    Page<Publisher> cursor(int page, int pageSize);

    Publisher update(Publisher publisher);

    boolean delete(Long id);
}
