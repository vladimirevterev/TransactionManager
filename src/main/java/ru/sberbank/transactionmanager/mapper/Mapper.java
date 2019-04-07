package ru.sberbank.transactionmanager.mapper;

/**
 * Интерфейс, описывающий контракт отображения данных между сущностями DTO и Entity
 * @param <E> тип Entity сущности
 * @param <D> тип DTO сущности
 */
public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);

}
