package ru.sberbank.transactionmanager.annotation;

import org.hibernate.annotations.ValueGenerationType;
import ru.sberbank.transactionmanager.generator.UuidGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = UuidGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UuidGeneration {
}
