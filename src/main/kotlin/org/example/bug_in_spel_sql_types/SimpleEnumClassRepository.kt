package org.example.bug_in_spel_sql_types;

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param

interface SimpleEnumClassRepository : CrudRepository<SimpleEnumClass, Long> {

  // language=sql
  @Modifying
  @Query(value = """
    INSERT INTO simple_enum_class(id, enum_class) 
    VALUES(:#{#simpleEnumClass.id}, :#{#simpleEnumClass.enumClass})
    """)
  fun saveCustom(@Param("simpleEnumClass") simpleEnumClass: SimpleEnumClass)
}
