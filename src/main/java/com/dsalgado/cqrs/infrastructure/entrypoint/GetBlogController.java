package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.blog.FindBlogQuery;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryBusFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetBlogController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @Value("${async_query_bus}")
  private String queryBusName;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus(queryBusName);
  }

  @GetMapping("/blog/{blogId}")
  public String getBlog(@PathVariable("blogId") String blogId) {
    FindBlogQuery query = new FindBlogQuery(blogId);
    try {
      queryBus.ask(query);
    } catch (Exception ex) {
      return HttpStatus.NOT_FOUND.toString();
    }

    return blogId;
  }
}
