package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.application.blog.BlogResponse;
import com.dsalgado.cqrs.domain.bus.Query;
import com.dsalgado.cqrs.domain.bus.QueryBus;
import com.dsalgado.cqrs.domain.bus.Response;
import com.dsalgado.cqrs.infrastructure.server.RabbitServer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("RabbitMQQueryBus")
public class RabbitMQQueryBus implements QueryBus {

  @Value("${rabbit.query_bus}")
  private String QUEUE_NAME;

  private final RabbitServer rabbitServer;

  public RabbitMQQueryBus(RabbitServer rabbitServer) {
    this.rabbitServer = rabbitServer;
  }

  @Override
  public <T extends Query> Response ask(T query) {
    System.out.println("Async Query bus");

    try {
      // this.publish(query);
    } catch (Exception ex) {
      throw new RuntimeException("there is no response");
    }

    return new BlogResponse("xxx", "the title", "the type", "the brief", "the url");
  }

  public void publish(Query query) throws IOException, TimeoutException {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitServer.getConnectionFactory());
    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    rabbitTemplate.convertAndSend(QUEUE_NAME, query);
  }
}
