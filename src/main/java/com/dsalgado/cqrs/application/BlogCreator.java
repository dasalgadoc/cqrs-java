package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogBrief;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.blog.BlogTitle;
import com.dsalgado.cqrs.domain.blog.BlogType;
import com.dsalgado.cqrs.domain.blog.BlogUrl;
import com.dsalgado.cqrs.domain.repository.BlogRepository;
import com.dsalgado.cqrs.domain.repository.BlogRepositoryFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BlogCreator {

  @Resource private BlogRepositoryFactory blogRepositoryFactory;
  private BlogRepository blogRepository;

  @PostConstruct
  public void initializer() {
    blogRepository = blogRepositoryFactory.getBlogRepository();
  }

  public void create(BlogId id, BlogTitle title, BlogType type, BlogBrief brief, BlogUrl url) {
    Blog blog = Blog.create(id, title, type, brief, url);

    blogRepository.save(blog);

    // TODO Publish domain events
  }
}
