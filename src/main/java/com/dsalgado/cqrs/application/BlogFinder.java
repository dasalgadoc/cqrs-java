package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.repository.BlogRepository;
import com.dsalgado.cqrs.domain.repository.BlogRepositoryFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BlogFinder {
  @Resource private BlogRepositoryFactory blogRepositoryFactory;
  private BlogRepository blogRepository;

  @PostConstruct
  public void initializer() {
    blogRepository = blogRepositoryFactory.getBlogRepository();
  }

  public Blog get(BlogId id) {
    return blogRepository.find(id);
  }
}
