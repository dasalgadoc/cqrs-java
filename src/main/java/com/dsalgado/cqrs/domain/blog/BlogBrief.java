package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.shared.StringValueObject;

public class BlogBrief extends StringValueObject {

  public BlogBrief(String value) {
    super(value);
    if (value.length() > 400) {
      throw new InvalidBlogType(BlogBrief.class.getSimpleName());
    }
  }
}
