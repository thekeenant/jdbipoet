package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface UpdateStatementBuilderStep1 {
  @CheckReturnValue
  UpdateStatementBuilderStep2 set(LazyExpression<?> field, LazyExpression<?> value);
}
