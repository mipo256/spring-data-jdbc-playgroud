package org.example.criteria;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ForCriteria {

  @Id
  @EqualsAndHashCode.Include
  private Long id;

  private String status;

  private String name;

  private OffsetDateTime createdAt;
}
