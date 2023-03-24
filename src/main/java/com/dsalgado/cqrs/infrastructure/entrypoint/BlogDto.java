package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogDto {
  @JsonProperty("id")
  private String id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("type")
  private String type;

  @JsonProperty("brief")
  private String brief;

  @JsonProperty("url")
  private String url;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getBrief() {
    return brief;
  }

  public String getUrl() {
    return url;
  }
}
