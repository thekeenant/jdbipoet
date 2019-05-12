package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.DeleteStatementBuilderStep1;
import app.firelab.jdbipoet.InsertStatementBuilderStep1;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SelectStatementBuilderStep1;
import app.firelab.jdbipoet.StatementFactory;
import app.firelab.jdbipoet.UpdateStatementBuilderStep1;
import java.util.List;

class BaseStatementFactory implements StatementFactory, ExpressionFactoryMixin {
  @Override
  public <E extends LazyExpression<?>> InsertStatementBuilderStep1 insertInto(LazyExpression<?> table) {
    return null;
  }

  @Override
  public SelectStatement select(String sql, List<Object> bindings) {
    return new RawSelectStatement(Raw.of(sql, bindings));
  }

  @Override
  public SelectStatementBuilderStep1 select() {
    return select(wildcard());
  }

  @Override
  public <E extends LazyExpression<?>> SelectStatementBuilderStep1 select(List<E> what) {
    return new BaseSelectStatementBuilderStep1(list(what));
  }

  @Override
  public UpdateStatementBuilderStep1 update(LazyExpression<?> table) {
    return null;
  }

  @Override
  public DeleteStatementBuilderStep1 deleteFrom(LazyExpression<?> table) {
    return null;
  }
}
