package com.bank.account.cmd.api.controllers;
import com.bank.account.cmd.api.commands.DepositFundsCommand;
import com.bank.account.cmd.api.dtos.OpenAccountResponse;
import com.bank.account.common.dtos.BaseResponse;
import com.bank.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@RestController
@RequestMapping(path = "/api/v1/depositFunds")
public class DepositFundsController {
  Logger logger=Logger.getLogger( OpenAccountController.class.getName() );
  @Autowired
  CommandDispatcher commandDispatcher;
  @PutMapping(path = "/{id}")
  public ResponseEntity<BaseResponse> depositFunds(@PathVariable String id,
                                                   @RequestBody DepositFundsCommand command){
    
    command.setId( id );
  
    try {
      commandDispatcher.send( command );
      return new ResponseEntity<>( new BaseResponse( "Deposit Account " +
      "creation request completely successfully.") ,
                                   HttpStatus.OK);
    } catch (IllegalStateException e) {
      logger.log( Level.WARNING, MessageFormat.format( "Client made a bad request,{0}",
                                                       e.getMessage() ) );
      return new ResponseEntity<>( new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST );
    } catch (Exception e){
      String error=MessageFormat.format( "Error while processing request to deposit bank account " +
                                         "for " +
                                         "new id.{0}",command.getId() );
      logger.log( Level.SEVERE,e.toString() );
      return new ResponseEntity<>( new BaseResponse(error),
                                   HttpStatus.INTERNAL_SERVER_ERROR );
    }
  
  }
  
}
