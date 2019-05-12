package app.firelab.jdbipoet;

public interface SelectStatementBuilderStep3 extends SelectStatementBuilderStep4 {
  SelectStatementBuilderStep4 groupBy(LazyExpression<?> grouping);
}
