package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

class OnConflictExpression implements BaseExpression {
  @Nullable
  private final Map<String, LazyExpression<?>> values;
  @Nullable
  private final LazyExpression<?> where;

  private OnConflictExpression(@Nullable Map<String, LazyExpression<?>> values, @Nullable LazyExpression<?> where) {
    this.values = values;
    this.where = where;
  }

  public static OnConflictExpression doNothing() {
    return new OnConflictExpression(null, null);
  }

  public static OnConflictExpression doUpdate(Map<String, LazyExpression<?>> values, @Nullable LazyExpression<?> where) {
    return new OnConflictExpression(values, where);
  }

  @Override
  public SqlPart write(SqlContext context) {
    BaseSqlPart.Builder builder = BaseSqlPart.builder(context)
        .append("ON CONFLICT DO ");

    if (values == null) {
      builder.append("NOTHING");
    }
    else {
      builder.append("UPDATE SET ");

      List<Entry<String, LazyExpression<?>>> fieldsToSet = new ArrayList<>(values.entrySet());
      for (int i = 0; i < fieldsToSet.size(); i++) {
        Entry<String, LazyExpression<?>> field = fieldsToSet.get(i);

        builder.append(field.getKey())
            .append('=')
            .append(field.getValue());

        if (i != fieldsToSet.size() - 1) {
          builder.append(',');
        }
      }

      if (where != null) {
        builder.append(" WHERE ")
            .append(where);
      }
    }

    return builder.build();
  }
}
