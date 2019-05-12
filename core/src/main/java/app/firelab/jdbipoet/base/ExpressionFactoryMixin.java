package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.DatabaseName;
import app.firelab.jdbipoet.ExpressionFactory;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.FullyQualifiedTableReference;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.ListExpression;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.TableName;
import app.firelab.jdbipoet.TableReference;
import java.util.List;

public interface ExpressionFactoryMixin extends ExpressionFactory {
  @Override
  default LazyExpression<?> nullValue() {
    return Sql.expressions().nullValue();
  }

  @Override
  default LazyExpression<?> excluded() {
    return Sql.expressions().excluded();
  }

  @Override
  default LazyExpression<?> wildcard() {
    return Sql.expressions().wildcard();
  }

  @Override
  default <T extends LazyExpression<?>> ListExpression<T> list(List<T> items) {
    return Sql.expressions().list(items);
  }

  @Override
  default FieldName fieldName(String name) {
    return Sql.expressions().fieldName(name);
  }

  @Override
  default TableName tableName(String name) {
    return Sql.expressions().tableName(name);
  }

  @Override
  default SchemaName schemaName(String name) {
    return Sql.expressions().schemaName(name);
  }

  @Override
  default DatabaseName databaseName(String name) {
    return Sql.expressions().databaseName(name);
  }

  @Override
  default TableReference tableRef(TableName name) {
    return Sql.expressions().tableRef(name);
  }

  @Override
  default SemiQualifiedTableReference tableRef(SchemaName schema, TableName name) {
    return Sql.expressions().tableRef(schema, name);
  }

  @Override
  default FullyQualifiedTableReference tableRef(DatabaseName database, SchemaName schema,
      TableName name) {
    return Sql.expressions().tableRef(database, schema, name);
  }

  @Override
  default ColumnReference columnRef(FieldName name) {
    return Sql.expressions().columnRef(name);
  }

  @Override
  default QualifiedColumnReference columnRef(TableReference table, FieldName name) {
    return Sql.expressions().columnRef(table, name);
  }
}
