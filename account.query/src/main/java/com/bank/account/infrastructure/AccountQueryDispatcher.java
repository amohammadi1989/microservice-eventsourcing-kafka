package com.bank.account.infrastructure;
import com.bank.core.commands.BaseCommand;
import com.bank.core.commands.CommandHandlerMethod;
import com.bank.core.domains.BaseEntity;
import com.bank.core.infrastructure.QueryDispatcher;
import com.bank.core.queries.BaseQuery;
import com.bank.core.queries.QueriesHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */
@Service
public class AccountQueryDispatcher implements QueryDispatcher {
  private final Map<Class<? extends BaseQuery>, List<QueriesHandlerMethod>> routes =
  new HashMap<>();
  @Override
  public <T extends BaseQuery> void registerHandler(Class<T> type, QueriesHandlerMethod<T> handler) {
    List handlers= routes.computeIfAbsent( type, c->new LinkedList<>() );
    handlers.add( handler );
  }
  
  @Override
  public <U extends BaseEntity> List<U> send(BaseQuery command) {
    List<QueriesHandlerMethod> handler= routes.get( command.getClass());
    if(handler==null || handler.size()==0){
      throw new RuntimeException("No query handler was registered!");
    }
    if(handler.size()>1){
      throw new RuntimeException("Cannot send query to one than more handler!");
    }
    return handler.get( 0 ).handle( command );
  }
}
