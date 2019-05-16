package app.firelab.jdbipoet;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface TableRow<T extends Table<?>> {
  T table();

  <V> V get(Column<V> column);

  default <C extends Column<V>, V> V get(Function<T, C> columnProvider) {
    return get(columnProvider.apply(table()));
  }

  Map<Column<?>, ?> toMap();

  default Map<String, ?> toStringMap() {
    Map<Column<?>, ?> map = toMap();
    return map.keySet().stream()
        .collect(Collectors.toMap(
            column -> column.name().asString(),
            map::get,
            (u, v) -> {
              throw new IllegalStateException();
            },
            LinkedHashMap::new
        ));
  }
}
