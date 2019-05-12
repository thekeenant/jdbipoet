package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Identifier;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;

class BaseIdentifier implements Identifier, BaseExpression {
  private final String name;

  BaseIdentifier(String name) {
    this.name = name;
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
//        .append('"')
        .append(name)
//        .append('"')
        .build();
  }

  @Override
  public String asString() {
    return name;
  }
}
