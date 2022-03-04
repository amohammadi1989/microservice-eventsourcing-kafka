package com.bank.account.api.controllers;
import com.bank.account.api.dtos.AccountLockupResponse;
import com.bank.account.api.queries.FindAccountByHolderQuery;
import com.bank.account.api.queries.FindAccountByIdQuery;
import com.bank.account.api.queries.FindAllAccountsQuery;
import com.bank.account.domain.BankAccount;
import com.bank.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */
@RestController
@RequestMapping(path = "/api/v1/bankAccountLookup")
public class AccountLockupController {
  private final Logger logger = Logger.getLogger( AccountLockupController.class.getName() );
  
  @Autowired
  private QueryDispatcher queryDispatcher;
  
  @GetMapping(path = "/")
  public ResponseEntity<AccountLockupResponse> getAllAccounts() {
    try {
      List<BankAccount> accounts = queryDispatcher.send( new FindAllAccountsQuery() );
      if (accounts == null || accounts.size() == 0) {
        return new ResponseEntity<>( null, HttpStatus.NO_CONTENT );
      }
      AccountLockupResponse response = AccountLockupResponse.builder()
      .bankAccounts( accounts )
      .messages( MessageFormat.format( "Successfully returned {0} bank account(s)!", accounts.size() ) )
      .build();
      return new ResponseEntity<>( response, HttpStatus.OK );
    } catch (Exception e) {
      String safeErrorMessage = "Failed to complete get all accounts request!";
      //logger.log( Level.SEVERE, safeErrorMessage, e);
      return new ResponseEntity<>( new AccountLockupResponse( safeErrorMessage ), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }
  
  @GetMapping(path = "/byId/{id}")
  public ResponseEntity<AccountLockupResponse> getAccountById(@PathVariable(value = "id") String id) {
    try {
      List<BankAccount> accounts = queryDispatcher.send( new FindAccountByIdQuery( id ) );
      if (accounts == null || accounts.size() == 0) {
        return new ResponseEntity<>( null, HttpStatus.NO_CONTENT );
      }
      AccountLockupResponse response = AccountLockupResponse.builder()
      .bankAccounts( accounts )
      .messages( "Successfully returned bank account!" )
      .build();
      return new ResponseEntity<>( response, HttpStatus.OK );
    } catch (Exception e) {
      String safeErrorMessage = "Failed to complete get accounts by ID request!";
      logger.log( Level.SEVERE, safeErrorMessage, e );
      return new ResponseEntity<>( new AccountLockupResponse( safeErrorMessage ), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }
  
  @GetMapping(path = "/byHolder/{accountHolder}")
  public ResponseEntity<AccountLockupResponse> getAccountByHolder(@PathVariable(value = "accountHolder") String accountHolder) {
    try {
      List<BankAccount> accounts = queryDispatcher.send( new FindAccountByHolderQuery( accountHolder ) );
      if (accounts == null || accounts.size() == 0) {
        return new ResponseEntity<>( null, HttpStatus.NO_CONTENT );
      }
      AccountLockupResponse response = AccountLockupResponse.builder()
      .bankAccounts( accounts )
      .messages( "Successfully returned bank account!" )
      .build();
      return new ResponseEntity<>( response, HttpStatus.OK );
    } catch (Exception e) {
      String safeErrorMessage = "Failed to complete get accounts by holder request!";
      logger.log( Level.SEVERE, safeErrorMessage, e );
      return new ResponseEntity<>( new AccountLockupResponse( safeErrorMessage ), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }
  
/*  @GetMapping(path = "/withBalance/{equalityType}/{balance}")
  public ResponseEntity<AccountLockupResponse> getAccountWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType,
                                                                     @PathVariable(value = "balance") double balance) {
    
  }*/
}
