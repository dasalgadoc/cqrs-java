package com.dsalgado.cqrs.application.blog;

import com.dsalgado.cqrs.domain.counter.EntityName;
import com.dsalgado.cqrs.domain.events.BlogCreatedDomainEvent;
import com.dsalgado.cqrs.domain.events.DomainEvent;
import com.dsalgado.cqrs.domain.events.EventObserver;
import com.dsalgado.cqrs.domain.repository.CounterRepository;
import com.dsalgado.cqrs.domain.repository.CounterRepositoryFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class BlogCounter implements EventObserver<BlogCreatedDomainEvent> {

  private static final String ENTITY_NAME = "Blogs";

  @Resource private CounterRepositoryFactory counterRepositoryFactory;
  private CounterRepository counterRepository;

  @PostConstruct
  public void initializer() {
    counterRepository = counterRepositoryFactory.getCounterRepository();
  }

  @Override
  public <T extends DomainEvent> void update(T event) {
    EntityName entityName = new EntityName(ENTITY_NAME);
    counterRepository.adding(entityName);
  }
}
