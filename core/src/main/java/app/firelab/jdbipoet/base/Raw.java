package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlPart;
import java.util.Collections;
import java.util.List;

public interface Raw extends BaseExpression {
  static Raw of(String sql) {
    return of(sql, Collections.emptyList());
  }

  static Raw of(String sql, List<Object> bindings) {
    SqlPart built = BaseSqlPart.builder().append(sql, bindings).build();
    return context -> built;
  }
}
