package org.example.model;

import java.time.Instant;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@Table
public class MyEntity {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String status;

    @CreatedDate
    private Instant createdAt;
}