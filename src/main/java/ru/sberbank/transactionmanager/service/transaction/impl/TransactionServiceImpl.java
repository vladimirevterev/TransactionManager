package ru.sberbank.transactionmanager.service.transaction.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.transactionmanager.domain.repository.transaction.TransactionRepository;
import ru.sberbank.transactionmanager.mapper.transaction.TransactionMapper;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;
import ru.sberbank.transactionmanager.service.transaction.TransactionService;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;

    TransactionMapper transactionMapper;

    @Override
    public TransactionDTO getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(transactionMapper::toDTO)
                .orElse(null);
    }

}
