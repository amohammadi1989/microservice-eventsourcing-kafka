package com.bank.core.handlers;
import com.bank.core.domains.AggregateRoot;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
public interface EventSourcingHandler<T> {
  void save(AggregateRoot aggregateRoot);
  T getById(String id);
}
