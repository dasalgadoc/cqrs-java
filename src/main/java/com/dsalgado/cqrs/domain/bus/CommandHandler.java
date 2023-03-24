package com.dsalgado.cqrs.domain.bus;

// It's an observer with different name
public interface CommandHandler<T extends Command> {
  <T extends Command> void invoke(T command);
}
