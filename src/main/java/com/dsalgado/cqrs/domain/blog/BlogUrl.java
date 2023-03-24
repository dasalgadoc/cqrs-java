package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.shared.StringValueObject;

public class BlogUrl extends StringValueObject {
  private String URL_REGEX = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

  public BlogUrl(String value) {
    super(value);
    if (!value.matches(URL_REGEX)) {
      throw new InvalidBlogType(BlogUrl.class.getSimpleName());
    }
  }
}
