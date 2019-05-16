package app.firelab.jdbipoet;

import java.util.Optional;

public interface NullableColumn<T> extends Column<Optional<T>> {
  @SuppressWarnings("unchecked")
  @Override
  default Class<Optional<T>> type() {
    Optional<T> type = Optional.empty();
    return (Class<Optional<T>>) type.getClass();
  }

  Class<T> nullableType();

  @Override
  default Class<?> databaseType() {
    return nullableType();
  }
}
