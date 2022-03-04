package com.bank.account.cmd.infrasturcture;
import com.bank.account.cmd.domain.AccountAggregate;
import com.bank.account.cmd.domain.EventStoreRepository;
import com.bank.core.events.BaseEvent;
import com.bank.core.events.EventModel;
import com.bank.core.infrastructure.EventStore;
import com.bank.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Service
public class AccountEventStore implements EventStore {
  @Autowired
  EventStoreRepository eventStoreRepository;
  @Autowired
  EventProducer eventProducer;
  @Override
  public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
    
    if(false) {
      //TODO: check concurency update
    }
    int version=expectedVersion+1;
    for(BaseEvent e:events){
      e.setVersion( version );
      
      EventModel eventModel=EventModel.builder()
      .event( e )
      .version( version )
      .timeStamp( new Date() )
      .aggregationIdentifier( aggregateId )
      .aggregationType( AccountAggregate.class.getTypeName() )
      .eventType( e.getClass().getTypeName() )
      .build();
      
      EventModel prsitEvent= eventStoreRepository.save( eventModel );
      
      if(!prsitEvent.getId().isEmpty()){
        eventProducer.produce( e.getClass().getSimpleName(),e );
      }
    }
    
  }
  
  @Override
  public List<BaseEvent> getEvents(String aggregateId) {
   List<EventModel> streamModels=eventStoreRepository.findByAggregationIdentifier( aggregateId );
   
   if(streamModels==null || streamModels.isEmpty()){
     //TODO: create custome expection
     throw  new RuntimeException("Incorrect account id provided.");
   }
    return streamModels.stream().map( s->s.getEvent() ).collect( Collectors.toList());
  }
}
