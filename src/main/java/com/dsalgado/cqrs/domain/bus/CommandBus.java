package com.dsalgado.cqrs.domain.bus;

public interface CommandBus {
  void dispatch(Command command);
}
