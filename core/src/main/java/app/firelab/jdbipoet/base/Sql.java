package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.ExpressionFactory;
import app.firelab.jdbipoet.StatementFactory;

public class Sql {
  private static final StatementFactory STATEMENTS = new BaseStatementFactory();
  private static final ExpressionFactory EXPRESSIONS = new BaseExpressionFactory();

  public static StatementFactory statements() {
    return STATEMENTS;
  }

  public static ExpressionFactory expressions() {
    return EXPRESSIONS;
  }
}
