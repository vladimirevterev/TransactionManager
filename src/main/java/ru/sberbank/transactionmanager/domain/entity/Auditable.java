package ru.sberbank.transactionmanager.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Auditable<U> extends Identified {

    /**
     * Пользователь-создатель записи
     */
    @CreatedBy
    protected U createdBy;

    /**
     * Дата создания записи в системе
     */
    @CreatedDate
    protected LocalDateTime creationDate;

    /**
     * Пользователь, который модифицировал запись
     */
    @LastModifiedBy
    protected U lastModifiedBy;

    /**
     * Дата обновления записи в системе
     */
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

}
