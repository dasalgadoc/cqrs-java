package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.bus.Query;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FindCounterQuery extends Query {
  @JsonIgnore public static final String QUERY_TYPE = "FindCounterQuery";

  private String entityName;

  public FindCounterQuery(String entityName) {
    super(QUERY_TYPE);
    this.entityName = entityName;
  }

  public String getEntityName() {
    return entityName;
  }
}
