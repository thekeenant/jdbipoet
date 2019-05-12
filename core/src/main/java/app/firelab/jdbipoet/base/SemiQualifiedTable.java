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

public abstract class SemiQualifiedTable<H extends TableHandle<?>> extends AbstractTable<H> implements SemiQualifiedTableReference, BaseExpression {
  private final SemiQualifiedTableReference reference;

  public SemiQualifiedTable(SemiQualifiedTableReference reference) {
    this.reference = reference;
  }

  public <T> Column<T> column(Class<T> type, String name) {
    return super.column(type, Sql.expressions().columnRef(this, name));
  }

  public <T> NullableColumn<T> nullableColumn(Class<T> type, String name) {
    return super.nullableColumn(type, Sql.expressions().columnRef(this, name));
  }

  @Override
  public SqlPart write(SqlContext context) {
    return reference.write(context);
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
  public FullyQualifiedTableReference qualifyWith(DatabaseName database) {
    return reference.qualifyWith(database);
  }
}
