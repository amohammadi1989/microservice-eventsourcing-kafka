package com.bank.account.cmd.api.controllers;
import com.bank.account.cmd.api.commands.CloseAccountCommand;
import com.bank.account.common.dtos.BaseResponse;
import com.bank.core.infrastructure.CommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */
@RestController
@RequestMapping("/api/v1/closeBankAccount")
public class CloseAccountController {
  @Autowired
  private CommandDispatcher commandDispatcher;
  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> closedBankAccount(@PathVariable String id){
    
    try {
      CloseAccountCommand command=new CloseAccountCommand(id);
      commandDispatcher.send( command );
      return new ResponseEntity<>( new BaseResponse("Bank account closed completely successfully." ), HttpStatus.OK );
    } catch (Exception e) {
      return new ResponseEntity<>( new BaseResponse("Error while processing request to closed " +
                                                    "exists bank account"),
                                   HttpStatus.BAD_REQUEST );
    }
    
  }
}
