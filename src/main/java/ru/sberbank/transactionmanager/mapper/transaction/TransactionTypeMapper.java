package ru.sberbank.transactionmanager.mapper.transaction;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.transaction.TransactionType;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionTypeDTO;

@Component
@AllArgsConstructor
public class TransactionTypeMapper implements Mapper<TransactionType, TransactionTypeDTO> {

    @Override
    public TransactionType toEntity(TransactionTypeDTO transactionTypeDTO) {
        TransactionType transactionType = new TransactionType();
        BeanUtils.copyProperties(transactionTypeDTO, transactionType);
        return transactionType;
    }

    @Override
    public TransactionTypeDTO toDTO(TransactionType transactionType) {
        TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
        BeanUtils.copyProperties(transactionType, transactionTypeDTO);
        return transactionTypeDTO;
    }

}
