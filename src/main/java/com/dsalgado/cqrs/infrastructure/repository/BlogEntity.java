package com.dsalgado.cqrs.infrastructure.repository;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogBrief;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.blog.BlogTitle;
import com.dsalgado.cqrs.domain.blog.BlogType;
import com.dsalgado.cqrs.domain.blog.BlogUrl;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Table(name = "blogs")
@EnableAutoConfiguration
public class BlogEntity {

  @Id private String id;
  private String title;
  private String type;
  private String brief;
  private String url;

  public BlogEntity() {}

  public BlogEntity(String id, String title, String type, String brief, String url) {
    this.id = id;
    this.title = title;
    this.type = type;
    this.brief = brief;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBrief() {
    return brief;
  }

  public void setBrief(String brief) {
    this.brief = brief;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Blog entityToBlog() {
    BlogId blogId = new BlogId(id);
    BlogTitle blogTitle = new BlogTitle(title);
    BlogType blogType = new BlogType(type);
    BlogBrief blogBrief = new BlogBrief(brief);
    BlogUrl blogUrl = new BlogUrl(url);
    return new Blog(blogId, blogTitle, blogType, blogBrief, blogUrl);
  }

  public static BlogEntity blogToEntity(Blog blog) {
    return new BlogEntity(
        blog.getId().getValue().toString(),
        blog.getTitle().getValue(),
        blog.getType().getValue().toString(),
        blog.getBrief().getValue(),
        blog.getUrl().getValue());
  }
}
