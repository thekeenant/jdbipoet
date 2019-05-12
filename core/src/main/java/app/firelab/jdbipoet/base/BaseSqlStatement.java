package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlStatement;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

class BaseSqlStatement extends BaseSqlPart implements SqlStatement {
  private final SqlContext context;

  BaseSqlStatement(SqlContext context, String sql, List<Object> bindings) {
    super(sql, bindings);
    this.context = context;
  }

  @Override
  public SqlContext context() {
    return context;
  }

  @Override
  public Query toQuery(Handle handle) {
    return map(new JdbiQueryMapper(handle));
  }

  @Override
  public Update toUpdate(Handle handle) {
    return map(new JdbiUpdateMapper(handle));
  }
}
