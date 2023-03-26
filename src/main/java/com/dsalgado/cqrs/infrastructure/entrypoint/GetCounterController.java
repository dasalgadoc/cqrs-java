package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.FindCounterQuery;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.QueryBusFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCounterController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus();
  }

  @GetMapping("/count")
  public String getCounter(@RequestParam("entity_type") String entityType) {
    FindCounterQuery query = new FindCounterQuery(entityType);
    queryBus.ask(query);

    return entityType;
  }
}
