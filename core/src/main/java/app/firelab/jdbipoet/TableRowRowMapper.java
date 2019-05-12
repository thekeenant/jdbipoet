package app.firelab.jdbipoet;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TableRowRowMapper<T extends Table<?>> implements RowMapper<TableRow<T>> {
  private final T table;

  public TableRowRowMapper(T table) {
    this.table = table;
  }

  @Override
  public TableRow<T> map(ResultSet rs, StatementContext ctx) throws SQLException {
    return TableRow.fromResultSet(table, rs, ctx);
  }
}
