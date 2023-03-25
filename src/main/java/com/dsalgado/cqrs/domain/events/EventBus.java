package com.dsalgado.cqrs.domain.events;

public interface EventBus {
  <T extends DomainEvent> void notify(T event);
}
