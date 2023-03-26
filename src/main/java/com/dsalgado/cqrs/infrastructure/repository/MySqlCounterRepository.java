package com.dsalgado.cqrs.infrastructure.repository;

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
  public void adding(String entity) {
    Optional<CounterEntity> returned = mySqlCounterCrudRepository.findById(entity);
    if (returned.isPresent()) {
      CounterEntity counterEntity = returned.get();
      counterEntity.setEntries(counterEntity.getEntries() + 1);
      mySqlCounterCrudRepository.save(counterEntity);
      return;
    }
    CounterEntity counterEntity = new CounterEntity(entity, 1);
    mySqlCounterCrudRepository.save(counterEntity);
  }
}
