package com.bank.core.commands;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand>{
  void handle(T command);
}
