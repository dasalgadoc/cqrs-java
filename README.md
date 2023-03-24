# cqrs-java
Demo CQRS project using Java

```
mvn compile && mvn package
docker-compose up --build
```

Next steps (Apply an observer):
```java

@Component
public class CommandHandlerRegistry {
  private final Map<String, CommandHandler<?>> handlers = new HashMap<>();

  @Autowired
  public CommandHandlerRegistry(List<CommandHandler<?>> handlers) {
    handlers.forEach(handler -> this.handlers.put(handler.getCommandName(), handler));
  }

  public CommandHandler<?> getHandler(String commandName) {
    return handlers.get(commandName);
  }
}

@Component
public class RabbitMQCommandBusReceiver {
  @Autowired private CommandHandlerRegistry registry;

  @RabbitListener(queues = "${rabbit.command_queue}")
  public void receiveMessage(@Payload byte[] message) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Command command = objectMapper.readValue(message, Command.class);
    CommandHandler<?> handler = registry.getHandler(command.getType());
    if (handler == null) {
      throw new RuntimeException("No handler found for command type: " + command.getType());
    }
    handler.handle(command);
  }
}
```