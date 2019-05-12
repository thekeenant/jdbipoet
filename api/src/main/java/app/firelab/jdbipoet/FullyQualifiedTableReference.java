package app.firelab.jdbipoet;

import com.google.errorprone.annotations.DoNotCall;

public interface FullyQualifiedTableReference extends SemiQualifiedTableReference {
  DatabaseName database();

  SemiQualifiedTableReference semiQualify();

  @Deprecated
  @DoNotCall
  @Override
  default FullyQualifiedTableReference qualifyWith(DatabaseName database) {
    throw new UnsupportedOperationException("Cannot qualifyWith fully qualified tableRef with database");
  }
}
