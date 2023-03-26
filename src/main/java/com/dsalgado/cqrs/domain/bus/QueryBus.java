package com.dsalgado.cqrs.domain.bus;

public interface QueryBus {
  <T extends Query> Response ask(T query);
}
