package com.dsalgado.cqrs.domain.blog;

public class BlogType {
  private final BlogTypes value;

  public BlogType(String value) {
    this.value = BlogTypes.valueOf(value);
  }

  public BlogTypes getValue() {
    return value;
  }
}
