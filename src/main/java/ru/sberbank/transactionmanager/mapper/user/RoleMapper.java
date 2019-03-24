package ru.sberbank.transactionmanager.mapper.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.user.Role;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.rest.dto.user.RoleDTO;

@Component
@AllArgsConstructor
public class RoleMapper implements Mapper<Role, RoleDTO> {

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        return roleDTO;
    }

}
