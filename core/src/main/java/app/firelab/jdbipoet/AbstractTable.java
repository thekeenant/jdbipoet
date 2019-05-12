package app.firelab.jdbipoet;

import app.firelab.jdbipoet.base.JdbiSql;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable<H extends TableHandle<?>> implements Table<H>, JdbiExpressionFactory {
  private final JdbiExpressionFactory expressions = JdbiSql.expressions();
  private final List<Column<?>> columns = new ArrayList<>();

  @Override
  public List<Column<?>> columns() {
    return columns;
  }

  @Override
  public <T> Column<T> column(Class<T> type, QualifiedColumnReference reference) {
    Column<T> column = expressions.column(type, reference);
    columns.add(column);
    return column;
  }

  @Override
  public <T> NullableColumn<T> nullableColumn(Class<T> type, QualifiedColumnReference reference) {
    NullableColumn<T> column = expressions.nullableColumn(type, reference);
    columns.add(column);
    return column;
  }
}
