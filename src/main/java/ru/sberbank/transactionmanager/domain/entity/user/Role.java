package ru.sberbank.transactionmanager.domain.entity.user;

import lombok.*;
import ru.sberbank.transactionmanager.domain.entity.Identified;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Сущность, описывающая таблицу "Справочник ролей пользователей"
 */
@Entity
@Table(name = "ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends Identified {

    private static final String ROLE_PATTERN = "ROLE_[A-Za-z]+";

    /**
     * Код роли
     */
    @Column(name = "CODE", nullable = false, unique = true, length = 3)
    public String code;

    /**
     * Наименование роли
     */
    @Column(name = "NAME", nullable = false, length = 64)
    @Pattern(regexp = ROLE_PATTERN)
    public String name;

    /**
     * Описание роли
     */
    @Column(name = "DESCRIPTION")
    public String description;

    /**
     * Список пользователей, с которыми связана роль
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_INFO_ID")
    )
    List<UserInfo> users;

}
