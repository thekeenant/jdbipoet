package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface SelectStatementBuilderStep2 extends SelectStatementBuilderStep3 {
  @CheckReturnValue
  SelectStatementBuilderStep3 where(LazyExpression<?> filter);
}
