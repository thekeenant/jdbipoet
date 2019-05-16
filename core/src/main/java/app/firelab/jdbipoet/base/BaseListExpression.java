package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.ListExpression;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import java.util.Collections;
import java.util.List;

class BaseListExpression<T extends LazyExpression<?>> implements ListExpression<T>, BaseExpression {

  private final List<T> items;

  BaseListExpression(List<T> items) {
    this.items = Collections.unmodifiableList(items);
  }

  @Override
  public List<T> items() {
    return items;
  }

  @Override
  public SqlPart write(SqlContext context) {
    BaseSqlPart.Builder builder = BaseSqlPart.builder(context);

    for (int i = 0; i < items.size(); i++) {
      T current = items.get(i);
      builder.append(current);

      if (i != items.size() - 1) {
        builder.append(", ");
      }
    }

    return builder.build();
  }
}
