package com.SpringCloudApp.controller;


import com.SpringCloudApp.dto.request.AccountDto;
import com.SpringCloudApp.model.elastic.AccountModel;
import com.SpringCloudApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/v1")
public class AccountController {
    private final AccountService service;
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto account) {
        return ResponseEntity.ok(service.saveAccount(account));
    }

    @PutMapping
    public ResponseEntity<AccountDto> update(@PathVariable("id") String id, @RequestBody AccountDto account) {
        return ResponseEntity.ok(service.updateAccount(id, account));
    }

    @DeleteMapping
    public void delete(String id) {
        service.deleteAccount(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAllAccount(pageable));
    }
}
