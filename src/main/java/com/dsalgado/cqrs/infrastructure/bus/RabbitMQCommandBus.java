package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.bus.Command;
import com.dsalgado.cqrs.domain.bus.CommandBus;
import com.dsalgado.cqrs.infrastructure.server.RabbitServer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("RabbitMQCommandBus")
public class RabbitMQCommandBus implements CommandBus {

  @Value("${rabbit.command_queue}")
  private String QUEUE_NAME;

  private final RabbitServer rabbitServer;

  @Autowired
  public RabbitMQCommandBus(RabbitServer rabbitServer) {
    this.rabbitServer = rabbitServer;
  }

  @Override
  public void dispatch(Command command) {
    try {
      this.publish(command);
    } catch (Exception ex) {
      // TODO better exception handler
    }
  }

  public void publish(Command command) throws IOException, TimeoutException {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitServer.getConnectionFactory());
    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    rabbitTemplate.convertAndSend(QUEUE_NAME, command);
  }
}
