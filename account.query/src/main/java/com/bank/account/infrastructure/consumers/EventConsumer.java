package com.bank.account.infrastructure.consumers;
import com.bank.account.common.events.AccountClosedEvent;
import com.bank.account.common.events.AccountOpenedEvent;
import com.bank.account.common.events.FundsDepositedEvent;
import com.bank.account.common.events.FundsWithdrawEvent;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
public interface EventConsumer {
  void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
  void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
  void consume(@Payload FundsWithdrawEvent event, Acknowledgment ack);
  void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
