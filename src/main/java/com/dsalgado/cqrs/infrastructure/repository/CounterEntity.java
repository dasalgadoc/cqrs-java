package com.dsalgado.cqrs.infrastructure.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Table(name = "counter")
@EnableAutoConfiguration
public class CounterEntity {

  @Id private String entity;
  private Integer entries;

  public CounterEntity() {}

  public CounterEntity(String entity, Integer entries) {
    this.entity = entity;
    this.entries = entries;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public Integer getEntries() {
    return entries;
  }

  public void setEntries(Integer entries) {
    this.entries = entries;
  }
}
