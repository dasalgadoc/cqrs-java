package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.bus.Response;

public class BlogResponse extends Response {
  private final String id;
  private final String title;
  private final String type;
  private final String brief;
  private final String url;

  public BlogResponse(String id, String title, String type, String brief, String url) {
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
}
