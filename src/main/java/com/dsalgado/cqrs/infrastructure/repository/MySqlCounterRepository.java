package com.dsalgado.cqrs.infrastructure.repository;

import com.dsalgado.cqrs.domain.counter.EntityCounter;
import com.dsalgado.cqrs.domain.counter.EntityName;
import com.dsalgado.cqrs.domain.repository.CounterRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

@Component("MySqlCounterRepository")
@EnableAutoConfiguration
public class MySqlCounterRepository implements CounterRepository {

  @Autowired private MySqlCounterCrudRepository mySqlCounterCrudRepository;

  @Override
  public void adding(EntityName entity) {
    Optional<CounterEntity> returned = mySqlCounterCrudRepository.findById(entity.getValue());
    if (returned.isPresent()) {
      CounterEntity counterEntity = returned.get();
      counterEntity.entriesPlusOne();
      mySqlCounterCrudRepository.save(counterEntity);
      return;
    }
    CounterEntity counterEntity = new CounterEntity(entity.getValue(), 1);
    mySqlCounterCrudRepository.save(counterEntity);
  }

  @Override
  public EntityCounter get(EntityName entityName) {
    Optional<CounterEntity> returned = mySqlCounterCrudRepository.findById(entityName.getValue());
    if (!returned.isPresent()) {
      throw new RuntimeException("entity does not exists");
    }
    return returned.get().persistenceEntityToEntityCounter();
  }
}
