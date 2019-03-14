package ru.sberbank.transactionmanager.generator;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;
import ru.sberbank.transactionmanager.annotation.UuidGeneration;

import java.util.UUID;

public class UuidGenerator implements AnnotationValueGeneration<UuidGeneration> {

    @Override
    public void initialize(UuidGeneration annotation, Class<?> propertyType) {

    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return (ValueGenerator<String>) (session, owner) -> UUID.randomUUID().toString();
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
