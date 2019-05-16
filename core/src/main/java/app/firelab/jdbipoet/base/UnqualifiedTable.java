package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableHandle;
import app.firelab.jdbipoet.TableName;
import app.firelab.jdbipoet.TableReference;

public abstract class UnqualifiedTable<H extends TableHandle<?>> extends AbstractTable<H> implements TableReference, BaseExpression {
  private final TableName name;

  public UnqualifiedTable(TableName name) {
    this.name = name;
  }

  public UnqualifiedTable(String name) {
    this(Sql.expressions().tableName(name));
  }

  public <T> Column<T> registerColumn(Class<T> type, String name) {
    return super.registerColumn(type, Sql.expressions().columnRef(this, name));
  }

  public <T> NullableColumn<T> registerNullableColumn(Class<T> type, String name) {
    return super.registerNullableColumn(type, Sql.expressions().columnRef(this, name));
  }

  @Override
  public TableName name() {
    return name;
  }

  @Override
  public SemiQualifiedTableReference qualifyWith(SchemaName schema) {
    return name.qualifyWith(schema);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return name.write(context);
  }
}
