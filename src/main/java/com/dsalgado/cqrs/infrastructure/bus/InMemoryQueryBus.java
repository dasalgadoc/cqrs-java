package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import com.dsalgado.cqrs.domain.bus.Response;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component("InMemoryQueryBus")
public class InMemoryQueryBus implements QueryBus {

  private final Map<String, QueryHandler<? extends Query>> inMemoryQueryHandlers;

  public InMemoryQueryBus(Map<String, QueryHandler<? extends Query>> inMemoryQueryHandlers) {
    this.inMemoryQueryHandlers = inMemoryQueryHandlers;
  }

  @Override
  public <T extends Query> Response ask(T query) {
    System.out.println("Sync Query Bus");

    String resourceToQuery = query.getQueryType();
    QueryHandler handler = inMemoryQueryHandlers.get(resourceToQuery);

    if (handler != null) {
      return handler.invoke(query);
    }

    throw new RuntimeException("handler not found");
  }
}
