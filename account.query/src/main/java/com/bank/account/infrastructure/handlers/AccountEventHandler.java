package com.bank.account.infrastructure.handlers;
import com.bank.account.common.events.AccountClosedEvent;
import com.bank.account.common.events.AccountOpenedEvent;
import com.bank.account.common.events.FundsDepositedEvent;
import com.bank.account.common.events.FundsWithdrawEvent;
import com.bank.account.domain.BankAccount;
import com.bank.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Service
public class AccountEventHandler implements EventHandler {
  @Autowired
  AccountRepository accountRepository;
  
  @Override
  public void on(AccountOpenedEvent event) {
    BankAccount bankAccount = BankAccount.builder()
    .accountHolder( event.getAccountHolder() )
    .accountType( event.getAccountType() )
    .balance( event.getOpeningBalance() )
    .creationDate( event.getCreateAccount() )
    .id( event.getId() ).build();
    accountRepository.save( bankAccount );
  }
  
  @Override
  public void on(FundsDepositedEvent event) {
    Optional<BankAccount> bankAccount = accountRepository.findById( event.getId() );
    if (!bankAccount.isPresent())
      return;
    double latestBalance = bankAccount.get().getBalance()+event.getAmount();
    bankAccount.get().setBalance( latestBalance );
    accountRepository.save( bankAccount.get() );
  }
  @Override
  public void on(FundsWithdrawEvent event) {
    Optional<BankAccount> bankAccount = accountRepository.findById( event.getId() );
    if (!bankAccount.isPresent())
      return;
    double latestBalance = bankAccount.get().getBalance()-event.getAmount();
    bankAccount.get().setBalance( latestBalance );
    accountRepository.save( bankAccount.get() );
  }
  
  @Override
  public void on(AccountClosedEvent event) {
    accountRepository.deleteById( event.getId() );
  }
}
