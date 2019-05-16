package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlStatement;
import app.firelab.jdbipoet.Statement;

class RawStatement implements Statement, BaseExpression {
  private final Raw raw;

  RawStatement(Raw raw) {
    this.raw = raw;
  }

  @Override
  public SqlStatement write(SqlContext context) {
    return raw.writeStatement(context);
  }
}
