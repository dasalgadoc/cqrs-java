package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.application.counter.FindCounterQuery;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GetCounterController {

  @Resource private QueryBusFactory queryBusFactory;
  private QueryBus queryBus;

  @Autowired private ObjectMapper objectMapper;

  @Value("${sync_query_bus}")
  private String queryBusName;

  @PostConstruct
  public void initializer() {
    queryBus = queryBusFactory.getQueryBus(queryBusName);
  }

  @GetMapping("/count")
  public ResponseEntity<CounterDto> getCounter(@RequestParam("entity_type") String entityType) {
    FindCounterQuery query = new FindCounterQuery(entityType);
    try {
      Response response = queryBus.ask(query);
      CounterDto counterDto = objectMapper.convertValue(response, CounterDto.class);

      return new ResponseEntity<>(counterDto, HttpStatus.OK);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Counter not found");
    }
  }
}
