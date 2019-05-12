package app.firelab.jdbipoet;

import java.util.List;

/**
 * A part of a parameterized SQL statement that may also provide bindings.
 */
public interface SqlPart {
  String sql();

  List<Object> bindings();

  SqlStatement toStatement(SqlContext context);
}
