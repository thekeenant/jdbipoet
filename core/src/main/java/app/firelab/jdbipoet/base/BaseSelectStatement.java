package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlStatement;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;

class BaseSelectStatement implements SelectStatement, BaseExpression {
  private final LazyExpression<?> what;
  private final LazyExpression<?> from;

  @Nullable
  private final LazyExpression<?> whereFilter;
  @Nullable
  private final LazyExpression<?> groupBy;
  @Nullable
  private final LazyExpression<?> having;
  @Nullable
  private final List<Entry<SetOperation, LazyExpression<?>>> setOperations;
  @Nullable
  private final LazyExpression<?> orderBy;
  @Nullable
  private final LazyExpression<?> limit;
  @Nullable
  private final LazyExpression<?> offset;

  private BaseSelectStatement(
      LazyExpression<?> what,
      LazyExpression<?> from,
      @Nullable LazyExpression<?> whereFilter,
      @Nullable LazyExpression<?> groupBy,
      @Nullable LazyExpression<?> having,
      @Nullable List<Entry<SetOperation, LazyExpression<?>>> setOperations,
      @Nullable LazyExpression<?> orderBy,
      @Nullable LazyExpression<?> limit,
      @Nullable LazyExpression<?> offset
  ) {
    this.what = what;
    this.from = from;
    this.whereFilter = whereFilter;
    this.groupBy = groupBy;
    this.having = having;
    this.setOperations = setOperations;
    this.orderBy = orderBy;
    this.limit = limit;
    this.offset = offset;
  }

  @Override
  public SqlStatement write(SqlContext context) {
    BaseSqlPart.Builder builder = BaseSqlPart.builder(context)
        .append("SELECT ")
        .append(what)
        .append(" FROM ")
        .append(from);

    if (whereFilter != null) {
      builder.append(" WHERE ")
          .append(whereFilter);
    }

    if (groupBy != null) {
      builder.append(" GROUP BY ")
          .append(groupBy);
    }

    if (having != null) {
      builder.append(" HAVING ")
          .append(having);
    }

    if (orderBy != null) {
      builder.append(" ORDER BY ")
          .append(orderBy);
    }

    if (limit != null) {
      builder.append(" LIMIT ")
          .append(limit);
    }

    if (offset != null) {
      builder.append(" OFFSET ")
          .append(offset);
    }

    if (setOperations != null) {
      setOperations.forEach(entry -> {
        SetOperation operation = entry.getKey();
        LazyExpression<?> statement = entry.getValue();

        builder.append(' ')
            .append(operation.name())
            .append(' ')
            .append(statement);
      });
    }

    return builder.buildStatement();
  }

  static Builder builder(LazyExpression<?> what, LazyExpression<?> table) {
    return new Builder(what, table);
  }

  private enum SetOperation {
    UNION,
    INTERSECT,
    EXCLUDE
  }

  static class Builder {
    private final LazyExpression<?> what;
    private final LazyExpression<?> from;
    private LazyExpression<?> whereFilter;
    private LazyExpression<?> groupBy;
    private LazyExpression<?> having;
    private List<Entry<SetOperation, LazyExpression<?>>> setOperations;
    private LazyExpression<?> orderBy;
    private LazyExpression<?> limit;
    private LazyExpression<?> offset;

    private Builder(LazyExpression<?> what, LazyExpression<?> from) {
      this.what = what;
      this.from = from;
    }

    Builder copy() {
      return new Builder(what, from)
          .withWhereFilter(whereFilter)
          .withGroupBy(groupBy)
          .withHaving(having)
          .withSetOperations(setOperations)
          .withOrderBy(orderBy)
          .withLimit(limit)
          .withOffset(offset);
    }

    Builder withWhereFilter(LazyExpression<?> whereFilter) {
      this.whereFilter = whereFilter;
      return this;
    }

    Builder withGroupBy(LazyExpression<?> groupBy) {
      this.groupBy = groupBy;
      return this;
    }

    Builder withHaving(LazyExpression<?> having) {
      this.having = having;
      return this;
    }

    private Builder withSetOperations(List<Entry<SetOperation, LazyExpression<?>>> setOperations) {
      this.setOperations = setOperations;
      return this;
    }

    private Builder withSetOperation(SetOperation operation, LazyExpression<?> select) {
      if (setOperations == null) {
        setOperations = new ArrayList<>();
      }
      setOperations.add(new SimpleEntry<>(operation, select));
      return this;
    }

    Builder withUnion(LazyExpression<?> select) {
      return withSetOperation(SetOperation.UNION, select);
    }

    Builder withIntersect(LazyExpression<?> select) {
      return withSetOperation(SetOperation.INTERSECT, select);
    }

    Builder withExclude(LazyExpression<?> select) {
      return withSetOperation(SetOperation.EXCLUDE, select);
    }

    Builder withOrderBy(LazyExpression<?> orderBy) {
      this.orderBy = orderBy;
      return this;
    }

    Builder withLimit(LazyExpression<?> limit) {
      this.limit = limit;
      return this;
    }

    Builder withOffset(LazyExpression<?> offset) {
      this.offset = offset;
      return this;
    }

    BaseSelectStatement build() {
      return new BaseSelectStatement(
          what,
          from,
          whereFilter,
          groupBy,
          having,
          setOperations,
          orderBy,
          limit,
          offset
      );
    }
  }
}
