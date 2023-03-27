package com.dsalgado.cqrs.application.counter;

import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import com.dsalgado.cqrs.domain.bus.Response;
import com.dsalgado.cqrs.domain.counter.EntityCounter;
import com.dsalgado.cqrs.domain.counter.EntityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCounterQueryHandler implements QueryHandler<FindCounterQuery> {

  @Autowired private EntityGetCounter entityGetCounter;

  @Override
  public <T extends Query> Response invoke(T query) {
    FindCounterQuery findCounterQuery = (FindCounterQuery) query;
    EntityName entityName = new EntityName(findCounterQuery.getEntityName());

    EntityCounter entityCounter = entityGetCounter.get(entityName);

    return new CounterResponse(
        entityCounter.getEntityName().getValue(), entityCounter.getEntityEntries().getValue());
  }
}
