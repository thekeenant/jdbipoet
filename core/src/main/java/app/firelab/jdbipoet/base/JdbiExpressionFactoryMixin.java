package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.JdbiExpressionFactory;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;

public interface JdbiExpressionFactoryMixin extends JdbiExpressionFactory {
  @Override
  default <T> Column<T> column(Class<T> type, QualifiedColumnReference reference) {
    return JdbiSql.expressions().column(type, reference);
  }

  @Override
  default <T> NullableColumn<T> nullableColumn(Class<T> type, QualifiedColumnReference reference) {
    return JdbiSql.expressions().nullableColumn(type, reference);
  }
}
