package org.example.repository;

import java.util.Optional;
import org.example.model.MyEntity;
import org.springframework.data.repository.CrudRepository;

public interface MyEntityRepository extends CrudRepository<MyEntity, Long> {

    <T> Optional<T> findProjectionByStatus(String status, Class<T> clazz);
}