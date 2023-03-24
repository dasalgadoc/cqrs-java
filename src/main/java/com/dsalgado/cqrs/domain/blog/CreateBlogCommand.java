package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.bus.Command;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBlogCommand extends Command {
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

  public CreateBlogCommand(String id, String title, String type, String brief, String url) {
    this.id = id;
    this.title = title;
    this.type = type;
    this.brief = brief;
    this.url = url;
  }

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

  @Override
  public boolean isValid() {
    return this == null || hasNull() || hasEmpties();
  }

  private boolean hasNull() {
    return id == null || title == null || type == null || brief == null || url == null;
  }

  private boolean hasEmpties() {
    return id.isEmpty() || title.isEmpty() || type.isEmpty() || brief.isEmpty() || url.isEmpty();
  }
}
