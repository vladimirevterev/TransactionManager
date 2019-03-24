package ru.sberbank.transactionmanager.domain.entity.user;

import lombok.*;
import ru.sberbank.transactionmanager.domain.entity.Auditable;

import javax.persistence.*;

/**
 * Сущность, описывающая таблицу "Пользователь"
 */
@Entity
@Table(name = "USER_ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRole extends Auditable<Long> {

    /**
     * Пользователь, с которым связана роль
     */
    @ManyToOne
    @JoinColumn(
            name = "USER_INFO_ID",
            foreignKey = @ForeignKey(name = "USER_INFO_USER_ROLE_FK"),
            nullable = false
    )
    public UserInfo userInfo;

    /**
     * Роль пользователя
     */
    @ManyToOne
    @JoinColumn(
            name = "ROLE_ID",
            foreignKey = @ForeignKey(name = "ROLE_USER_ROLE_FK"),
            nullable = false
    )
    public Role role;

}
