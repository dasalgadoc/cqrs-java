package com.dsalgado.cqrs.application.blog;

import com.dsalgado.cqrs.domain.blog.Blog;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import com.dsalgado.cqrs.domain.bus.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBlogQueryHandler implements QueryHandler<FindBlogQuery> {

  @Autowired private BlogFinder blogFinder;

  @Override
  public <T extends Query> Response invoke(T query) {
    FindBlogQuery findBlogQuery = (FindBlogQuery) query;
    BlogId blogId = new BlogId(findBlogQuery.getBlogId());

    Blog blog = blogFinder.get(blogId);

    return new BlogResponse(
        blog.getId().getValue().toString(),
        blog.getTitle().getValue(),
        blog.getType().getValue().toString(),
        blog.getBrief().getValue(),
        blog.getUrl().getValue());
  }
}
