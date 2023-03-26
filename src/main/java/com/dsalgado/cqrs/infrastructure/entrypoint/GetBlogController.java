package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.FindBlogQuery;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryBusFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetBlogController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus();
  }

  @GetMapping("/blog/{blogId}")
  public String getBlog(@PathVariable("blogId") String blogId) {
    FindBlogQuery query = new FindBlogQuery(blogId);
    queryBus.ask(query);

    return blogId;
  }
}
