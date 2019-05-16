package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import java.util.Objects;

public interface Param<T> extends BaseExpression {
  public static <T> Param<T> of(T param) {
    return new Param<T>() {
      @Override
      public T param() {
        return param;
      }

      @Override
      public SqlPart write(SqlContext context) {
        return BaseSqlPart.builder(context)
            .append("?", param)
            .build();
      }

      @Override
      public boolean equals(Object obj) {
        return obj instanceof Param && ((Param) obj).param().equals(param);
      }

      @Override
      public int hashCode() {
        return Objects.hash(Param.class, param);
      }
    };
  }

  T param();
}