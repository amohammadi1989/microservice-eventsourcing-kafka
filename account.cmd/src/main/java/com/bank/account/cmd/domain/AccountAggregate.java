package com.bank.account.cmd.domain;
import com.bank.account.cmd.api.commands.OpenAccountCommand;
import com.bank.account.common.events.AccountClosedEvent;
import com.bank.account.common.events.AccountOpenedEvent;
import com.bank.account.common.events.FundsDepositedEvent;
import com.bank.account.common.events.FundsWithdrawEvent;
import com.bank.core.domains.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
  
  private Boolean active;
  private double balance;
  
  public double getBalance() {
    return balance;
  }
  
  public AccountAggregate(OpenAccountCommand command){
    raiseEvent( AccountOpenedEvent.builder()
                .accountType( command.getAccountType() )
                .accountHolder( command.getAccountHolder() )
                .openingBalance( command.getOpeningBalance() )
                .createAccount( new Date() )
                .id( command.getId() ).build()
    );
  }
  public void apply(AccountOpenedEvent event){
    this.setId( event.getId() );
    this.active=true;
    this.balance=event.getOpeningBalance();
  }
  public void depositFunds(double amount){
    if(!this.active){
      throw new IllegalStateException("Funds cannot be deposited into closed account");
    }
    if(amount<=0){
      throw new IllegalStateException("The deposit amount must be greater than 0!.");
    }
    raiseEvent( FundsDepositedEvent.builder()
                .id( this.getId() )
                .amount( amount ).build()
    );
  }
  public void apply(FundsDepositedEvent event){
    this.setId( event.getId() );
    this.balance+=event.getAmount();
  }
  public void withdrawFunds(double amount){
    if(!this.active){
      throw new IllegalStateException("Funds cannot be withdraw from closed account");
    }
    raiseEvent( FundsWithdrawEvent.builder()
                .id( this.getId() )
                .amount( amount).build());
  }
  public void apply(FundsWithdrawEvent event){
    this.setId( event.getId() );
    this.balance-=event.getAmount();
  }
  public void closedAccount(){
    if(!this.active){
      throw new IllegalStateException("The bank account already been closed.");
    }
    raiseEvent( AccountClosedEvent.builder().id( this.getId() ).build() );
    
  }
  public void apply(AccountClosedEvent event){
    this.setId( event.getId() );
    this.active=false;
  }
  
}
