package app.firelab.jdbipoet;

import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckReturnValue;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;


@CheckReturnValue
public interface ExpressionFactory {
  /**
   * Wraps an expression with parentheses.
   * @param expr the expression to wrap
   * @return the new parenthesized expression
   */
  default LazyExpression<?> paren(LazyExpression<?> expr) {
    return expr.paren();
  }

  /**
   * @return NULL
   */
  LazyExpression<?> nullValue();

  /**
   * @return EXCLUDED
   */
  LazyExpression<?> excluded();

  /**
   * @return "*", wildcard operator expression
   */
  LazyExpression<?> wildcard();

  /**
   * Creates a comma separated list of expressions.
   * @param items the expressions to comma separate
   * @param <T> the type of expression
   * @return the new list expression
   */
  <T extends LazyExpression<?>> ListExpression<T> list(List<T> items);

  /**
   * {@link this#list(List)}.
   */
  default ListExpression<LazyExpression<?>> list(LazyExpression<?>... items) {
    return list(Arrays.asList(items));
  }

  /**
   * Creates a field name from a string.
   * @param name the field name
   * @return the field name expression
   */
  FieldName fieldName(String name);

  /**
   * Creates a table name from a string.
   * @param name the table name
   * @return the table name expression
   */
  TableName tableName(String name);

  /**
   * Creates a schema name from a string.
   * @param name the schema name
   * @return the schema name expression
   */
  SchemaName schemaName(String name);

  /**
   * Creates a database name from a string.
   * @param name the database name
   * @return the database name expression
   */
  DatabaseName databaseName(String name);

  /**
   * Creates a table reference from its name.
   * @param name the table name
   * @return the table reference expression
   */
  TableReference tableRef(TableName name);

  /**
   * Creates a table reference from its name.
   * @param name the table name
   * @return the table reference expression
   */
  default TableReference tableRef(String name) {
    return tableRef(tableName(name));
  }

  /**
   * Creates a semi qualified table reference from its name.
   * @param schema the schema name
   * @param name the table name
   * @return the table reference expression
   */
  SemiQualifiedTableReference tableRef(SchemaName schema, TableName name);

  /**
   * Creates a semi qualified table reference from its name.
   * @param schema the schema name
   * @param name the table name
   * @return the table reference expression
   */
  default SemiQualifiedTableReference tableRef(String schema, String name) {
    return tableRef(schemaName(schema), tableName(name));
  }

  /**
   * Creates a fully qualified table reference from its name.
   * @param database the database name
   * @param schema the schema name
   * @param name the table name
   * @return the table reference expression
   */
  FullyQualifiedTableReference tableRef(DatabaseName database, SchemaName schema, TableName name);

  /**
   * Creates a fully qualified table reference from its name.
   * @param database the database name
   * @param schema the schema name
   * @param name the table name
   * @return the table reference expression
   */
  default FullyQualifiedTableReference tableRef(String database, String schema, String name) {
    return tableRef(databaseName(database), schemaName(schema), tableName(name));
  }

  /**
   * Creates a column reference from its name.
   * @param name the column name
   * @return the column reference expression
   */
  ColumnReference columnRef(FieldName name);

  /**
   * Creates a column reference from its name.
   * @param name the column name
   * @return the column reference expression
   */
  default ColumnReference columnRef(String name) {
    return columnRef(fieldName(name));
  }

  /**
   * Creates a qualified column reference from its name.
   * @param table the table reference
   * @param name the column name
   * @return the column reference expression
   */
  QualifiedColumnReference columnRef(TableReference table, FieldName name);

  /**
   * Creates a qualified column reference from its name.
   * @param table the table reference
   * @param name the column name
   * @return the column reference expression
   */
  default QualifiedColumnReference columnRef(TableReference table, String name) {
    return columnRef(table, fieldName(name));
  }
}
