package com.dsalgado.cqrs.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "domainEventName")
@JsonSubTypes({
  @JsonSubTypes.Type(value = BlogCreatedDomainEvent.class, name = "BlogCreatedDomainEvent")
  // Add more domain events
})
public abstract class DomainEvent {
  private final String domainEventName;

  @JsonCreator
  public DomainEvent(@JsonProperty("domainEventName") String domainEventName) {
    this.domainEventName = domainEventName;
  }

  public String getDomainEventName() {
    return domainEventName;
  }
}
