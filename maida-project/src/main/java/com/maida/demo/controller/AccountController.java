package com.maida.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maida.demo.model.Account;
import com.maida.demo.service.AccountService;

import lombok.experimental.var;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<Account> register (@RequestBody Account account, @RequestHeader String Authorization){
		var newAccount = accountService.register(account, Authorization);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}
	
	@GetMapping("/balance")
	public ResponseEntity<Account> showBalance(@RequestBody Account account, @RequestHeader String Authorization){
		var balance = accountService.showBalance(account, Authorization);
		return ResponseEntity.ok(balance);
	}
	

}
