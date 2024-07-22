package org.example.single_select;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Accessors(chain = true)
@Table("single_select_root_entity")
public class RootEntity {

  @Id
  private Long id;

  @CreatedDate
  private OffsetDateTime createdAt;

  @MappedCollection(idColumn = "root_entity_id")
  private List<ReferencedEntity> referencedEntities;
}
