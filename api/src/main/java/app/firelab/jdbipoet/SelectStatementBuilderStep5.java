package app.firelab.jdbipoet;

public interface SelectStatementBuilderStep5 extends SelectStatementBuilderStep6 {
  SelectStatementBuilderStep5 union(LazyExpression<?> select);

  SelectStatementBuilderStep5 intersect(LazyExpression<?> select);

  SelectStatementBuilderStep5 except(LazyExpression<?> select);

  SelectStatementBuilderStep6 orderBy(LazyExpression<?> ordering);
}
