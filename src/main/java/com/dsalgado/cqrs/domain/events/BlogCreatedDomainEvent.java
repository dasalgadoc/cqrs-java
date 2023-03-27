package com.dsalgado.cqrs.domain.events;

import com.dsalgado.cqrs.application.blog.CreateBlogCommand;
import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.shared.DateTimePatterns;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Date;

@JsonTypeName("BlogCreatedDomainEvent")
public class BlogCreatedDomainEvent extends DomainEvent {
  @JsonIgnore public static final String EVENT_NAME = "BlogCreatedDomainEvent";

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimePatterns.RFC_3339)
  @JsonProperty("create_date")
  private Date createDate;

  @JsonProperty("blog_data")
  private CreateBlogCommand blogDto;

  @JsonCreator
  public BlogCreatedDomainEvent(
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimePatterns.RFC_3339)
          @JsonProperty("create_date")
          Date createDate,
      @JsonProperty("blog_data") CreateBlogCommand blogDto) {
    super(EVENT_NAME);
    this.createDate = createDate;
    this.blogDto = blogDto;
  }

  @JsonIgnore
  public BlogCreatedDomainEvent(Blog blog) {
    super(EVENT_NAME);
    this.createDate = new Date();
    blogDto =
        new CreateBlogCommand(
            blog.getId().getValue().toString(),
            blog.getTitle().getValue(),
            blog.getType().getValue().toString(),
            blog.getBrief().getValue(),
            blog.getUrl().getValue());
  }

  public Date getCreateDate() {
    return createDate;
  }

  public CreateBlogCommand getBlogDto() {
    return blogDto;
  }
}
