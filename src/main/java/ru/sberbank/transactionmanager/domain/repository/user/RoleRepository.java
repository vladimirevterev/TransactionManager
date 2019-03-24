package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findRoleByCode(String code);

}
