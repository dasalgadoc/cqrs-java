package com.dsalgado.cqrs.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;

public interface MySqlCounterCrudRepository extends CrudRepository<CounterEntity, String> {}
