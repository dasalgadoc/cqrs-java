package com.dsalgado.cqrs.domain.counter;

import com.dsalgado.cqrs.domain.shared.IntegerValueObject;

public class EntityEntries extends IntegerValueObject {
  public EntityEntries(Integer value) {
    super(value);
    // Can be a checked custom exception
    if (this.isLessThanZero()) {
      throw new RuntimeException("negative numbers are invalid for entity entries counter");
    }
  }
}
