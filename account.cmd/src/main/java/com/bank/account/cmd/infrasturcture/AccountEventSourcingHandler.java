package com.bank.account.cmd.infrasturcture;
import com.bank.account.cmd.domain.AccountAggregate;
import com.bank.core.domains.AggregateRoot;
import com.bank.core.events.BaseEvent;
import com.bank.core.events.EventModel;
import com.bank.core.handlers.EventSourcingHandler;
import com.bank.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Service
public class AccountEventSourcingHandler<T> implements EventSourcingHandler<AccountAggregate> {
  @Autowired
  EventStore eventStore;
  @Override
  public void save(AggregateRoot aggregateRoot) {
       eventStore.saveEvents( aggregateRoot.getId(),aggregateRoot.getUncommittedChanges(),
                              aggregateRoot.getVersion() );
       aggregateRoot.markChangesCommitted();
  }
  
  @Override
  public AccountAggregate getById(String id) {
     AccountAggregate accountAggregate=new AccountAggregate();
     List<BaseEvent> events= eventStore.getEvents( id );
     
     if(events!=null || events.size()!=0) {
       accountAggregate.setId( id );
       accountAggregate.replayEvents( events );
       int latestVersion = events.stream().map( a -> a.getVersion() ).max( Comparator.naturalOrder() ).get();
       accountAggregate.setVersion( latestVersion );
     }
     
    return accountAggregate;
  }
}
