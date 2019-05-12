package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableReference;

class BaseQualifiedColumnReference implements QualifiedColumnReference, BaseExpression {

  private final TableReference table;
  private final BaseColumnReference unqualified;

  BaseQualifiedColumnReference(TableReference table, FieldName name) {
    this.table = table;
    this.unqualified = new BaseColumnReference(name);
  }

  @Override
  public TableReference table() {
    return table;
  }

  @Override
  public ColumnReference unqualify() {
    return unqualified;
  }

  @Override
  public FieldName name() {
    return unqualified.name();
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(table.name())
        .append('.')
        .append(unqualified)
        .build();
  }
}
