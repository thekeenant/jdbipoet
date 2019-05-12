package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableReference;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.statement.StatementContext;

class BaseColumn<T> implements Column<T>, BaseExpression {
  private final Class<T> type;
  private final QualifiedColumnReference reference;

  private final NullableColumn<T> delegate;

  BaseColumn(Class<T> type, QualifiedColumnReference reference) {
    this.type = type;
    this.reference = reference;
    this.delegate = new BaseNullableColumn<>(type, reference);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return reference.unqualify().write(context);
  }

  @Override
  public QualifiedColumnReference qualified() {
    return reference;
  }

  @Override
  public Class<T> type() {
    return type;
  }

  @Override
  public T map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
    return delegate.map(r, columnNumber, ctx)
        .orElseThrow(() -> new IllegalStateException("Null not allowed for column: " + reference.name().asString() + " with type " + type));
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
