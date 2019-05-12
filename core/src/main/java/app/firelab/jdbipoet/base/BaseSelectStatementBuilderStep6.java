package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SelectStatementBuilderStep6;
import app.firelab.jdbipoet.SelectStatementBuilderStep7;
import app.firelab.jdbipoet.SelectStatementBuilderStep8;
import app.firelab.jdbipoet.base.BaseSelectStatement.Builder;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import javax.annotation.Nullable;

class BaseSelectStatementBuilderStep6 implements SelectStatementBuilderStep6, BaseExpression {
  private final Builder builder;

  BaseSelectStatementBuilderStep6(
      Builder builder,
      @Nullable LazyExpression<?> orderBy
  ) {
    this.builder = builder.copy().withOrderBy(orderBy);
  }

  @Override
  public SelectStatement build() {
    return builder.build();
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
