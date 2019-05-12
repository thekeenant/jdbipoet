package app.firelab.jdbipoet;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.StatementContext;

public abstract class AbstractTableHandle<T extends Table<?>> implements TableHandle<T> {
  protected final T table;

  private final Handle handle;
  private final TableRowRowMapper<T> rowMapper;

  public AbstractTableHandle(T table, Handle handle) {
    this.table = table;
    this.handle = handle;

    rowMapper = new TableRowRowMapper<>(table);
  }

  @Override
  public T table() {
    return table;
  }

  @Override
  public Handle handle() {
    return handle;
  }

  @Override
  public TableRow<T> map(ResultSet rs, StatementContext ctx) throws SQLException {
    return rowMapper.map(rs, ctx);
  }
}
