package com.dsalgado.cqrs.domain.shared;

public class IntegerValueObject {
  protected Integer value;

  public IntegerValueObject(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public boolean isBiggerThan(IntegerValueObject other) {
    return this.value > other.getValue();
  }

  public boolean isBiggerThanZero() {
    return this.value > 0;
  }

  public boolean isZero() {
    return this.value == 0;
  }

  public boolean isLessThanZero() {
    return this.value < 0;
  }

  // Others common methods
}
