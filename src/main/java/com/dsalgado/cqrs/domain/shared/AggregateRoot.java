package com.dsalgado.cqrs.domain.shared;

import com.dsalgado.cqrs.domain.events.DomainEvent;
import java.util.ArrayList;
import java.util.List;

public class AggregateRoot {
  private List<DomainEvent> domainEvents;

  public AggregateRoot(List<DomainEvent> domainEvents) {
    this.domainEvents = domainEvents;
  }

  public List<DomainEvent> pullDomainEvents() {
    List<DomainEvent> events = new ArrayList<>(domainEvents);
    domainEvents.clear();

    return events;
  }

  protected void recordEvent(DomainEvent domainEvent) {
    domainEvents.add(domainEvent);
  }
}
