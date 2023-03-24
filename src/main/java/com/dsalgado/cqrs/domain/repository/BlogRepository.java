package com.dsalgado.cqrs.domain.repository;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogId;

public interface BlogRepository {
  void save(Blog blog);

  Blog find(BlogId blogId);

  void update(Blog blog);

  void delete(BlogId blogID);
}
