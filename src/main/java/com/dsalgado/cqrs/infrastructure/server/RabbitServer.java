package com.dsalgado.cqrs.infrastructure.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitServer {

  private final ConnectionFactory connectionFactory;

  public RabbitServer(
      @Value("${rabbit.host}") String rabbitHost,
      @Value("${rabbit.port}") Integer rabbitPort,
      @Value("${rabbit.user}") String rabbitUsername,
      @Value("${rabbit.pass}") String rabbitPassword) {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    this.connectionFactory = connectionFactory;
    connectionFactory.setHost(rabbitHost);
    connectionFactory.setUsername(rabbitUsername);
    connectionFactory.setPassword(rabbitPassword);
    connectionFactory.setPort(rabbitPort);
  }

  public ConnectionFactory getConnectionFactory() throws IOException, TimeoutException {
    return connectionFactory;
  }

  public Channel getChannel(Connection connection) throws IOException {
    return connection.createChannel();
  }
}
