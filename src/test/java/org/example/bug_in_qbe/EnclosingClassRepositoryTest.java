package org.example.bug_in_qbe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.example.AbstractIntegrationTest;
import org.example.bug_in_qbe.EnclosingClass.Address;
import org.example.bug_in_spel_sql_types.EnumClass;
import org.example.bug_in_spel_sql_types.SimpleEnumClass;
import org.example.bug_in_spel_sql_types.SimpleEnumClassRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.jdbc.Sql;

class EnclosingClassRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private EnclosingClassRepository enclosingClassRepository;

  @Test
  @Sql(statements = """
      CREATE TABLE enclosing_class(
        id UUID PRIMARY KEY,
        name TEXT,
        street TEXT,
        city TEXT
      );
      """)
  void test() {
    EnclosingClass enclosingClass = new EnclosingClass()
        .setName("Alex")
        .setAddress(
            new Address()
                .setCity("Berlin")
                .setStreet("abc")
        );

    var all = enclosingClassRepository.findAll(
        Example.of(
            enclosingClass,
            ExampleMatcher.matching()
                .withIgnoreCase("name")
                .withIgnoreCase("address.city")
        )
    );
  }
}
