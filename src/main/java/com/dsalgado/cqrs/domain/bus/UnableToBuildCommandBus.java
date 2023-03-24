package com.dsalgado.cqrs.domain.bus;

public class UnableToBuildCommandBus extends RuntimeException {
  public UnableToBuildCommandBus(String commandBusName) {
    super(String.format("unable to build (%s) command bus", commandBusName));
  }
}
