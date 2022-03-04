package com.bank.account.cmd.api.commands;
import com.bank.account.cmd.domain.AccountAggregate;
import com.bank.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Service
public class AccountCommandHandler implements CommandHandler{
  
  private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;
  
  public AccountCommandHandler(EventSourcingHandler<AccountAggregate> eventSourcingHandler) {
    this.eventSourcingHandler = eventSourcingHandler;
  }
  
  @Override
  public void handle(OpenAccountCommand command) {
    AccountAggregate aggregate=new AccountAggregate(command);
    eventSourcingHandler.save( aggregate );
  }
  
  @Override
  public void handle(DepositFundsCommand command) {
    AccountAggregate aggregate= eventSourcingHandler.getById( command.getId() );
    aggregate.depositFunds( command.getAmount() );
    eventSourcingHandler.save( aggregate );
  }
  
  @Override
  public void handle(WithdrawFundsCommand command) {
    AccountAggregate aggregate=eventSourcingHandler.getById( command.getId() );
    if(command.getAmount()>aggregate.getBalance()){
      throw new IllegalStateException("Amount greater than balance.");
    }
    aggregate.withdrawFunds( command.getAmount() );
    eventSourcingHandler.save( aggregate );
  }
  
  @Override
  public void handle(CloseAccountCommand command) {
    AccountAggregate aggregate=eventSourcingHandler.getById( command.getId() );
    aggregate.closedAccount();
    eventSourcingHandler.save( aggregate );
  }
}
