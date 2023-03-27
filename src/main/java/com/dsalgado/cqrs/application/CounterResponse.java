package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.bus.Response;

public class CounterResponse extends Response {
  private final String entityName;
  private final Integer entityEntries;

  public CounterResponse(String entityName, Integer entityEntries) {
    this.entityName = entityName;
    this.entityEntries = entityEntries;
  }

  public String getEntityName() {
    return entityName;
  }

  public Integer getEntityEntries() {
    return entityEntries;
  }
}
