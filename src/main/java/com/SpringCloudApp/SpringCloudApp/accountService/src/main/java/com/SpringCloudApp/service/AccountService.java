package com.SpringCloudApp.service;

import com.SpringCloudApp.dto.request.AccountDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface AccountService {

    AccountDto getAccountById(String id);

    AccountDto saveAccount(AccountDto account);

    AccountDto updateAccount(String id, AccountDto account);

    void deleteAccount(String id);

    List<AccountDto> findAllAccount(Pageable pageable);
}
