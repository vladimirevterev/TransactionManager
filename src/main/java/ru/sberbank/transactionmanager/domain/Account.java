package ru.sberbank.transactionmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import ru.sberbank.transactionmanager.annotation.UuidGeneration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    /**
     * Идентификатор счета
     */
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public Long id;

    /**
     * Номер счета
     */
    @UuidGeneration
    @Column(name = "NUMBER")
    public String number;

    /**
     * Остаток средств по счету
     */
    @Column(name = "BALANCE")
    public Double balance;

    /**
     * Признак, указывающий на то, что данный счет является главным для пользователя
     */
    @Column(name = "IS_PRIMARY")
    public Boolean isPrimary;

    /**
     * Дата создания пользователя в системе
     */
    @Temporal(TemporalType.TIME)
    @Generated(GenerationTime.INSERT)
    @Column(name="CREATED_TIME", insertable=false)
    public Date createdTime;

    /**
     * Дата обновления пользователя в системе
     */
    @Temporal(TemporalType.TIME)
    @Generated(GenerationTime.ALWAYS)
    @Column(name="MODIFIED_TIME", insertable=false,updatable=false)
    public Date modifiedTime;

    /**
     * Пользователь-владелец счета
     */
    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID", foreignKey = @ForeignKey(name = "USER_INFO_ID_FK"))
    UserInfo userInfo;

}
