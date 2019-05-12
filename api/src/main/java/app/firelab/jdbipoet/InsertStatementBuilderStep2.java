package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface InsertStatementBuilderStep2 extends InsertStatementBuilderStep3 {
  @CheckReturnValue
  InsertStatementBuilderStep3 returning();

  @CheckReturnValue
  InsertStatementBuilderStep3 returning(LazyExpression<?> fields);
}
