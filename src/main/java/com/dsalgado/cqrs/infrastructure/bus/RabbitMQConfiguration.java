package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.application.BlogCounter;
import com.dsalgado.cqrs.application.BlogCreator;
import com.dsalgado.cqrs.application.CreateBlogCommand;
import com.dsalgado.cqrs.application.PostBlogCommandHandler;
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

  public RabbitMQConfiguration(BlogCreator blogCreator) {
    this.blogCreator = blogCreator;
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
    blogCreatedObservers.add(new BlogCounter());

    eventObservers.put(BlogCreatedDomainEvent.EVENT_NAME, blogCreatedObservers);

    return eventObservers;
  }
}
