package org.example.bug_in_spel_sql_types;

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class SimpleEnumClass(
  @Id
  var id: UUID,
  val enumClass: EnumClass
) {
}
