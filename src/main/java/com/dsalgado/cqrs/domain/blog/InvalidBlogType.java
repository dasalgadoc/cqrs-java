package com.dsalgado.cqrs.domain.blog;

public class InvalidBlogType extends RuntimeException {
  public InvalidBlogType(String valueObjectName) {
    super(String.format("invalid blog type in %s", valueObjectName));
  }
}
