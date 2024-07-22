package org.example.single_select;

import java.util.List;
import org.example.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:org/example/single_select/RootEntityDifferentSelectTest.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:cleanup.sql")
public class RootEntityDifferentSelectTest extends AbstractIntegrationTest {

  @Autowired
  private RootEntityRepository repository;

  @Test
  void test() {
      repository.save(new RootEntity().setReferencedEntities(List.of(new ReferencedEntity().setStatus("ACTIVE"))));
  }
}
