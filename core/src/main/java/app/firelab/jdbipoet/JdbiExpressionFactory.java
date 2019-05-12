package app.firelab.jdbipoet;

public interface JdbiExpressionFactory {
  /**
   * Creates a column from a column reference.
   * @param type the type of the column
   * @param reference the column reference
   * @param <T> the type of the column
   * @return the column
   */
  <T> Column<T> column(Class<T> type, QualifiedColumnReference reference);

  /**
   * Creates a nullable column from a column reference.
   * @param type the type of the column
   * @param reference the column reference
   * @param <T> the type of the column
   * @return the column
   */
  <T> NullableColumn<T> nullableColumn(Class<T> type, QualifiedColumnReference reference);
}
