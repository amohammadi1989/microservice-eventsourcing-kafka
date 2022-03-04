package com.bank.core.infrastructure;
import com.bank.core.commands.BaseCommand;
import com.bank.core.commands.CommandHandlerMethod;
public interface CommandDispatcher {
  <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
  void send(BaseCommand command);
}
