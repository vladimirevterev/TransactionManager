package ru.sberbank.transactionmanager.mapper.account;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;

@Component
@AllArgsConstructor
public class AccountMapper implements Mapper<Account, AccountDTO> {

    @Override
    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(account.id);
        account.setUserInfo(userInfo);
        return account;
    }

    @Override
    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        accountDTO.setUserId(account.getUserInfo().getId());
        return accountDTO;
    }

}
