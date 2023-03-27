package com.dsalgado.cqrs.application.blog;

import com.dsalgado.cqrs.domain.bus.Query;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FindBlogQuery extends Query {
  @JsonIgnore public static final String QUERY_TYPE = "FindBlogQuery";

  private String blogId;

  public FindBlogQuery(String blogId) {
    super(QUERY_TYPE);
    this.blogId = blogId;
  }

  public String getBlogId() {
    return blogId;
  }
}
