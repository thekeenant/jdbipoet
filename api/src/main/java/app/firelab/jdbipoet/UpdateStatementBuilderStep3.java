package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface UpdateStatementBuilderStep3 extends UpdateStatementBuilderStep4 {
  @CheckReturnValue
  UpdateStatementBuilderStep4 returning();

  @CheckReturnValue
  UpdateStatementBuilderStep4 returning(LazyExpression<?> fields);
}
