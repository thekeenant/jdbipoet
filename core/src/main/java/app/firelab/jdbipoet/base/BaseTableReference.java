package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.TableReference;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableName;

class BaseTableReference implements TableReference, BaseExpression {

  private final TableName table;

  BaseTableReference(TableName table) {
    this.table = table;
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(table)
        .build();
  }

  @Override
  public TableName name() {
    return table;
  }

  @Override
  public SemiQualifiedTableReference qualifyWith(SchemaName schema) {
    return new BaseSemiQualifiedTableReference(schema, table);
  }
}
