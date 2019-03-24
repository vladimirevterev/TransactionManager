package ru.sberbank.transactionmanager.mapper.account;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;

@Component
@AllArgsConstructor
public class AccountMapper implements Mapper<Account, AccountDTO> {

    @Override
    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account, "userInfo");
        return account;
    }

    @Override
    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO, "userInfo");
        return accountDTO;
    }

}
