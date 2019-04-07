package ru.sberbank.transactionmanager.service.transaction.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.sberbank.transactionmanager.common.dictionary.TransactionTypeDictionary;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.transaction.Transaction;
import ru.sberbank.transactionmanager.domain.entity.transaction.TransactionType;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.account.AccountRepository;
import ru.sberbank.transactionmanager.domain.repository.transaction.TransactionRepository;
import ru.sberbank.transactionmanager.domain.repository.transaction.TransactionTypeRepository;
import ru.sberbank.transactionmanager.mapper.transaction.TransactionMapper;
import ru.sberbank.transactionmanager.rest.dto.operation.RemittanceDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.ReplenishmentDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.WithdrawalDTO;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;
import ru.sberbank.transactionmanager.service.transaction.TransactionService;
import ru.sberbank.transactionmanager.utils.ErrorHelper;
import ru.sberbank.transactionmanager.utils.UserUtils;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private AccountRepository accountRepository;
    private UserUtils userUtils;

    @Override
    @Transactional
    public TransactionDTO getTransaction(Long transactionId) throws TransactionManagerException {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        UserInfo currentUserInfo = userUtils.getCurrentUser();
        ErrorHelper.check(
                isNull(transaction),
                "Информация о транзакции с идентификатором " + transactionId + " не найдена",
                Error.TRANSACTION_NOT_FOUND
        );
        checkIfTransactionRelatedWithUser(transaction, currentUserInfo);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    @Transactional
    public Page<TransactionDTO> getTransactions(Pageable pageable) throws TransactionManagerException {
        UserInfo currentUser = userUtils.getCurrentUser();
        List<Account> userAccounts = currentUser.getAccounts();
        ErrorHelper.check(
                CollectionUtils.isEmpty(userAccounts),
                "С данным текущего пользователя не связано ни одного счета",
                Error.USER_HAS_NO_ACCOUNTS
        );
        return transactionRepository
                .findTransactionBySourceAccountInOrDestinationAccountIn(userAccounts, userAccounts, pageable)
                .map(transactionMapper::toDTO);
    }

    @Override
    @Transactional
    public void transferFunds(RemittanceDTO remittanceDTO) throws TransactionManagerException {
        checkTransactionAmount(remittanceDTO.getAmount());
        Account sourceAccount = getSourceAccount(remittanceDTO.getSourceAccountId(), remittanceDTO.getAmount());
        Account recipientAccount = getRecipientAccount(remittanceDTO);
        sourceAccount.setBalance(sourceAccount.getBalance() - remittanceDTO.getAmount());
        recipientAccount.setBalance(recipientAccount.getBalance() + remittanceDTO.getAmount());
        TransactionType transactionType =
                transactionTypeRepository.findTransactionTypeByCode(TransactionTypeDictionary.REMITTANCE.getCode());
        accountRepository.save(sourceAccount);
        accountRepository.save(recipientAccount);
        transactionRepository.save(
                Transaction.builder()
                        .transactionType(transactionType)
                        .sourceAccount(sourceAccount)
                        .destinationAccount(recipientAccount)
                        .amount(remittanceDTO.getAmount())
                        .build()
        );
    }

    @Override
    @Transactional
    public void replenishFunds(ReplenishmentDTO replenishmentDTO) throws TransactionManagerException {
        checkTransactionAmount(replenishmentDTO.getAmount());
        UserInfo currentUserInfo = userUtils.getCurrentUser();
        Account sourceAccount = findAccount(replenishmentDTO.getAccountId());
        if (nonNull(sourceAccount)) {
            checkIfUserHasAccount(currentUserInfo, sourceAccount);
        } else {
            sourceAccount = getSuitableUserAccount(currentUserInfo);
        }
        sourceAccount.setBalance(sourceAccount.getBalance() + replenishmentDTO.getAmount());
        accountRepository.save(sourceAccount);
        TransactionType transactionType =
                transactionTypeRepository.findTransactionTypeByCode(TransactionTypeDictionary.REPLENISHMENT.getCode());
        transactionRepository.save(
                Transaction.builder()
                        .transactionType(transactionType)
                        .sourceAccount(sourceAccount)
                        .destinationAccount(null)
                        .amount(replenishmentDTO.getAmount())
                        .build()
        );
    }

    @Override
    @Transactional
    public void withdrawalFunds(WithdrawalDTO withdrawalDTO) throws TransactionManagerException {
        checkTransactionAmount(withdrawalDTO.getAmount());
        Account sourceAccount = getSourceAccount(withdrawalDTO.getAccountId(), withdrawalDTO.getAmount());
        sourceAccount.setBalance(sourceAccount.getBalance() - withdrawalDTO.getAmount());
        accountRepository.save(sourceAccount);
        TransactionType transactionType =
                transactionTypeRepository.findTransactionTypeByCode(TransactionTypeDictionary.WITHDRAWAL.getCode());
        transactionRepository.save(
                Transaction.builder()
                        .transactionType(transactionType)
                        .sourceAccount(sourceAccount)
                        .destinationAccount(null)
                        .amount(withdrawalDTO.getAmount())
                        .build()
        );
    }

    /**
     * Метод получения счета текущего пользователя, на котором имеется
     * достаточно средств для осуществления операции превода/снятия средств
     *
     * @param currentUserAccountId счет текущего пользователя
     * @param requiredBalance минимальная сумма средств на счету
     * @return {@link Account} счет текущего пользователя
     */
    private Account getSourceAccount(Long currentUserAccountId, Double requiredBalance) throws TransactionManagerException {
        UserInfo currentUserInfo = userUtils.getCurrentUser();
        ErrorHelper.check(
                CollectionUtils.isEmpty(currentUserInfo.getAccounts()),
                "С текущим пользователем не связано ни одного счета",
                Error.USER_HAS_NO_ACCOUNTS
        );
        // Если счет задан явно - возвращаем его
        Account sourceAccount = findAccount(currentUserAccountId);
        if (nonNull(sourceAccount)) {
            checkIfUserHasAccount(currentUserInfo, sourceAccount);
            checkIfAccountHasEnoughFunds(sourceAccount, requiredBalance);
            return sourceAccount;
        }
        // Иначе ищем подходящий
        sourceAccount = currentUserInfo.getAccounts().stream()
                .filter(account -> account.balance >= requiredBalance)
                .max(Comparator.comparing(Account::getIsPrimary))
                .orElse(null);
        ErrorHelper.check(
                isNull(sourceAccount),
                "У текущего пользователя нет счета с достаточным количеством средств для осуществления операции",
                Error.INSUFFICIENT_FUNDS
        );
        return sourceAccount;
    }

    /**
     * Метод определения счета-получателя средств при переводе средств
     *
     * @param remittanceDTO информация о переводе средств
     * @return {@link Account} данные счета-получателя
     */
    private Account getRecipientAccount(RemittanceDTO remittanceDTO) throws TransactionManagerException {
        if (isNull(remittanceDTO.getRecipientId()) && isNull(remittanceDTO.getRecipientAccountId())) {
            ErrorHelper.makeError(
                    "Данные о переводе не содержат информацию о получателе средств",
                    Error.RECIPIENT_NOT_SPECIFIED
            );
        }
        UserInfo recipientUser = userUtils.getUserById(remittanceDTO.getRecipientId());
        Account recipientAccount = findAccount(remittanceDTO.getRecipientAccountId());
        if (nonNull(recipientAccount) && nonNull(recipientUser)) {
            checkIfUserHasAccount(recipientUser, recipientAccount);
            return recipientAccount;
        } else if (isNull(recipientAccount) && nonNull(recipientUser)) {
            return getSuitableUserAccount(recipientUser);
        }
        return recipientAccount;
    }

    /**
     * Получение данных о счете
     *
     * @param accountId идентификатор счета
     * @return {@link Account} данные счета
     */
    private Account findAccount(Long accountId) throws TransactionManagerException {
        if (isNull(accountId)) {
            return null;
        }
        Account recipientAccount = accountRepository.findById(accountId).orElse(null);
        ErrorHelper.check(
                isNull(recipientAccount),
                "Cчет с идентификатором " + accountId + " не найден",
                Error.ACCOUNT_NOT_FOUND
        );
        return recipientAccount;
    }


    /**
     * Поиск "подходящего" счета пользователя для зачисления средств.
     * "Подходящим" считается в первую очередь главный счет пользователя.
     * Если для пользователя не задан главный счет, выбирается произвольный счет.
     *
     * @param userInfo данные пользователя
     * @return {@link Account} счет пользователя
     */
    private Account getSuitableUserAccount(@NonNull UserInfo userInfo) throws TransactionManagerException {
        Account suitableAccount = userInfo.getAccounts().stream()
                .max(Comparator.comparing(Account::getIsPrimary))
                .orElse(null);
        ErrorHelper.check(
                isNull(suitableAccount),
                "С пользователем " + userInfo.getFullName() + " не связано ни одного счета",
                Error.USER_HAS_NO_ACCOUNTS
        );
        return suitableAccount;
    }

    /**
     * Проверка на то, что указанная сумма средств для операции перевода/пополнения/снятия положительная
     *
     * @param amount Сумма для операции перевода/пополнения/снятия
     */
    private void checkTransactionAmount(Double amount) throws TransactionManagerException {
        ErrorHelper.check(
                isNull(amount),
                "Указанная сумма для операции перевода/пополнения/снятия средств не задана",
                Error.TRANSACTION_AMOUNT_NOT_SPECIFIED
        );
        ErrorHelper.check(
                amount <= 0,
                "Указанная сумма для операции перевода/пополнения/снятия средств отрицательная или равна нулю",
                Error.TRANSACTION_AMOUNT_NOT_POSITIVE
        );
    }

    /**
     * Проверка на то, что пользователь связан со счетом
     *
     * @param recipientUser    данные пользователя
     * @param recipientAccount данные счета
     */
    private void checkIfUserHasAccount(@NotNull UserInfo recipientUser, @NotNull Account recipientAccount)
            throws TransactionManagerException {
        ErrorHelper.check(
                !recipientUser.getAccounts().stream()
                        .map(Account::getId)
                        .collect(Collectors.toList())
                        .contains(recipientAccount.getId()),
                "Пользователь " + recipientUser.getFullName() + " не связан со счетом " + recipientAccount.getNumber(),
                Error.USER_IS_NOT_ASSOCIATED_WITH_ACCOUNT
        );
    }

    /**
     * Проверка на то, что на счете имеется достаточно средств
     *
     * @param account данные счета
     * @param requiredBalance требуемое количество средств на счете
     */
    private void checkIfAccountHasEnoughFunds(@NonNull Account account, @NonNull Double requiredBalance) throws TransactionManagerException {
        ErrorHelper.check(
                account.getBalance() <= requiredBalance,
                "Недостаточно средств на счете " + account.getNumber() + ". " +
                        "Баланс счета: " + account.getBalance() + ". Требуется: " + requiredBalance,
                Error.INSUFFICIENT_FUNDS
        );
    }

    private void checkIfTransactionRelatedWithUser(@NotNull Transaction transaction, @NotNull UserInfo userInfo) throws TransactionManagerException {
        ErrorHelper.check(
                !(userInfo.getAccounts().contains(transaction.getSourceAccount())
                        || userInfo.getAccounts().contains(transaction.getDestinationAccount())),
                "Данная транзакция не связана с текущим пользователем",
                Error.USER_IS_NOT_ASSOCIATED_WITH_TRANSACTION
        );
    }
}
