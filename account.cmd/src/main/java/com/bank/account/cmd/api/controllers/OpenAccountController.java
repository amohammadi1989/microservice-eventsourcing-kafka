package com.bank.account.cmd.api.controllers;
import com.bank.account.cmd.api.commands.OpenAccountCommand;
import com.bank.account.cmd.api.dtos.OpenAccountResponse;
import com.bank.account.common.dtos.BaseResponse;
import com.bank.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@RestController
@RequestMapping(path = "/api/v1/openBankAccount")
public class OpenAccountController {
  Logger logger=Logger.getLogger( OpenAccountController.class.getName() );
  @Autowired
  CommandDispatcher commandDispatcher;
  @PostMapping
  public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command){
    String id= UUID.randomUUID().toString();
    command.setId( id );
    
    try {
      commandDispatcher.send( command );
      return new ResponseEntity<>( new OpenAccountResponse(command.getId(),"Bank Account " +
      "creation request completely successfully.") ,
                                   HttpStatus.CREATED);
    } catch (IllegalStateException e) {
     logger.log( Level.WARNING, MessageFormat.format( "Client made a bad request,{0}",
                                                      e.getMessage() ) );
     return new ResponseEntity<>( new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST );
    } catch (Exception e){
    String error=MessageFormat.format( "Error while processing request to new bank account for " +
                                       "new id.{0}",command.getId() );
    logger.log( Level.SEVERE,e.toString() );
    return new ResponseEntity<>( new OpenAccountResponse(error,command.getId()),
                                 HttpStatus.INTERNAL_SERVER_ERROR );
    }
    
    
  }
}
