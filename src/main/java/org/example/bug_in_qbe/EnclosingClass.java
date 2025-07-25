package org.example.bug_in_qbe;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@Accessors(chain = true)
public class EnclosingClass {

  @Id
  private Long id;

  private String name;

  @Embedded.Nullable
  private Address address;

  @Data
  @Accessors(chain = true)
  static class Address {

    private String street;
    private String city;
  }
}
