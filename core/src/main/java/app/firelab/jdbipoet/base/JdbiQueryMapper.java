package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlStatement;
import java.util.function.Function;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;

public class JdbiQueryMapper implements Function<SqlStatement, Query> {
  private final Handle handle;

  public JdbiQueryMapper(Handle handle) {
    this.handle = handle;
  }

  public Query apply(SqlStatement statement) {
    Query query = handle.createQuery(statement.sql());
    for (int i = 0; i < statement.bindings().size(); i++) {
      query.bind(i, statement.bindings().get(i));
    }
    return query;
  }
}
