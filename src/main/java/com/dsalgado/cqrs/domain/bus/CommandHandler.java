package com.dsalgado.cqrs.domain.bus;

public interface CommandHandler<T extends Command> {
  <T extends Command> void invoke(T command);
}
