package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import com.dsalgado.cqrs.domain.bus.Response;
import com.dsalgado.cqrs.domain.counter.EntityName;
import org.springframework.beans.factory.annotation.Autowired;

public class GetCounterQueryHandler implements QueryHandler {

  @Autowired private EntityGetCounter entityGetCounter;

  @Override
  public <T extends Query> Response invoke(T query) {
    FindCounterQuery findCounterQuery = (FindCounterQuery) query;

    EntityName entityName = new EntityName(findCounterQuery.getEntityName());

    return null;
  }
}
