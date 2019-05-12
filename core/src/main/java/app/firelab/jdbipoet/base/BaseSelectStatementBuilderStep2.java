package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SelectStatementBuilderStep2;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SelectStatementBuilderStep3;
import app.firelab.jdbipoet.SelectStatementBuilderStep4;
import app.firelab.jdbipoet.SelectStatementBuilderStep5;
import app.firelab.jdbipoet.SelectStatementBuilderStep6;
import app.firelab.jdbipoet.SelectStatementBuilderStep7;
import app.firelab.jdbipoet.SelectStatementBuilderStep8;
import app.firelab.jdbipoet.base.BaseSelectStatement.Builder;

class BaseSelectStatementBuilderStep2 implements SelectStatementBuilderStep2, BaseExpression {

  private final Builder builder;

  BaseSelectStatementBuilderStep2(LazyExpression<?> what, LazyExpression<?> from) {
    this.builder = BaseSelectStatement.builder(what, from);

  }
  @Override
  public SelectStatement build() {
    return builder.build();
  }

  @Override
  public SelectStatementBuilderStep3 where(LazyExpression<?> whereFilter) {
    return new BaseSelectStatementBuilderStep3(builder, whereFilter);
  }

  @Override
  public SelectStatementBuilderStep4 groupBy(LazyExpression<?> grouping) {
    return new BaseSelectStatementBuilderStep4(builder, grouping);
  }

  @Override
  public SelectStatementBuilderStep5 having(LazyExpression<?> condition) {
    return new BaseSelectStatementBuilderStep5(builder, condition);
  }

  @Override
  public SelectStatementBuilderStep5 union(LazyExpression<?> select) {
    return new BaseSelectStatementBuilderStep5(builder, null, select, null, null);
  }

  @Override
  public SelectStatementBuilderStep5 intersect(LazyExpression<?> select) {
    return new BaseSelectStatementBuilderStep5(builder, null, null, select, null);
  }

  @Override
  public SelectStatementBuilderStep5 except(LazyExpression<?> select) {
    return new BaseSelectStatementBuilderStep5(builder, null, null, null, select);
  }

  @Override
  public SelectStatementBuilderStep6 orderBy(LazyExpression<?> ordering) {
    return new BaseSelectStatementBuilderStep6(builder, ordering);
  }

  @Override
  public SelectStatementBuilderStep7 limit(LazyExpression<?> count) {
    return new BaseSelectStatementBuilderStep7(builder, count);
  }

  @Override
  public SelectStatementBuilderStep8 offset(LazyExpression<?> start) {
    return new BaseSelectStatementBuilderStep8(builder, start);
  }
}
