package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlStatement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

class BaseInsertStatement implements SelectStatement, BaseExpression {
  private final LazyExpression<?> into;
  @Nullable
  private final List<Map<String, LazyExpression<?>>> values;
  @Nullable
  private final OnConflictExpression onConflict;

  private BaseInsertStatement(
      LazyExpression<?> into,
      @Nullable
      List<Map<String, LazyExpression<?>>> values,
      @Nullable
      OnConflictExpression onConflict
  ) {
    this.into = into;
    this.values = values;
    this.onConflict = onConflict;
  }

  @Override
  public SqlStatement write(SqlContext context) {
    BaseSqlPart.Builder builder = BaseSqlPart.builder(context)
        .append("INSERT INTO ")
        .append(into);


    if (values == null) {
      builder.append(" DEFAULT VALUES");
    } else {
      if (values.isEmpty()) {
        throw new IllegalStateException("Cannot insert when no values provided");
      }

      Set<String> columns = values.stream()
          .flatMap(map -> map.keySet().stream())
          .collect(Collectors.toCollection(LinkedHashSet::new));

      builder.append(" (");
      builder.append(String.join(",", columns));
      builder.append(") VALUES ");

      for (int i = 0; i < values.size(); i++) {
        Map<String, LazyExpression<?>> row = values.get(i);
        builder.append("(");

        for (String column : columns) {
          @Nullable
          LazyExpression<?> value = row.get(column);

          if (value == null) {
            value = Sql.expressions().nullValue();
          }

          builder.append(value);
        }

        builder.append(")");

        if (i != values.size() - 1) {
          builder.append(",");
        }
      }
    }

    if (onConflict != null) {
      builder.append(" ")
          .append(onConflict);
    }

    return builder.buildStatement();
  }

  static Builder builder(LazyExpression<?> into) {
    return new Builder(into);
  }

  static class Builder {
    private final LazyExpression<?> into;

    private Builder(LazyExpression<?> into) {
      this.into = into;
    }

  }
}
