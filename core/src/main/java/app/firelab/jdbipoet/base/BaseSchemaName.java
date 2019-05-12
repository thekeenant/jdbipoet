package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Identifier;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;

class BaseSchemaName implements SchemaName, BaseExpression {
  private final Identifier identifier;

  BaseSchemaName(String name) {
    this.identifier = new BaseIdentifier(name);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return identifier.write(context);
  }

  @Override
  public String asString() {
    return identifier.asString();
  }
}
