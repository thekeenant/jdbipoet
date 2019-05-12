package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableReference;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

class BaseNullableColumn<T> implements NullableColumn<T>, BaseExpression {
  private final Class<T> type;
  private final QualifiedColumnReference reference;

  BaseNullableColumn(Class<T> type, QualifiedColumnReference reference) {
    this.type = type;
    this.reference = reference;
  }

  @Override
  public SqlPart write(SqlContext context) {
    return reference.unqualify().write(context);
  }

  @Override
  public Class<T> nullableType() {
    return type;
  }

  @Override
  public QualifiedColumnReference qualified() {
    return reference;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Optional<T> map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
    Optional<Object> value = Optional.ofNullable(r.getObject(columnNumber));

    // Nothing to be done if we found null.
    if (!value.isPresent()) {
      return Optional.empty();
    }

    // If the value is already the correct type, return it.
    if (type.isInstance(value)) {
      return Optional.of((T) value);
    }

    // Try to map to correct type...
    ColumnMapper<T> mapper = ctx.findColumnMapperFor(type).orElse(null);
    if (mapper == null) {
      throw new IllegalStateException("No mapper to convert " + value + " into " + type);
    }
    return Optional.of(mapper.map(r, columnNumber, ctx));
  }

  @Override
  public TableReference table() {
    return reference.table();
  }

  @Override
  public ColumnReference unqualify() {
    return reference.unqualify();
  }

  @Override
  public FieldName name() {
    return reference.name();
  }
}
