package com.SpringCloudApp.service.Impl;

import com.SpringCloudApp.dto.request.AccountDto;
import com.SpringCloudApp.model.Account;
import com.SpringCloudApp.model.elastic.AccountModel;
import com.SpringCloudApp.repository.AccountRepository;
import com.SpringCloudApp.repository.elastic.AccountModelRepository;
import com.SpringCloudApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountModelRepository accountModelRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public AccountDto getAccountById(String id) {

        return modelMapper.map(accountRepository.findById(id).orElseThrow(IllegalAccessError::new),AccountDto.class);
    }

    @Override
    public AccountDto saveAccount(AccountDto account) {

        Account _account = modelMapper.map(account, Account.class);

       _account =  accountRepository.save(_account);

        AccountModel accountModel = AccountModel.builder()
                .id(_account.getId())
                .email(_account.getEmail())
                .birthDate(_account.getBirthDate())
                .username(_account.getUsername())
                .surname(_account.getSurname())
                .name(_account.getName()).build();

        accountModelRepository.save(accountModel);

        return modelMapper.map(_account,AccountDto.class);
    }

    @Override
    public AccountDto updateAccount(String id, AccountDto account) {
        AccountModel accountModel = accountModelRepository.findById(id).orElseThrow(IllegalAccessError::new);
        Account _account = accountRepository.findById(id).orElseThrow(IllegalAccessError::new);

        accountModel = modelMapper.map(account,AccountModel.class);
        _account = modelMapper.map(account, Account.class);

        accountRepository.save(_account);
        accountModelRepository.save(accountModel);


        return modelMapper.map(_account,AccountDto.class);
    }

    @Override
    public void deleteAccount(String id) {

    }

    @Override
    public List<AccountDto> findAllAccount(Pageable pageable) {
        return null;
    }
}
