package com.dsalgado.cqrs.domain.events;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EventBusFactory {
  @Resource private List<EventBus> eventBusList;

  @Value("${event_bus}")
  private String eventBusName;

  private Optional<EventBus> retrieveEventBus() {
    return eventBusList.stream()
        .filter(eventBus -> eventBusName.equals(eventBus.getClass().getSimpleName()))
        .findFirst();
  }

  public EventBus getEventBus() {
    Optional<EventBus> eventBus = retrieveEventBus();
    if (!eventBus.isPresent()) {
      throw new UnableToBuildEventBus(eventBusName);
    }
    return eventBus.get();
  }
}
