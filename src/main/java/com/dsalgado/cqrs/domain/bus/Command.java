package com.dsalgado.cqrs.domain.bus;

public abstract class Command {
  public abstract boolean isValid();
}
