package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface SelectStatementBuilderStep1 {
  @CheckReturnValue
  SelectStatementBuilderStep2 from(LazyExpression<?> table);
}
