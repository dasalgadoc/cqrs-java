package com.dsalgado.cqrs.domain.bus;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueryBusFactory {
  @Resource private List<QueryBus> queryBusList;

  @Value("${query_bus}")
  private String queryBusName;

  private Optional<QueryBus> retrieveQueryBus() {
    return queryBusList.stream()
        .filter(queryBus -> queryBusName.equals(queryBus.getClass().getSimpleName()))
        .findFirst();
  }

  public QueryBus getQueryBus() {
    Optional<QueryBus> queryBus = retrieveQueryBus();
    if (!queryBus.isPresent()) {
      throw new UnableToBuildBus(queryBusName);
    }
    return queryBus.get();
  }
}
