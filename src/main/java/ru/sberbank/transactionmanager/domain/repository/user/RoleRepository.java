package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role getRoleByCode(String code);

}
