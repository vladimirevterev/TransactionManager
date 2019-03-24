package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.UserRole;

@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {
}
