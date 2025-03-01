package org.example.bug_many_to_many;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

  @Id
  private Long id;

  private String name;

  @MappedCollection(idColumn = "category_id", keyColumn = "category_id")
  private List<ProductCategories> productCategories;
}
