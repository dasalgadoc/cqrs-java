package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.FindCounterQuery;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryBusFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCounterController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @Value("${sync_query_bus}")
  private String queryBusName;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus(queryBusName);
  }

  @GetMapping("/count")
  public String getCounter(@RequestParam("entity_type") String entityType) {
    FindCounterQuery query = new FindCounterQuery(entityType);
    try {
      queryBus.ask(query);
    } catch (Exception ex) {
      return HttpStatus.NOT_FOUND.toString();
    }

    return entityType;
  }
}
