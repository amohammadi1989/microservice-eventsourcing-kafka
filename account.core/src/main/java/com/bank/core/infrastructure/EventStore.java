package com.bank.core.infrastructure;
import com.bank.core.events.BaseEvent;
import com.bank.core.events.EventModel;

import java.util.List;
public interface EventStore {
  void saveEvents(String aggregateId,Iterable<BaseEvent> events,int expectedVersion );
  List<BaseEvent> getEvents(String aggregateId);
}
