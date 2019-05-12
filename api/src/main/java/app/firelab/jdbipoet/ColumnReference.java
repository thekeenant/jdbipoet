package app.firelab.jdbipoet;

import javax.annotation.CheckReturnValue;

public interface ColumnReference extends Expression {
  FieldName name();

  @CheckReturnValue
  QualifiedColumnReference qualifyWith(TableReference table);
}
