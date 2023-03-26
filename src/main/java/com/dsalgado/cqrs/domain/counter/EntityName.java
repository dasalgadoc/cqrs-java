package com.dsalgado.cqrs.domain.counter;

import com.dsalgado.cqrs.domain.shared.StringValueObject;

public class EntityName extends StringValueObject {
  public EntityName(String value) {
    super(value);
    // Can be a checked custom exception
    if (value.isEmpty()) {
      throw new RuntimeException("entity can't be empty");
    }
  }
}
