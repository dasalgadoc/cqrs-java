package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.application.blog.BlogCounter;
import com.dsalgado.cqrs.application.blog.BlogCreator;
import com.dsalgado.cqrs.application.blog.CreateBlogCommand;
import com.dsalgado.cqrs.application.blog.PostBlogCommandHandler;
import com.dsalgado.cqrs.domain.bus.Command;
import com.dsalgado.cqrs.domain.bus.CommandHandler;
import com.dsalgado.cqrs.domain.events.BlogCreatedDomainEvent;
import com.dsalgado.cqrs.domain.events.DomainEvent;
import com.dsalgado.cqrs.domain.events.EventObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
  @Autowired private final BlogCreator blogCreator;
  @Autowired private final BlogCounter blogCounter;

  public RabbitMQConfiguration(BlogCreator blogCreator, BlogCounter blogCounter) {
    this.blogCreator = blogCreator;
    this.blogCounter = blogCounter;
  }

  @Bean
  public Map<String, List<CommandHandler<? extends Command>>> commandHandlers() {
    Map<String, List<CommandHandler<? extends Command>>> commandHandlers = new HashMap<>();

    List<CommandHandler<? extends Command>> createBlogCommands = new ArrayList<>();
    createBlogCommands.add(new PostBlogCommandHandler(blogCreator));

    commandHandlers.put(CreateBlogCommand.class.getSimpleName(), createBlogCommands);

    // add more commandHandlers here for other commands

    return commandHandlers;
  }

  @Bean
  public Map<String, List<EventObserver<? extends DomainEvent>>> eventObservers() {
    Map<String, List<EventObserver<? extends DomainEvent>>> eventObservers = new HashMap<>();

    List<EventObserver<? extends DomainEvent>> blogCreatedObservers = new ArrayList<>();
    blogCreatedObservers.add(blogCounter);

    eventObservers.put(BlogCreatedDomainEvent.EVENT_NAME, blogCreatedObservers);

    return eventObservers;
  }
}
