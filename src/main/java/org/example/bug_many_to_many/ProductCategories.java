package org.example.bug_many_to_many;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
public class ProductCategories {

  private AggregateReference<Product, Long> productId;
  private AggregateReference<Category, Long> categoryId;
}
