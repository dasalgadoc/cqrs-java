package com.dsalgado.cqrs.domain.shared;

public abstract class StringValueObject {
  protected String value;

  public StringValueObject(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
