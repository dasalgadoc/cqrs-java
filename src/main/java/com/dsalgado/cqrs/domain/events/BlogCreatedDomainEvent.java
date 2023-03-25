package com.dsalgado.cqrs.domain.events;

import com.dsalgado.cqrs.application.CreateBlogCommand;
import com.dsalgado.cqrs.domain.blog.Blog;

public class BlogCreatedDomainEvent extends DomainEvent {
  public static final String EVENT_NAME = "blog_created";
  private CreateBlogCommand blogDto;

  public BlogCreatedDomainEvent(Blog blog) {
    blogDto =
        new CreateBlogCommand(
            blog.getId().getValue().toString(),
            blog.getTitle().getValue(),
            blog.getType().getValue().toString(),
            blog.getBrief().getValue(),
            blog.getUrl().getValue());
  }

  @Override
  public String eventName() {
    return BlogCreatedDomainEvent.EVENT_NAME;
  }
}
