package com.dsalgado.cqrs.infrastructure.repository;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.repository.BlogRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

@Component("MySqlBlogRepository")
@EnableAutoConfiguration
public class MySqlBlogRepository implements BlogRepository {

  @Autowired private MySqlBlogCrudRepository mySqlBlogCrudRepository;

  @Override
  public void save(Blog blog) {
    mySqlBlogCrudRepository.save(BlogEntity.blogToEntity(blog));
  }

  @Override
  public Blog find(BlogId blogId) {
    Optional<BlogEntity> returned = mySqlBlogCrudRepository.findById(blogId.getValue().toString());
    if (returned.isPresent()) {
      return returned.get().entityToBlog();
    }
    throw new RuntimeException("Blog not found");
  }

  @Override
  public void update(Blog blog) {
    mySqlBlogCrudRepository.save(BlogEntity.blogToEntity(blog));
  }

  @Override
  public void delete(BlogId blogID) {
    BlogEntity blogEntity = BlogEntity.blogToEntity(find(blogID));
    mySqlBlogCrudRepository.delete(blogEntity);
  }
}
