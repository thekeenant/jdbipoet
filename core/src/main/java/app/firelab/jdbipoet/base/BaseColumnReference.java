package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableReference;

class BaseColumnReference implements ColumnReference, BaseExpression {

  private final FieldName name;

  BaseColumnReference(FieldName name) {
    this.name = name;
  }

  @Override
  public FieldName name() {
    return name;
  }

  @Override
  public QualifiedColumnReference qualifyWith(TableReference table) {
    return new BaseQualifiedColumnReference(table, name);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(name)
        .build();
  }
}
