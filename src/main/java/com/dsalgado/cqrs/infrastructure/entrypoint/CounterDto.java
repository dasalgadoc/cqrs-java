package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CounterDto {
  @JsonProperty("entity_name")
  private String entityName;

  @JsonProperty("entity_entries")
  private Integer entityEntries;

  public String getEntityName() {
    return entityName;
  }

  public Integer getEntityEntries() {
    return entityEntries;
  }
}
