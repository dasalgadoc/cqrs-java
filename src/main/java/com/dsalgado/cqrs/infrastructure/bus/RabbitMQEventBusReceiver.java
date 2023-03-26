package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.events.DomainEvent;
import com.dsalgado.cqrs.domain.events.EventObserver;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component("RabbitMQEventBusReceiver")
public class RabbitMQEventBusReceiver {

  private final Map<String, List<EventObserver<? extends DomainEvent>>> eventObservers;

  public RabbitMQEventBusReceiver(
      Map<String, List<EventObserver<? extends DomainEvent>>> eventObservers) {
    this.eventObservers = eventObservers;
  }

  @RabbitListener(queues = "${rabbit.event_queue}")
  public void receiveMessage(@Payload byte[] message) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    DomainEvent domainEvent = objectMapper.readValue(message, DomainEvent.class);
    System.out.println("Received domain event: " + domainEvent.getDomainEventName());

    List<EventObserver<? extends DomainEvent>> observers =
        eventObservers.get(domainEvent.getDomainEventName());
    if (observers != null) {
      observers.forEach(observer -> observer.update(domainEvent));
    }
  }
}
