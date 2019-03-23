package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByCode(String code);

}
