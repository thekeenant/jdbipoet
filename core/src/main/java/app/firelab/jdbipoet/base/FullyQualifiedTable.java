package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.AbstractTable;
import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.DatabaseName;
import app.firelab.jdbipoet.FullyQualifiedTableReference;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableHandle;
import app.firelab.jdbipoet.TableReference;

public abstract class FullyQualifiedTable<H extends TableHandle<?>> extends AbstractTable<H> implements FullyQualifiedTableReference, BaseExpression {
  private final FullyQualifiedTableReference reference;

  public FullyQualifiedTable(FullyQualifiedTableReference reference) {
    this.reference = reference;
  }

  public <T> Column<T> column(Class<T> type, String name) {
    return super.column(type, Sql.expressions().columnRef(this, name));
  }

  public <T> NullableColumn<T> nullableColumn(Class<T> type, String name) {
    return super.nullableColumn(type, Sql.expressions().columnRef(this, name));
  }

  @Override
  public DatabaseName database() {
    return reference.database();
  }

  @Override
  public SemiQualifiedTableReference semiQualify() {
    return reference.semiQualify();
  }

  @Override
  public SchemaName schema() {
    return reference.schema();
  }

  @Override
  public TableReference unqualify() {
    return reference.unqualify();
  }

  @Override
  public SqlPart write(SqlContext context) {
    return reference.write(context);
  }
}
