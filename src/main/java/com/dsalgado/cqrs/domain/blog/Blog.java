package com.dsalgado.cqrs.domain.blog;

public class Blog {
  private final BlogId id;
  private final BlogTitle title;
  private final BlogType type;
  private final BlogBrief brief;
  private final BlogUrl url;

  public Blog(BlogId id, BlogTitle title, BlogType type, BlogBrief brief, BlogUrl url) {
    this.id = id;
    this.title = title;
    this.type = type;
    this.brief = brief;
    this.url = url;
  }

  public static Blog create(
      BlogId id, BlogTitle title, BlogType type, BlogBrief brief, BlogUrl url) {
    // TODO: Domain event for create blog
    return new Blog(id, title, type, brief, url);
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
}
