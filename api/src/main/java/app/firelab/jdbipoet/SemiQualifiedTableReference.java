package app.firelab.jdbipoet;

import com.google.errorprone.annotations.DoNotCall;
import javax.annotation.CheckReturnValue;

public interface SemiQualifiedTableReference extends TableReference {
  SchemaName schema();

  TableReference unqualify();

  @Override
  default TableName name() {
    return unqualify().name();
  }

  @CheckReturnValue
  FullyQualifiedTableReference qualifyWith(DatabaseName database);

  @Deprecated
  @DoNotCall
  @Override
  default SemiQualifiedTableReference qualifyWith(SchemaName schema) {
    throw new UnsupportedOperationException("Cannot qualifyWith semi qualified tableRef with schema");
  }
}
