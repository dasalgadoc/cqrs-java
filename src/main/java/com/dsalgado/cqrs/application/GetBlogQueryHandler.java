package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import com.dsalgado.cqrs.domain.bus.Response;

public class GetBlogQueryHandler implements QueryHandler {
  @Override
  public <T extends Query> Response invoke(T query) {
    FindBlogQuery findBlogQuery = (FindBlogQuery) query;

    return null;
  }
}
