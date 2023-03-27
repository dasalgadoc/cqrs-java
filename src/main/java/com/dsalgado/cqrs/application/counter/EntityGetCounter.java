package com.dsalgado.cqrs.application.counter;

import com.dsalgado.cqrs.domain.counter.EntityCounter;
import com.dsalgado.cqrs.domain.counter.EntityName;
import com.dsalgado.cqrs.domain.repository.CounterRepository;
import com.dsalgado.cqrs.domain.repository.CounterRepositoryFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EntityGetCounter {

  @Resource private CounterRepositoryFactory counterRepositoryFactory;
  private CounterRepository counterRepository;

  @PostConstruct
  public void initializer() {
    counterRepository = counterRepositoryFactory.getCounterRepository();
  }

  public EntityCounter get(EntityName entityName) {
    return counterRepository.get(entityName);
  }
}
