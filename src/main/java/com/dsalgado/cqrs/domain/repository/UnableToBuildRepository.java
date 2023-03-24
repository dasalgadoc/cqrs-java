package com.dsalgado.cqrs.domain.repository;

public class UnableToBuildRepository extends RuntimeException {
  public UnableToBuildRepository(String repositoryName) {
    super(String.format("unable to build (%s) repository", repositoryName));
  }
}
