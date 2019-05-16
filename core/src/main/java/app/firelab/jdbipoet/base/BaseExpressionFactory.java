package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.ColumnReference;
import app.firelab.jdbipoet.DatabaseName;
import app.firelab.jdbipoet.ExpressionFactory;
import app.firelab.jdbipoet.FieldName;
import app.firelab.jdbipoet.FullyQualifiedTableReference;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.ListExpression;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.TableName;
import app.firelab.jdbipoet.TableReference;
import java.util.List;

class BaseExpressionFactory implements ExpressionFactory {
  private static final LazyExpression<?> NULL = Raw.of("NULL");
  private static final LazyExpression<?> EXCLUDED = Raw.of("EXCLUDED");
  private static final LazyExpression<?> WILDCARD = Raw.of("*");

  @Override
  public <T> Column<T> column(Class<T> type, QualifiedColumnReference reference) {
    return new BaseColumn<>(type, reference);
  }

  @Override
  public <T> NullableColumn<T> nullableColumn(Class<T> type, QualifiedColumnReference reference) {
    return new BaseNullableColumn<>(type, reference);
  }

  @Override
  public LazyExpression<?> nullValue() {
    return NULL;
  }

  @Override
  public LazyExpression<?> excluded() {
    return EXCLUDED;
  }

  @Override
  public LazyExpression<?> wildcard() {
    return WILDCARD;
  }

  @Override
  public <T extends LazyExpression<?>> ListExpression<T> list(List<T> items) {
    return new BaseListExpression<>(items);
  }

  @Override
  public FieldName fieldName(String name) {
    return new BaseFieldName(name);
  }

  @Override
  public TableName tableName(String name) {
    return new BaseTableName(name);
  }

  @Override
  public SchemaName schemaName(String name) {
    return new BaseSchemaName(name);
  }

  @Override
  public DatabaseName databaseName(String name) {
    return new BaseDatabaseName(name);
  }

  @Override
  public TableReference tableRef(TableName name) {
    return new BaseTableReference(name);
  }

  @Override
  public SemiQualifiedTableReference tableRef(SchemaName schema, TableName name) {
    return new BaseSemiQualifiedTableReference(schema, name);
  }

  @Override
  public FullyQualifiedTableReference tableRef(DatabaseName database, SchemaName schema, TableName name) {
    return new BaseFullyQualifiedTableReference(database, schema, name);
  }

  @Override
  public ColumnReference columnRef(FieldName name) {
    return new BaseColumnReference(name);
  }

  @Override
  public QualifiedColumnReference columnRef(TableReference table, FieldName name) {
    return new BaseQualifiedColumnReference(table, name);
  }
}
