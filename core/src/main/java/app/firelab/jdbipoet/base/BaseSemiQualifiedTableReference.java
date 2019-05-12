package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.DatabaseName;
import app.firelab.jdbipoet.FullyQualifiedTableReference;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableName;
import app.firelab.jdbipoet.TableReference;

class BaseSemiQualifiedTableReference implements SemiQualifiedTableReference, BaseExpression {

  private final SchemaName schema;
  private final TableReference unqualified;

  BaseSemiQualifiedTableReference(SchemaName schema, TableName name) {
    this.schema = schema;
    this.unqualified = new BaseTableReference(name);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(schema)
        .append('.')
        .append(unqualified)
        .build();
  }

  @Override
  public SchemaName schema() {
    return schema;
  }

  @Override
  public TableReference unqualify() {
    return unqualified;
  }

  @Override
  public FullyQualifiedTableReference qualifyWith(DatabaseName database) {
    return new BaseFullyQualifiedTableReference(database, schema, name());
  }
}
