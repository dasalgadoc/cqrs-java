package com.dsalgado.cqrs.domain.counter;

public class EntityCounter {
  private final EntityName entityName;
  private final EntityEntries entityEntries;

  public EntityCounter(EntityName entityName) {
    this.entityName = entityName;
    this.entityEntries = new EntityEntries(0);
  }

  public EntityCounter(EntityName entityName, EntityEntries entityEntries) {
    this.entityName = entityName;
    this.entityEntries = entityEntries;
  }

  public EntityName getEntityName() {
    return entityName;
  }

  public EntityEntries getEntityEntries() {
    return entityEntries;
  }
}
