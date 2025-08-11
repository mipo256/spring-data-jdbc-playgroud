package org.example.criteria_child_agg_search;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.example.AbstractIntegrationTest;
import org.example.criteria_child_agg_search.RootAgg.ChildAgg;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.test.context.jdbc.Sql;

class RootAggCriteriaTest extends AbstractIntegrationTest {

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    @Sql(statements = """
            CREATE TABLE IF NOT EXISTS root_agg(id bigserial, name_c text);
            CREATE TABLE IF NOT EXISTS child_agg(id bigserial, type text, root_agg_id bigint);
            """)
    void testQueryingByChildAgo() {
        RootAgg rootAgg = new RootAgg()
                .setName("my_name")
                .setChild(new ChildAgg().setType("type"));

        List<RootAgg> result = jdbcAggregateTemplate.findAll(
                Query.query(Criteria
                        .where("name_c")
                        .is("my_name")
                        .and(Criteria.where("child_agg.type").is("type"))),
                RootAgg.class
        );

        Assertions.assertThat(rootAgg).isNotNull();
    }

}