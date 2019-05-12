package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SemiQualifiedTableReference;
import app.firelab.jdbipoet.TableReference;
import app.firelab.jdbipoet.DatabaseName;
import app.firelab.jdbipoet.FullyQualifiedTableReference;
import app.firelab.jdbipoet.SchemaName;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import app.firelab.jdbipoet.TableName;

class BaseFullyQualifiedTableReference implements FullyQualifiedTableReference, BaseExpression {

  private final DatabaseName database;
  private final SemiQualifiedTableReference semiQualified;

  BaseFullyQualifiedTableReference(DatabaseName database, SchemaName schema, TableName table) {
    this.database = database;
    this.semiQualified = new BaseSemiQualifiedTableReference(schema, table);
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(database)
        .append('.')
        .append(semiQualified)
        .build();
  }

  @Override
  public DatabaseName database() {
    return database;
  }

  @Override
  public SemiQualifiedTableReference semiQualify() {
    return semiQualified;
  }

  @Override
  public SchemaName schema() {
    return semiQualified.schema();
  }

  @Override
  public TableReference unqualify() {
    return semiQualified.unqualify();
  }
}
