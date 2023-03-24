package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.bus.Command;
import com.dsalgado.cqrs.domain.bus.CommandBus;
import org.springframework.stereotype.Component;

@Component("SyncCommandBus")
public class SyncCommandBus implements CommandBus {

  @Override
  public void dispatch(Command command) {
    System.out.println(String.format("Hola desde el bus %s", command.toString()));
  }
}
