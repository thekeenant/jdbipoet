package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface DeleteStatementBuilderStep2 {
  @CheckReturnValue
  DeleteStatementBuilderStep3 returning();

  @CheckReturnValue
  DeleteStatementBuilderStep3 returning(LazyExpression<?> fields);
}
