package com.bank.account.cmd;

import com.bank.account.cmd.api.commands.*;
import com.bank.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApp {
	@Autowired
	CommandDispatcher commandDispatcher;
	@Autowired
	CommandHandler commandHandler;
	public static void main(String[] args) {
		SpringApplication.run( CommandApp.class, args);
	}
	
	@PostConstruct
	public void init(){
		commandDispatcher.registerHandler( OpenAccountCommand.class,commandHandler::handle );
		commandDispatcher.registerHandler( WithdrawFundsCommand.class, commandHandler::handle );
		commandDispatcher.registerHandler( DepositFundsCommand.class, commandHandler::handle );
		commandDispatcher.registerHandler( CloseAccountCommand.class, commandHandler::handle );
		
	}
}
