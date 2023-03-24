package com.dsalgado.cqrs.domain.bus;

import com.dsalgado.cqrs.domain.blog.CreateBlogCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "commandType")
@JsonSubTypes({@JsonSubTypes.Type(value = CreateBlogCommand.class, name = "CreateBlogCommand")
  // Add concrete commands here
})
public abstract class Command {
  private final String commandType;

  @JsonCreator
  public Command(@JsonProperty("commandType") String commandType) {
    this.commandType = commandType;
  }

  @JsonIgnore
  public abstract boolean isValid();

  public String getCommandType() {
    return commandType;
  }
}
