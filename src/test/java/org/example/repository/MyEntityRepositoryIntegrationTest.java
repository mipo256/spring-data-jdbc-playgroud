package org.example.repository;

import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.assertj.core.api.Assertions;
import org.example.AbstractIntegrationTest;
import org.example.model.MyEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:org/example/repository/MyEntityRepositoryIntegrationTest.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:cleanup.sql")
public class MyEntityRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Test
    void whenDynamicProjectsAreInUseViaInterfaces_thenBlast() {
        saveInitialEntity();
        Optional<MyEntityInterfaceProjection> active = myEntityRepository.findProjectionByStatus("active", MyEntityInterfaceProjection.class);

        Assertions.assertThat(active)
            .isPresent()
            .hasValueSatisfying(myEntityProjection -> {
               Assertions.assertThat(myEntityProjection.id() != null && myEntityProjection.status().equalsIgnoreCase("active")).isTrue();
            });
    }

    @Test
    void whenDynamicProjectsAreInUseViaClasses_thenWorksAsExpected() {
        saveInitialEntity();
        Optional<MyEntityProjection> active = myEntityRepository.findProjectionByStatus("active", MyEntityProjection.class);

        Assertions.assertThat(active)
            .isPresent()
            .hasValueSatisfying(myEntityProjection -> {
               Assertions.assertThat(myEntityProjection.getId() != null && myEntityProjection.getStatus().equalsIgnoreCase("active")).isTrue();
            });
    }

    @NotNull
    private MyEntity saveInitialEntity() {
        MyEntity myEntity = new MyEntity();
        myEntity.setStatus("active");
        return myEntityRepository.save(myEntity);
    }

    interface MyEntityInterfaceProjection {
        String status();

        Long id();
    }

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    static class MyEntityProjection {
        private String status;

        @EqualsAndHashCode.Include
        private Long id;
    }
}