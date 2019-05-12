package app.firelab.jdbipoet;

import java.util.List;

public interface ListExpression<T extends LazyExpression<?>> extends Expression {
  List<T> items();
}
