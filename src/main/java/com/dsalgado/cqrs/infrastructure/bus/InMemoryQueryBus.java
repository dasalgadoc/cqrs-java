package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.application.counter.CounterResponse;
import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.Response;
import org.springframework.stereotype.Component;

@Component("InMemoryQueryBus")
public class InMemoryQueryBus implements QueryBus {
  @Override
  public <T extends Query> Response ask(T query) {
    System.out.println("Sync Query Bus");
    return new CounterResponse("example", 42);
  }
}
