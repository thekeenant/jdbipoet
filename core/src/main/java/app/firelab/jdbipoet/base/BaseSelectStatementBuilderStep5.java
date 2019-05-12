package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.SelectStatementBuilderStep5;
import app.firelab.jdbipoet.SelectStatementBuilderStep6;
import app.firelab.jdbipoet.SelectStatementBuilderStep7;
import app.firelab.jdbipoet.SelectStatementBuilderStep8;
import app.firelab.jdbipoet.base.BaseSelectStatement.Builder;
import javax.annotation.Nullable;

class BaseSelectStatementBuilderStep5 implements SelectStatementBuilderStep5, BaseExpression {
  private final Builder builder;

  BaseSelectStatementBuilderStep5(
      Builder builder,
      @Nullable LazyExpression<?> having,
      @Nullable LazyExpression<?> union,
      @Nullable LazyExpression<?> intersect,
      @Nullable LazyExpression<?> except
  ) {
    this.builder = builder.copy();
    if (having != null) {
      this.builder.withHaving(having);
    }
    if (union != null) {
      this.builder.withUnion(union);
    }
    if (intersect != null) {
      this.builder.withIntersect(intersect);
    }
    if (except != null) {
      this.builder.withExclude(except);
    }
  }

  BaseSelectStatementBuilderStep5(Builder builder, LazyExpression<?> having) {
    this(builder, having, null, null, null);
  }

  @Override
  public SelectStatement build() {
    return builder.build();
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
