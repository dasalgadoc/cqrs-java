package com.dsalgado.cqrs.domain.events;

public class UnableToBuildEventBus extends RuntimeException {
  public UnableToBuildEventBus(String eventBusName) {
    super(String.format("unable to build (%s) event bus", eventBusName));
  }
}
