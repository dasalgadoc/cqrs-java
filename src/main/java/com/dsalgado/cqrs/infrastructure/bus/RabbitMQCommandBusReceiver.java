package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.bus.Command;
import com.dsalgado.cqrs.domain.bus.CommandHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component("RabbitMQCommandBusReceiver")
public class RabbitMQCommandBusReceiver {

  private final Map<String, List<CommandHandler<? extends Command>>> commandHandlers;

  public RabbitMQCommandBusReceiver(
      Map<String, List<CommandHandler<? extends Command>>> commandHandlers) {
    this.commandHandlers = commandHandlers;
  }

  @RabbitListener(queues = "${rabbit.command_queue}")
  public void receiveMessage(@Payload byte[] message) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Command command = objectMapper.readValue(message, Command.class);
    System.out.println("Received message: " + command.getCommandType());

    List<CommandHandler<? extends Command>> handlers =
        commandHandlers.get(command.getCommandType());
    if (handlers != null) {
      handlers.forEach(handler -> handler.invoke(command));
    }
  }
}
