package app.firelab.jdbipoet.example;

import app.firelab.jdbipoet.SelectStatement;
import app.firelab.jdbipoet.base.ExpressionFactoryMixin;
import app.firelab.jdbipoet.base.LazyKey;
import app.firelab.jdbipoet.base.Param;
import app.firelab.jdbipoet.base.Raw;
import app.firelab.jdbipoet.base.StatementFactoryMixin;
import java.util.UUID;

public class Testing implements Runnable, StatementFactoryMixin, ExpressionFactoryMixin {

  private final LazyKey<Param<Integer>> idKey = LazyKey.create();

  private final SelectStatement findById = select(wildcard())
      .from(Raw.of("users"))
      .where(Raw.of("id").equalTo(idKey))
      .build();

  public static void main(String[] args) {
    new Testing().run();
  }

  public void run() {
    insertInto(Raw.of("table"))
        .values(row -> row.set(fieldName("id"), Param.of(UUID.randomUUID())))
        .build();
  }
}
