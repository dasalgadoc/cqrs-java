package com.dsalgado.cqrs.domain.bus;

public interface QueryHandler {
  <T extends Query> Response invoke(T query);
}
