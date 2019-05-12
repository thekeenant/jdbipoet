package app.firelab.jdbipoet;

import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface StatementFactory {
  <E extends LazyExpression<?>> InsertStatementBuilderStep1 insertInto(LazyExpression<?> table);

  default SelectStatement select(String raw, Object... bindings) {
    return select(raw, Arrays.asList(bindings));
  }

  SelectStatement select(String raw, List<Object> bindings);

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
