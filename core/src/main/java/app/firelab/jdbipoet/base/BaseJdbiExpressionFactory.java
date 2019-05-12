package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.JdbiExpressionFactory;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;

class BaseJdbiExpressionFactory implements JdbiExpressionFactory {
  @Override
  public <T> Column<T> column(Class<T> type, QualifiedColumnReference reference) {
    return new BaseColumn<>(type, reference);
  }

  @Override
  public <T> NullableColumn<T> nullableColumn(Class<T> type, QualifiedColumnReference reference) {
    return new BaseNullableColumn<>(type, reference);
  }
}
