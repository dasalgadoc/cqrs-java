package com.dsalgado.cqrs.domain.repository;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlogRepositoryFactory {
  @Resource private List<BlogRepository> blogRepositoryList;

  @Value("${blogs.repository}")
  private String repositoryName;

  private Optional<BlogRepository> retrieveBlogRepository() {
    return blogRepositoryList.stream()
        .filter(blogRepository -> repositoryName.equals(blogRepository.getClass().getSimpleName()))
        .findFirst();
  }

  public BlogRepository getBlogRepository() {
    Optional<BlogRepository> repository = retrieveBlogRepository();
    if (!repository.isPresent()) {
      throw new UnableToBuildRepository(BlogRepository.class.getSimpleName());
    }
    return repository.get();
  }
}
