package com.dsalgado.cqrs.domain.repository;

import com.dsalgado.cqrs.domain.counter.EntityCounter;
import com.dsalgado.cqrs.domain.counter.EntityName;

public interface CounterRepository {
  void adding(EntityName entity);

  EntityCounter get(EntityName entityName);
}
