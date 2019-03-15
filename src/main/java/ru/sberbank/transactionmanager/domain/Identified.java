package ru.sberbank.transactionmanager.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class Identified implements Serializable {

    /**
     * Идентификатор записи
     */
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public Long id;

}
