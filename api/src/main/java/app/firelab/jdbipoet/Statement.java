package app.firelab.jdbipoet;

import app.firelab.jdbipoet.SqlContext.Builder;
import java.util.function.Consumer;

/**
 * A complete SQL statement.
 */
public interface Statement extends Expression {

  @Override
  default SqlStatement write(Consumer<Builder> builderConsumer) {
    Builder builder = SqlContext.builder();
    builderConsumer.accept(builder);
    return write(builder.build());
  }

  @Override
  SqlStatement write(SqlContext context);

  @Override
  default SqlStatement write() {
    return write(SqlContext.empty());
  }
}
