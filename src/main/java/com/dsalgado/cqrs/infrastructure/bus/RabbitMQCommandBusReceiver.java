package com.dsalgado.cqrs.infrastructure.bus;

import com.dsalgado.cqrs.domain.blog.CreateBlogCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component("RabbitMQCommandBusReceiver")
public class RabbitMQCommandBusReceiver {

  @RabbitListener(queues = "${rabbit.command_queue}")
  public void receiveMessage(@Payload byte[] message) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CreateBlogCommand command = objectMapper.readValue(message, CreateBlogCommand.class);
    System.out.println("Received message: " + command.getTitle());
  }
}
