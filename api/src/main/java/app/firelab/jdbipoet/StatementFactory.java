package app.firelab.jdbipoet;

import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface StatementFactory {
  InsertStatementBuilderStep1 insertInto(LazyExpression<?> table);

  default Statement statement(String raw, Object... bindings) {
    return statement(raw, Arrays.asList(bindings));
  }

  Statement statement(String raw, List<Object> bindings);

  /**
   * Starts building a select statement, selecting all fields, "*".
   * @return the builder
   */
  SelectStatementBuilderStep1 select();

  /**
   * Starts building a select statement, selecting the expressions provided.
   * @param what the fields/expressions to select
   * @return the builder
   */
  <E extends LazyExpression<?>> SelectStatementBuilderStep1 select(List<E> what);

  default SelectStatementBuilderStep1 select(LazyExpression<?>... what) {
    return select(Arrays.asList(what));
  }

  UpdateStatementBuilderStep1 update(LazyExpression<?> table);

  DeleteStatementBuilderStep1 deleteFrom(LazyExpression<?> table);
}
