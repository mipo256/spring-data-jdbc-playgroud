package org.example.bug_many_to_many;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.example.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

public class ProductRepositryIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, statements = """
      CREATE TABLE IF NOT EXISTS product(
        id BIGSERIAL PRIMARY KEY,
        name TEXT
      );

      CREATE TABLE IF NOT EXISTS category(
        id BIGSERIAL PRIMARY KEY,
        name TEXT
      );

      CREATE TABLE IF NOT EXISTS product_categories(
        product_id BIGINT,
        category_id BIGINT
      );
      
      INSERT INTO category(id, name) VALUES(1, 'groceries');
      """)
//  @Transactional
  void testManyToManyPersistence() {
    // given.
    Product product = Product.createNew(
        1L,
        "product first",
        List.of(
            new ProductCategories()
                .setProductId(AggregateReference.to(1L))
                .setCategoryId(AggregateReference.to(1L))
        )
    );

    // when.
    Product saved = productRepository.save(product);

    // then.
    Optional<Product> foundProduct = productRepository.findById(saved.getId());

    Assertions.assertThat(foundProduct).isPresent().hasValueSatisfying(it -> {
      Assertions.assertThat(it.getProductCategories())
          .hasSize(1) // this one fails, since loaded 'foundProduct' does have ProductCategories
          .first()
          .extracting("categoryId")
          .isEqualTo(AggregateReference.to(1L));
    });
  }
}
