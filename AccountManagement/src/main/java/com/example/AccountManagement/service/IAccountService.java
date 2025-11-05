package com.example.AccountManagement.service;

import com.example.AccountManagement.dto.AccountBalanceDto;
import com.example.AccountManagement.dto.AccountCreateDto;
import com.example.AccountManagement.dto.AccountDto;
import com.example.AccountManagement.dto.AccountUpdateDto;

import java.util.List;

public interface IAccountService {

    AccountDto createAccount(AccountCreateDto accountCreateDto);

    AccountBalanceDto checkBalance(String accountNumber);

    AccountDto updateAccount(AccountUpdateDto accountUpdateDto);

    boolean closeAccount(String accountNumber);


    AccountDto fetchAccount(String accountNumber);

    String blockAccount(String accountNumber, String reason);

    String unblockAccount(String accountNumber);

    String verifyAccount(String accountNumber);

    String generateAccountHistory(String accountNumber);


}
