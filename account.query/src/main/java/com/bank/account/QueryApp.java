package com.bank.account;

import com.bank.account.api.queries.*;
import com.bank.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApp {
	@Autowired
	QueryHandler queryHandler;
	@Autowired
	QueryDispatcher queryDispatcher;

	public static void main(String[] args) {
		SpringApplication.run( QueryApp.class, args);
	}

	@PostConstruct
	public void init(){
		queryDispatcher.registerHandler( FindAccountByIdQuery.class,queryHandler::handle );
		queryDispatcher.registerHandler( FindAccountByHolderQuery.class, queryHandler::handle );
		queryDispatcher.registerHandler( FindAllAccountsQuery.class, queryHandler::handle );
		queryDispatcher.registerHandler( FindAccountWithBalanceQuery.class, queryHandler::handle );
		
	}
}
