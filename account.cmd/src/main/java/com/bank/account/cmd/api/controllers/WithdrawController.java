package com.bank.account.cmd.api.controllers;
import com.bank.account.cmd.api.commands.WithdrawFundsCommand;
import com.bank.account.common.dtos.BaseResponse;
import com.bank.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Logger;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */
@RestController
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawController {
  Logger logger=Logger.getLogger( WithdrawController.class.getName() );
  @Autowired
  private CommandDispatcher commandDispatcher;
  
  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable String id,
                                                    @RequestBody WithdrawFundsCommand command){
    command.setId( id );
    
    try {
      commandDispatcher.send( command );
      return new ResponseEntity<>( new BaseResponse("Withdraw account creation request " +
                                                    " completely successfully.") ,
                                   HttpStatus.OK);
    } catch (IllegalStateException ex){
      String error= MessageFormat.format( "Client made bad request for {id}",command.getId() );
      return new ResponseEntity<>( new BaseResponse(error),HttpStatus.BAD_REQUEST );
    
    }catch (Exception e) {
      String error= MessageFormat.format( "Error when processing request to withdraw bank " +
                                          "account for new id{0}",command.getId() );
      return new ResponseEntity<>( new BaseResponse(error),HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }
}
