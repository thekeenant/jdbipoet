package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatementBuilderStep1;
import app.firelab.jdbipoet.SelectStatementBuilderStep2;

class BaseSelectStatementBuilderStep1 implements SelectStatementBuilderStep1 {
  private final LazyExpression<?> what;

  BaseSelectStatementBuilderStep1(LazyExpression<?> what) {
    this.what = what;
  }

  @Override
  public SelectStatementBuilderStep2 from(LazyExpression<?> table) {
    return new BaseSelectStatementBuilderStep2(what, table);
  }
}
