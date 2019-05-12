package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SelectStatementBuilderStep8;
import app.firelab.jdbipoet.base.BaseSelectStatement.Builder;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import javax.annotation.Nullable;

class BaseSelectStatementBuilderStep8 implements SelectStatementBuilderStep8, BaseExpression {
  private final Builder builder;

  BaseSelectStatementBuilderStep8(
      Builder builder,
      @Nullable LazyExpression<?> offset
  ) {
    this.builder = builder.copy().withOffset(offset);
  }

  @Override
  public SelectStatement build() {
    return builder.build();
  }
}
