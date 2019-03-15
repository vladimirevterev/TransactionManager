package ru.sberbank.transactionmanager.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
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
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    /**
     * Пользователь, который модифицировал запись
     */
    @LastModifiedBy
    protected U lastModifiedBy;

    /**
     * Дата обновления записи в системе
     */
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

}
