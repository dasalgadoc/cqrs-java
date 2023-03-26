package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.Response;
import org.springframework.stereotype.Component;

@Component("RabbitMQQueryBus")
public class RabbitMQQueryBus implements QueryBus {
  @Override
  public <T extends Query> Response ask(T query) {
    System.out.println("Llegue al query bus");
    return null;
  }
}