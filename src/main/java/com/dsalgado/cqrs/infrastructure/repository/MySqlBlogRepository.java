package com.dsalgado.cqrs.infrastructure.repository;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.repository.BlogRepository;
import org.springframework.stereotype.Component;

@Component("MySqlBlogRepository")
public class MySqlBlogRepository implements BlogRepository {
  @Override
  public void save(Blog blog) {
    System.out.println("Guardar sin implementar");
  }

  @Override
  public Blog find(BlogId blogId) {
    throw new RuntimeException("Not implemented yet");
  }

  @Override
  public void update(Blog blog) {
    throw new RuntimeException("Not implemented yet");
  }

  @Override
  public void delete(BlogId blogID) {
    throw new RuntimeException("Not implemented yet");
  }
}
