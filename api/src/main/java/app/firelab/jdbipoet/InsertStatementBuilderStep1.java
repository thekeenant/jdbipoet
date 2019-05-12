package app.firelab.jdbipoet;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public interface InsertStatementBuilderStep1 extends InsertStatementBuilderStep2 {
  LazyExpression<?> excluded();

  @CheckReturnValue
  InsertStatementBuilderStep1 defaultValues();

  @CheckReturnValue
  InsertStatementBuilderStep1 values(Map<String, LazyExpression<?>> values);

  @CheckReturnValue
  default <T> InsertStatementBuilderStep1 values(Collection<T> items, BiFunction<T, SettableRow, SettableRow> rowModifier) {
    InsertStatementBuilderStep1 result = this;
    for (T item : items) {
      result = values(row -> rowModifier.apply(item, row));
    }
    return result;
  }

  @CheckReturnValue
  default InsertStatementBuilderStep1 values(RowModifier rowModifier) {
    SettableRow row = SettableRow.create();
    row = rowModifier.apply(row);
    return values(row.values());
  }

  @FunctionalInterface
  interface RowModifier extends Function<SettableRow, SettableRow> {
    @Override
    SettableRow apply(SettableRow row);
  }

  @FunctionalInterface
  interface ConflictUpdate extends BiFunction<SettableRow, LazyExpression<?>, SettableRow> {
    @Override
    SettableRow apply(SettableRow row, LazyExpression<?> excluded);
  }

  interface SettableRow {
    SettableRow set(String column, LazyExpression<?> value);

    @CheckReturnValue
    SettableRow copy();

    Map<String, LazyExpression<?>> values();

    default SettableRow set(FieldName column, LazyExpression<?> value) {
      return set(column.asString(), value);
    }

    default SettableRow set(ColumnReference column, LazyExpression<?> value) {
      return set(column.name(), value);
    }

    static SettableRow create() {
      return create(new LinkedHashMap<>());
    }

    static SettableRow create(LinkedHashMap<String, LazyExpression<?>> values) {
      return new SettableRow() {
        @Override
        public SettableRow set(String column, LazyExpression<?> value) {
          values.put(column, value);
          return this;
        }

        @Override
        public SettableRow copy() {
          return create(new LinkedHashMap<>(values));
        }

        @Override
        public Map<String, LazyExpression<?>> values() {
          return values;
        }
      };
    }
  }

  @CheckReturnValue
  InsertStatementBuilderStep2 onConflictDoNothing();

  @CheckReturnValue
  InsertStatementBuilderStep2 onConflictDoUpdateSet(Map<String, LazyExpression<?>> values);

  @CheckReturnValue
  InsertStatementBuilderStep2 onConflictDoUpdateSet(Map<String, LazyExpression<?>> values, LazyExpression<?> where);

  @CheckReturnValue
  default InsertStatementBuilderStep2 onConflictDoUpdateSet(RowModifier conflictUpdate) {
    return onConflictDoUpdateSet(conflictUpdate, null);
  }

  @CheckReturnValue
  default InsertStatementBuilderStep2 onConflictDoUpdateSet(RowModifier conflictUpdate, @Nullable LazyExpression<?> where) {
    return onConflictDoUpdateSet((row, excluded) -> conflictUpdate.apply(row), where);
  }

  @CheckReturnValue
  default InsertStatementBuilderStep2 onConflictDoUpdateSet(ConflictUpdate conflictUpdate) {
    return onConflictDoUpdateSet(conflictUpdate, null);
  }

  @CheckReturnValue
  default InsertStatementBuilderStep2 onConflictDoUpdateSet(ConflictUpdate conflictUpdate, @Nullable LazyExpression<?> where) {
    SettableRow row = SettableRow.create();
    conflictUpdate.apply(row, excluded());
    if (where == null) {
      return onConflictDoUpdateSet(row.values());
    } else {
      return onConflictDoUpdateSet(row.values(), where);
    }
  }
}
