package com.bank.account.cmd.infrasturcture;
import com.bank.core.events.BaseEvent;
import com.bank.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Service
public class AccountEventProducer implements EventProducer {
  @Autowired
  KafkaTemplate<String,Object> kafkaTemplate;
  @Override
  public void produce(String topic, BaseEvent event) {
    kafkaTemplate.send( topic,event );
  }
}
