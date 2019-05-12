package app.firelab.jdbipoet;

/**
 * Some expression that can be represented in SQL.
 */
public interface Expression extends LazyExpression<Expression> {
  @Override
  default Expression evaluate(SqlContext context) {
    return this;
  }

  /**
   * Writes this expression to SQL.
   *
   * @param context the context of the SQL query
   * @return the generated SQL statement or part of a statement
   */
  @Override
  SqlPart write(SqlContext context);
}
