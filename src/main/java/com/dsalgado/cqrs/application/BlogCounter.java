package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.events.BlogCreatedDomainEvent;
import com.dsalgado.cqrs.domain.events.DomainEvent;
import com.dsalgado.cqrs.domain.events.EventObserver;
import org.springframework.stereotype.Service;

@Service
public class BlogCounter implements EventObserver<BlogCreatedDomainEvent> {

  @Override
  public <T extends DomainEvent> void update(T event) {
    BlogCreatedDomainEvent blogCreatedDomainEvent = (BlogCreatedDomainEvent) event;
    System.out.println("Evento recibido");
    System.out.println(blogCreatedDomainEvent.getDomainEventName());
    System.out.println(blogCreatedDomainEvent.getCreateDate());
    System.out.println(blogCreatedDomainEvent.getBlogDto().getId());
  }
}
