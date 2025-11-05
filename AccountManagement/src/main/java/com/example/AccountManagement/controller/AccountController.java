package com.example.AccountManagement.controller;


import com.example.AccountManagement.dto.AccountBalanceDto;
import com.example.AccountManagement.dto.AccountCreateDto;
import com.example.AccountManagement.dto.AccountDto;
import com.example.AccountManagement.dto.AccountUpdateDto;
import com.example.AccountManagement.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/accounts")
@io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "Success",
        content = @io.swagger.v3.oas.annotations.media.Content
)
public class AccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/create_account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountCreateDto accountCreateDto)
    {
    AccountDto createdAccount = accountService.createAccount(accountCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAccount);
    }
    @GetMapping("/check_balance")
    public ResponseEntity<AccountBalanceDto> checkBalance(@RequestParam String accountNumber) {
        AccountBalanceDto balance = accountService.checkBalance(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balance);
    }

    @PutMapping("/update_account_details")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountUpdateDto accountUpdateDto) {
        AccountDto updatedAccount = accountService.updateAccount(accountUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedAccount);
    }

    @DeleteMapping("/close_account")
    public ResponseEntity<String> closeAccount(@RequestParam String accountNumber) {
        boolean closed = accountService.closeAccount(accountNumber);
        if (closed) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Account closed successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Account not found");
        }
    }

    @GetMapping("/fetch_general_data")
    public ResponseEntity<AccountDto> fetchAccount(@RequestParam String accountNumber) {
        AccountDto account = accountService.fetchAccount(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(account);
    }


    @PatchMapping("/block_account")
    public ResponseEntity<String> blockAccount(@RequestParam String accountNumber,
                                               @RequestParam(required = false, defaultValue = "Manual block") String reason) {
        String result = accountService.blockAccount(accountNumber, reason);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result + " - Account blocked successfully.");
    }

    @PatchMapping("/unblock_account")
    public ResponseEntity<String> unblockAccount(@RequestParam String accountNumber) {
        String result = accountService.unblockAccount(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result + " - Account unblocked successfully.");
    }

    @PatchMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam String accountNumber) {
        String result = accountService.verifyAccount(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result + " - Verify account successfully.");
    }

    @GetMapping("/history")
    public ResponseEntity<String> generateAccountHistory(@RequestParam String accountNumber) {
        String history = accountService.generateAccountHistory(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(history);
    }

}
