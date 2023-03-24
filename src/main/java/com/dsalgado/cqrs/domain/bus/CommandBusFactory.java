package com.dsalgado.cqrs.domain.bus;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CommandBusFactory {
  @Resource private List<CommandBus> commandBuses;

  @Value("${command_bus}")
  private String commandBusName;

  private Optional<CommandBus> retrieveCommandBus() {
    return commandBuses.stream()
        .filter(commandBus -> commandBusName.equals(commandBus.getClass().getSimpleName()))
        .findFirst();
  }

  public CommandBus getCommandBus() {
    Optional<CommandBus> commandBus = retrieveCommandBus();
    if (!commandBus.isPresent()) {
      throw new UnableToBuildCommandBus(commandBusName);
    }
    return commandBus.get();
  }
}
