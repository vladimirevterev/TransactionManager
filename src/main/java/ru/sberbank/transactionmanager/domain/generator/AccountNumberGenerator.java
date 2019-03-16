package ru.sberbank.transactionmanager.domain.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;
import ru.sberbank.transactionmanager.domain.generator.annotation.AccountNumberGeneration;

public class AccountNumberGenerator implements AnnotationValueGeneration<AccountNumberGeneration> {
    @Override
    public void initialize(AccountNumberGeneration annotation, Class<?> propertyType) {

    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return (ValueGenerator<String>) (session, owner) -> RandomStringUtils.randomNumeric(20);
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}
