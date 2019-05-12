package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface UpdateStatementBuilderStep2 extends UpdateStatementBuilderStep3 {
  @CheckReturnValue
  UpdateStatementBuilderStep2 set(LazyExpression<?> field, LazyExpression<?> value);

  @CheckReturnValue
  UpdateStatementBuilderStep3 where(LazyExpression<?> field, LazyExpression<?> value);
}
