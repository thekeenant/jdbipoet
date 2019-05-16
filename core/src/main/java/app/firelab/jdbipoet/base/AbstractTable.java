package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Column;
import app.firelab.jdbipoet.NullableColumn;
import app.firelab.jdbipoet.QualifiedColumnReference;
import app.firelab.jdbipoet.Table;
import app.firelab.jdbipoet.TableHandle;
import app.firelab.jdbipoet.base.Sql;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable<H extends TableHandle<?>> implements Table<H> {
  private final List<Column<?>> columns = new ArrayList<>();

  @Override
  public List<Column<?>> columns() {
    return columns;
  }

  public <T> Column<T> registerColumn(Class<T> type, QualifiedColumnReference reference) {
    Column<T> column = Sql.expressions().column(type, reference);
    columns.add(column);
    return column;
  }

  public <T> NullableColumn<T> registerNullableColumn(Class<T> type, QualifiedColumnReference reference) {
    NullableColumn<T> column = Sql.expressions().nullableColumn(type, reference);
    columns.add(column);
    return column;
  }
}
