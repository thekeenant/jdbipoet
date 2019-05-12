package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface TableReference extends Expression {
  TableName name();

  @CheckReturnValue
  SemiQualifiedTableReference qualifyWith(SchemaName schema);
}
