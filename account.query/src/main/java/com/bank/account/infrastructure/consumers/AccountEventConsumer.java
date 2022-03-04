package com.bank.account.infrastructure.consumers;
import com.bank.account.common.events.AccountClosedEvent;
import com.bank.account.common.events.AccountOpenedEvent;
import com.bank.account.common.events.FundsDepositedEvent;
import com.bank.account.common.events.FundsWithdrawEvent;
import com.bank.account.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Service
public class AccountEventConsumer implements EventConsumer{
  @Autowired
  EventHandler eventHandler;
  @KafkaListener(topics = "AccountOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountOpenedEvent event, Acknowledgment ack) {
    eventHandler.on( event );
    ack.acknowledge();
  }
  
  @KafkaListener(topics = "FundsDepositedEvent",groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsDepositedEvent event, Acknowledgment ack) {
    eventHandler.on( event );
    ack.acknowledge();
  }
  @KafkaListener(topics = "FundsWithdrawEvent",groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsWithdrawEvent event, Acknowledgment ack) {
    eventHandler.on( event );
    ack.acknowledge();
  }
  @KafkaListener(topics = "AccountClosedEvent",groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountClosedEvent event, Acknowledgment ack) {
    eventHandler.on( event );
    ack.acknowledge();
  }
}
