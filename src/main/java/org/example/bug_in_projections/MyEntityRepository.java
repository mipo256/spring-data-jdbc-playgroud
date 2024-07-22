package org.example.bug_in_projections;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MyEntityRepository extends CrudRepository<MyEntity, Long> {

    <T> Optional<T> findProjectionByStatus(String status, Class<T> clazz);
}