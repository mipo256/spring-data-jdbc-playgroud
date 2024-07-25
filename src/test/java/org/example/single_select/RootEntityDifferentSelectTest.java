package org.example.single_select;

import java.util.List;
import java.util.Optional;
import org.example.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.jdbc.Sql;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:org/example/single_select/RootEntityDifferentSelectTest.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:cleanup.sql")
public class RootEntityDifferentSelectTest extends AbstractIntegrationTest {

  @Autowired
  private RootEntityRepository repository;

  @Autowired
  private RelationalMappingContext relationalMappingContext;

  @SpyBean
  private NamedParameterJdbcOperations jdbcTemplate;

  @Test
  void testLoadingWithSingleSelect() {

    // given.
    RootEntity instance = repository.save(new RootEntity().setReferencedEntities(List.of(new ReferencedEntity().setStatus("ACTIVE"))));

    // when.
    Optional<RootEntity> rootEntity = repository.findById(instance.getId());

    // then.
    Mockito.verify(jdbcTemplate).query(Mockito.anyString(), Mockito.any(SqlParameterSource.class), Mockito.any(ResultSetExtractor.class));
  }
}
