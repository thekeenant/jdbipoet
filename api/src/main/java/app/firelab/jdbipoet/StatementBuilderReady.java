package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface StatementBuilderReady<T extends Statement> extends Statement {
  @CheckReturnValue
  T build();

  @Override
  default SqlStatement write(SqlContext context) {
    return build().write(context);
  }
}
