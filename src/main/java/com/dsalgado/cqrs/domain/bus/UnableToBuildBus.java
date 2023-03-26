package com.dsalgado.cqrs.domain.bus;

public class UnableToBuildBus extends RuntimeException {
  public UnableToBuildBus(String commandBusName) {
    super(String.format("unable to build (%s) command bus", commandBusName));
  }
}
