package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlStatement;

public class RawSelectStatement implements SelectStatement, BaseExpression {
  private final Raw raw;

  public RawSelectStatement(Raw raw) {
    this.raw = raw;
  }

  @Override
  public SqlStatement write(SqlContext context) {
    return raw.writeStatement(context);
  }
}
