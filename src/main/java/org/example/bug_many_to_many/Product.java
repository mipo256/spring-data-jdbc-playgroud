package org.example.bug_many_to_many;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Persistable<Long> {

  @Id
  private Long id;

  private String name;

  @Transient
  private boolean isNew;

  @MappedCollection(keyColumn = "product_id", idColumn = "product_id")
  private List<ProductCategories> productCategories;

  public static Product createNew(Long id, String name, List<ProductCategories> productCategories) {
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setNew(true);
    product.setProductCategories(productCategories);
    return product;
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }
}
