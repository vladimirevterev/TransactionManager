package ru.sberbank.transactionmanager.annotation;

import org.hibernate.annotations.ValueGenerationType;
import ru.sberbank.transactionmanager.generator.AccountNumberGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = AccountNumberGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNumberGeneration {
}
