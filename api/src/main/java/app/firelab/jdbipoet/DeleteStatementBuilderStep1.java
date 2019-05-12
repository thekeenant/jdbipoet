package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface DeleteStatementBuilderStep1 extends DeleteStatementBuilderStep2 {
  @CheckReturnValue
  DeleteStatementBuilderStep2 where(LazyExpression<?> filter);
}
