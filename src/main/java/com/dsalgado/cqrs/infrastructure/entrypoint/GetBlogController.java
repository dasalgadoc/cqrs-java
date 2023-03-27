package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.blog.FindBlogQuery;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryBusFactory;
import com.dsalgado.cqrs.domain.bus.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GetBlogController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @Autowired private ObjectMapper objectMapper;

  @Value("${async_query_bus}")
  private String queryBusName;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus(queryBusName);
  }

  @GetMapping("/blog/{blogId}")
  public ResponseEntity<BlogDto> getBlog(@PathVariable("blogId") String blogId) {
    FindBlogQuery query = new FindBlogQuery(blogId);
    try {
      Response response = queryBus.ask(query);
      BlogDto blogDto = objectMapper.convertValue(response, BlogDto.class);

      return new ResponseEntity<>(blogDto, HttpStatus.OK);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Counter not found");
    }
  }
}
