package org.example.criteria;

import java.time.OffsetDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.example.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.test.context.jdbc.Sql;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:org/example/criteria/CriteriaExampleUsageTest.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:cleanup.sql")
public class CriteriaExampleUsageTest extends AbstractIntegrationTest {

  @Autowired
  private JdbcAggregateTemplate repository;

  @Test
  void testCriteriaApi() {
    Criteria like = Criteria
        .where("status").is("READY").ignoreCase(true)
        .and("created_at").between(OffsetDateTime.now().minusDays(2), OffsetDateTime.now().plusDays(2))
        .and("name").like("%J%");

    Query query = Query.query(like);

    Page<ForCriteria> allByQuery = repository.findAll(query, ForCriteria.class, Pageable.unpaged());

    Assertions.assertThat(allByQuery.getTotalElements()).isEqualTo(3);
  }
}
