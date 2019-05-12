package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Identifier;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableName;

class BaseTableName implements TableName, BaseExpression {
  private final Identifier identifier;

  BaseTableName(String name) {
    this.identifier = new BaseIdentifier(name);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return identifier.write(context);
  }

  @Override
  public SemiQualifiedTableReference qualifyWith(SchemaName schema) {
    return new BaseSemiQualifiedTableReference(schema, this);
  }

  @Override
  public String asString() {
    return identifier.asString();
  }
}
