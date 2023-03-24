package com.dsalgado.cqrs.domain.blog;

import java.util.UUID;

public class BlogId {
  private final UUID value;

  public BlogId(String value) {
    this.value = UUID.fromString(value);
  }

  public UUID getValue() {
    return value;
  }
}
