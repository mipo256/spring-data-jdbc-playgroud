package org.example.config;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.RelationalManagedTypes;
import org.springframework.data.relational.core.mapping.NamingStrategy;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

  @Override
  public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy, JdbcCustomConversions customConversions,
      RelationalManagedTypes jdbcManagedTypes) {
    JdbcMappingContext jdbcMappingContext = super.jdbcMappingContext(namingStrategy, customConversions, jdbcManagedTypes);
    jdbcMappingContext.setSingleQueryLoadingEnabled(true);
    return jdbcMappingContext;
  }
}
