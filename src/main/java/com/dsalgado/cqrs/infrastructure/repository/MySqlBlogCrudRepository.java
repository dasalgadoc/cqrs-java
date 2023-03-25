package com.dsalgado.cqrs.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MySqlBlogCrudRepository extends CrudRepository<BlogEntity, String> {
  Optional<BlogEntity> findById(String name);
}
