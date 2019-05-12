package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SelectStatementBuilderStep7;
import app.firelab.jdbipoet.SelectStatementBuilderStep8;
import app.firelab.jdbipoet.base.BaseSelectStatement.Builder;
import javax.annotation.Nullable;

class BaseSelectStatementBuilderStep7 implements SelectStatementBuilderStep7, BaseExpression {
  private final Builder builder;

  BaseSelectStatementBuilderStep7(
      Builder builder,
      @Nullable LazyExpression<?> limit
  ) {
    this.builder = builder.copy().withLimit(limit);
  }

  @Override
  public SelectStatement build() {
    return builder.build();
  }

  @Override
  public SelectStatementBuilderStep8 offset(LazyExpression<?> start) {
    return new BaseSelectStatementBuilderStep8(builder, start);
  }
}
