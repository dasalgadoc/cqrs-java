package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.application.counter.FindCounterQuery;
import com.dsalgado.cqrs.application.counter.GetCounterQueryHandler;
import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryQueueConfiguration {

  @Bean
  public Map<String, QueryHandler<? extends Query>> inMemoryQueryHandlers() {
    Map<String, QueryHandler<? extends Query>> queryHandler = new HashMap<>();

    queryHandler.put(FindCounterQuery.QUERY_TYPE, new GetCounterQueryHandler());

    return queryHandler;
  }
}
