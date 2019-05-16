package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Table;
import app.firelab.jdbipoet.base.BaseTableRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TableRowRowMapper<T extends Table<?>> implements RowMapper<BaseTableRow<T>> {
  private final T table;

  public TableRowRowMapper(T table) {
    this.table = table;
  }

  @Override
  public BaseTableRow<T> map(ResultSet rs, StatementContext ctx) throws SQLException {
    return BaseTableRow.fromResultSet(table, rs, ctx);
  }
}
