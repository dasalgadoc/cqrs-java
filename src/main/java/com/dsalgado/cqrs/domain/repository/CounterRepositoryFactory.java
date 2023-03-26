package com.dsalgado.cqrs.domain.repository;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CounterRepositoryFactory {
  @Resource private List<CounterRepository> counterRepositoryList;

  @Value("${counter.repository}")
  private String repositoryName;

  private Optional<CounterRepository> retrieveCounterRepository() {
    return counterRepositoryList.stream()
        .filter(
            counterRepository ->
                repositoryName.equals(counterRepository.getClass().getSimpleName()))
        .findFirst();
  }

  public CounterRepository getCounterRepository() {
    Optional<CounterRepository> repository = retrieveCounterRepository();
    if (!repository.isPresent()) {
      throw new UnableToBuildRepository(BlogRepository.class.getSimpleName());
    }
    return repository.get();
  }
}
