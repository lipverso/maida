package com.maida.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.maida.demo.exception.AccountNumberAlreadyExistentException;
import com.maida.demo.exception.AccountNumberNotFoundException;
import com.maida.demo.exception.UnauthorizedAccessException;
import com.maida.demo.model.Account;
import com.maida.demo.model.User;
import com.maida.demo.repository.AccountRepository;
import com.maida.demo.repository.UserRepository;

@Service
public class AccountService {

	//Field injection
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	public Account register(Account account, String token) {
		var user = findUserByToken(token);

		if (user != null) {
			account.setUser(user);
		}

		saveAccount(account);

		return account;
	}

	private void saveAccount(Account account) {
		var newAccount = findAccountByNumber(account);

		if (newAccount == null) {
			accountRepository.save(account);
		} else {
			throw new AccountNumberAlreadyExistentException();
		}
	}

	public Account showBalance(Account account, String token) {
		TokenService.validate(token);
		var user = findUserByToken(token);

		//boolean isValidAccountNumber = user.getAccounts().stream().anyMatch(u -> u.getNumber().equals(account.getNumber()));
		
		for(int i=0; i < user.getAccounts().size(); i++) {
			if (user.getAccounts().get(i).getNumber().equals(account.getNumber())) {
				return user.getAccounts().get(i);
			}
		}
		
		throw new AccountNumberNotFoundException();
		
		/*if(isValidAccountNumber) {
			return user.getAccounts().
		} else {
			throw new AccountNumberNotFoundException();
		}*/
		
	}

	private User findUserByToken(String authorization) {
		var user = userRepository.findByToken(authorization).orElseThrow(UnauthorizedAccessException::new);		
		return user;
	}

	private Account findAccountByNumber(Account account) {
		var findAccount = accountRepository.findByNumber(account.getNumber()).orElse(null);
		return findAccount;
	}

}
