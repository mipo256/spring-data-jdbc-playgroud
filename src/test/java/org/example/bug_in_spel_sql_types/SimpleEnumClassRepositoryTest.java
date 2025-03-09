package org.example.bug_in_spel_sql_types;

import java.util.UUID;
import org.example.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class SimpleEnumClassRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private SimpleEnumClassRepository repository;

  @Test
  @Sql(statements = """
      CREATE TABLE simple_enum_class(
        id UUID PRIMARY KEY,
        enum_class TEXT
      );
      """)
  void test() {
    repository.saveCustom(new SimpleEnumClass(UUID.randomUUID(), EnumClass.ACTIVE));
  }
}
