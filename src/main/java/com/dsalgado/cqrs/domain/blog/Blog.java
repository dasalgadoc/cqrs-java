package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.events.BlogCreatedDomainEvent;
import com.dsalgado.cqrs.domain.shared.AggregateRoot;
import java.util.ArrayList;

public class Blog extends AggregateRoot {
  private final BlogId id;
  private final BlogTitle title;
  private final BlogType type;
  private final BlogBrief brief;
  private final BlogUrl url;

  public Blog(BlogId id, BlogTitle title, BlogType type, BlogBrief brief, BlogUrl url) {
    super(new ArrayList<>());
    this.id = id;
    this.title = title;
    this.type = type;
    this.brief = brief;
    this.url = url;
  }

  public static Blog create(
      BlogId id, BlogTitle title, BlogType type, BlogBrief brief, BlogUrl url) {
    Blog newBlog = new Blog(id, title, type, brief, url);
    newBlog.recordEvent(new BlogCreatedDomainEvent(newBlog));

    return newBlog;
  }

  public BlogId getId() {
    return id;
  }

  public BlogTitle getTitle() {
    return title;
  }

  public BlogType getType() {
    return type;
  }

  public BlogBrief getBrief() {
    return brief;
  }

  public BlogUrl getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "Blog{"
        + "id="
        + id.getValue()
        + ", title="
        + title.getValue()
        + ", type="
        + type.getValue()
        + ", brief="
        + brief.getValue()
        + ", url="
        + url.getValue()
        + '}';
  }
}
