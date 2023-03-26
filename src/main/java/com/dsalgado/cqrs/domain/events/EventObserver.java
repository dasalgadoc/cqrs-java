package com.dsalgado.cqrs.domain.events;

public interface EventObserver<T extends DomainEvent> {
  <T extends DomainEvent> void update(T event);
}
