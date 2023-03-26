package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.events.DomainEvent;
import com.dsalgado.cqrs.domain.events.EventBus;
import com.dsalgado.cqrs.infrastructure.server.RabbitServer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("RabbitMQEventBus")
public class RabbitMQEventBus implements EventBus {

  @Value("${rabbit.event_queue}")
  private String QUEUE_NAME;

  private final RabbitServer rabbitServer;

  @Autowired
  public RabbitMQEventBus(RabbitServer rabbitServer) {
    this.rabbitServer = rabbitServer;
  }

  @Override
  public <T extends DomainEvent> void notify(T event) {
    try {
      this.publish(event);
    } catch (Exception ex) {
    }
  }

  public <T extends DomainEvent> void publish(T event) throws IOException, TimeoutException {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitServer.getConnectionFactory());
    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    rabbitTemplate.convertAndSend(QUEUE_NAME, event);
  }
}
