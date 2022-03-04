package com.bank.account.cmd.infrasturcture;
import com.bank.core.commands.BaseCommand;
import com.bank.core.commands.CommandHandlerMethod;
import com.bank.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Service
public class AccountCommandDispatcher implements CommandDispatcher {
  private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes =
  new HashMap<>();
  @Override
  public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
    List handlers= routes.computeIfAbsent( type, c->new LinkedList<>() );
    handlers.add( handler );
  }
  
  @Override
  public void send(BaseCommand command) {
    List<CommandHandlerMethod> handler= routes.get( command.getClass());
    if(handler==null || handler.size()==0){
      throw new RuntimeException("No command handler was registered!");
    }
    if(handler.size()>1){
      throw new RuntimeException("Cannot send command to one than more handler!");
    }
    handler.get( 0 ).handle( command );
  }
}
