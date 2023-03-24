package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.shared.StringValueObject;

public class BlogTitle extends StringValueObject {

  public BlogTitle(String value) {
    super(value);
    if (value.length() > 140) {
      throw new InvalidBlogType(BlogTitle.class.getSimpleName());
    }
  }
}
