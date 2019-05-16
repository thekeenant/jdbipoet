package app.firelab.jdbipoet;

import org.jdbi.v3.core.mapper.RowMapper;

public interface TableHandle<T extends Table<?>> extends HandleWrapper, RowMapper<TableRow<T>> {
  T table();
}
