package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlStatement;
import java.util.function.Function;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

public class JdbiUpdateMapper implements Function<SqlStatement, Update> {
  private final Handle handle;

  public JdbiUpdateMapper(Handle handle) {
    this.handle = handle;
  }

  public Update apply(SqlStatement statement) {
    Update update = handle.createUpdate(statement.sql());
    for (int i = 0; i < statement.bindings().size(); i++) {
      update.bind(i, statement.bindings().get(i));
    }
    return update;
  }
}
