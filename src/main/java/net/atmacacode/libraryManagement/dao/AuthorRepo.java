package net.atmacacode.libraryManagement.dao;

import net.atmacacode.libraryManagement.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
