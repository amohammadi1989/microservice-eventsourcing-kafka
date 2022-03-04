package com.bank.core.producers;

import com.bank.core.events.BaseEvent;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
public interface EventProducer {
  void produce(String topic, BaseEvent event);
}
