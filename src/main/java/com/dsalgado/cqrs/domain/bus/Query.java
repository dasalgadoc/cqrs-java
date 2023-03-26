package com.dsalgado.cqrs.domain.bus;

public abstract class Query {
  private final String queryType;

  public Query(String queryType) {
    this.queryType = queryType;
  }

  public String getQueryType() {
    return queryType;
  }
}
