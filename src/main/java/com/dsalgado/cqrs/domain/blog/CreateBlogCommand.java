package com.dsalgado.cqrs.domain.blog;

import com.dsalgado.cqrs.domain.bus.Command;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("CreateBlogCommand")
public class CreateBlogCommand extends Command {
  @JsonIgnore public static final String COMMAND_TYPE = "CreateBlogCommand";

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

  @JsonCreator
  public CreateBlogCommand(
      @JsonProperty("id") String id,
      @JsonProperty("title") String title,
      @JsonProperty("type") String type,
      @JsonProperty("brief") String brief,
      @JsonProperty("url") String url) {
    super(COMMAND_TYPE);
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
  @JsonIgnore
  public boolean isValid() {
    return this == null || hasNull() || hasEmpties();
  }

  @JsonIgnore
  private boolean hasNull() {
    return id == null || title == null || type == null || brief == null || url == null;
  }

  @JsonIgnore
  private boolean hasEmpties() {
    return id.isEmpty() || title.isEmpty() || type.isEmpty() || brief.isEmpty() || url.isEmpty();
  }
}
