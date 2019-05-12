package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.SqlStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class BaseSqlPart implements SqlPart {

  private final String sql;
  private final List<Object> bindings;

  BaseSqlPart(String sql, List<Object> bindings) {
    this.sql = sql;
    this.bindings = bindings;
  }

  @Override
  public String sql() {
    return sql;
  }

  @Override
  public List<Object> bindings() {
    return Collections.unmodifiableList(bindings);
  }

  @Override
  public SqlStatement toStatement(SqlContext context) {
    return new BaseSqlStatement(context, sql, bindings);
  }

  static Builder builder(SqlContext context) {
    return new Builder(context);
  }

  static BasicBuilder builder() {
    return new BasicBuilder();
  }

  static class Builder extends BasicBuilder {

    private final SqlContext context;

    public Builder(SqlContext context) {
      this.context = context;
    }

    public Builder append(LazyExpression expression) {
      return append(expression.write(context));
    }

    public SqlStatement buildStatement() {
      return new BaseSqlStatement(context, sql.toString(), bindings).toStatement(context);
    }

    @Override
    public <T> Builder append(String sql, Collection<T> bindings) {
      return (Builder) super.append(sql, bindings);
    }

    @Override
    public <T> Builder append(String sql, T binding) {
      return (Builder) super.append(sql, binding);
    }

    @Override
    public Builder append(char character) {
      return (Builder) super.append(character);
    }

    @Override
    public Builder append(String sql) {
      return (Builder) super.append(sql);
    }

    @Override
    public Builder append(SqlPart part) {
      return (Builder) super.append(part);
    }
  }

  static class BasicBuilder {
    final StringBuilder sql = new StringBuilder();
    final List<Object> bindings = new ArrayList<>();

    private BasicBuilder() {
    }

    public <T> BasicBuilder append(String sql, Collection<T> bindings) {
      this.sql.append(sql);
      this.bindings.addAll(bindings);
      return this;
    }

    public <T> BasicBuilder append(String sql, T binding) {
      this.sql.append(sql);
      this.bindings.add(binding);
      return this;
    }

    public BasicBuilder append(char character) {
      this.sql.append(character);
      return this;
    }

    public BasicBuilder append(String sql) {
      return append(sql, Collections.emptyList());
    }

    public BasicBuilder append(SqlPart part) {
      return append(part.sql(), part.bindings());
    }

    public SqlPart build() {
      return new BaseSqlPart(sql.toString(), bindings);
    }
  }
}
