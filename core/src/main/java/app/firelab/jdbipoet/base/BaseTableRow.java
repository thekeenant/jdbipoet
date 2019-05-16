package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.Table;
import app.firelab.jdbipoet.TableRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jdbi.v3.core.statement.StatementContext;

public class BaseTableRow<T extends Table<?>> implements TableRow<T> {
  private final T table;
  private final Map<Column<?>, ?> values;

  private BaseTableRow(T table, Map<Column<?>, ?> values) {
    this.table = table;
    this.values = values;
  }

  @Override
  public T table() {
    return table;
  }

  @Override
  public Map<Column<?>, ?> toMap() {
    return new HashMap<>(values);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <V> V get(Column<V> column) {
    if (!values.containsKey(column)) {
      throw new IllegalArgumentException("Column not found in row: " + values);
    }
    return (V) values.get(column);
  }

  static <T extends Table<?>> BaseTableRow<T> fromResultSet(T table, ResultSet resultSet, StatementContext ctx) throws SQLException {
    Map<Column<?>, Object> values = new LinkedHashMap<>();
    Map<String, Column<?>> columnsByName = table.columns().stream()
        .collect(Collectors.toMap(
           column -> column.name().asString(),
           column -> column
        ));

    // Iterate all returned fields in result set
    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
      String name = resultSet.getMetaData().getColumnName(i);
      Column<?> resolvedColumn = columnsByName.get(name);

      if (resolvedColumn == null) {
        throw new IllegalArgumentException("No matching column for ResultSet field: " + name);
      }

      Object value = resolvedColumn.map(resultSet, i, ctx);
      values.put(resolvedColumn, value);
    }

    // Ensure all columns are returned in ResultSet
    Set<Column<?>> missingColumns = new HashSet<>(table.columns());
    missingColumns.removeAll(values.keySet());
    if (!missingColumns.isEmpty()) {
      throw new IllegalArgumentException("Columns not returned in ResultSet: " + missingColumns);
    }

    return new BaseTableRow<>(table, values);
  }
}
