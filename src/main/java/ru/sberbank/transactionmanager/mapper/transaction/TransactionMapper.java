package ru.sberbank.transactionmanager.mapper.transaction;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.transaction.Transaction;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.mapper.account.AccountMapper;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TransactionMapper implements Mapper<Transaction, TransactionDTO> {

    AccountMapper accountMapper;

    TransactionTypeMapper transactionTypeMapper;

    @Override
    public Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(
                transactionDTO,
                transaction,
                "transactionType", "sourceAccount", "destinationAccount"
        );
        transaction.setTransactionType(transactionTypeMapper.toEntity(transactionDTO.getTransactionType()));
        Optional.ofNullable(transactionDTO.getSourceAccount())
                .ifPresent(source -> transaction.setSourceAccount(accountMapper.toEntity(source)));
        Optional.ofNullable(transactionDTO.getDestinationAccount())
                .ifPresent(destination -> transaction.setDestinationAccount(accountMapper.toEntity(destination)));
        return transaction;
    }

    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        BeanUtils.copyProperties(
                transaction,
                transactionDTO,
                "transactionType", "sourceAccount", "destinationAccount"
        );
        transactionDTO.setTransactionType(transactionTypeMapper.toDTO(transaction.getTransactionType()));
        Optional.ofNullable(transaction.getSourceAccount())
                .ifPresent(source -> transactionDTO.setSourceAccount(accountMapper.toDTO(source)));
        Optional.ofNullable(transaction.getDestinationAccount())
                .ifPresent(destination -> transactionDTO.setDestinationAccount(accountMapper.toDTO(destination)));
        transactionDTO.setDestinationAccount(accountMapper.toDTO(transaction.getDestinationAccount()));
        return transactionDTO;
    }

}
