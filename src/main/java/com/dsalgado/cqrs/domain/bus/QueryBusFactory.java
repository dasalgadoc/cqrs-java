package com.dsalgado.cqrs.domain.bus;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class QueryBusFactory {
  @Resource private List<QueryBus> queryBusList;

  private Optional<QueryBus> retrieveQueryBus(String queryBusName) {
    return queryBusList.stream()
        .filter(queryBus -> queryBusName.equals(queryBus.getClass().getSimpleName()))
        .findFirst();
  }

  public QueryBus getQueryBus(String requestedQueryBus) {
    Optional<QueryBus> queryBus = retrieveQueryBus(requestedQueryBus);
    if (!queryBus.isPresent()) {
      throw new UnableToBuildBus(requestedQueryBus);
    }
    return queryBus.get();
  }
}
