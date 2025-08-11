package org.example.criteria_child_agg_search;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table
public class RootAgg {

    @Id
    private Long id;

    @Column(value = "name_c")
    private String name;

    @MappedCollection(idColumn = "root_agg_id")
    private ChildAgg child;

    @Setter
    @Getter
    @Table
    public static class ChildAgg {

        @Id
        private Long id;

        private String type;
    }
}
