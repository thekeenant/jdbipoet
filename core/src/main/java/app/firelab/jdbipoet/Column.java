package app.firelab.jdbipoet;

import org.jdbi.v3.core.mapper.ColumnMapper;

public interface Column<T> extends Expression, ColumnMapper<T>, QualifiedColumnReference {
  QualifiedColumnReference qualified();

  Class<T> type();

  /**
   * @return the type of the column when returned from the database
   */
  default Class<?> databaseType() {
    return type();
  }
}
