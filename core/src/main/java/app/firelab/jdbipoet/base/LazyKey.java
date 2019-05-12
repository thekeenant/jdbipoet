package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Expression;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;
import java.util.Objects;
import java.util.UUID;

/**
 * A {@link LazyExpression} which resolves to the {@link Expression} provided to the
 * {@link SqlContext} keyed by this key.
 *
 * @param <E> the expression type to resolve to
 */
public final class LazyKey<E extends Expression> implements BaseLazyExpression<E> {
  private final UUID uuid = UUID.randomUUID();
  private final String declaration;

  private LazyKey(String declaration) {
    this.declaration = declaration;
  }

  public static <E extends Expression> LazyKey<E> create() {
    StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
    String declaration = caller.getClassName() + ":" + caller.getLineNumber();
    return new LazyKey<>(declaration);
  }

  @Override
  public E evaluate(SqlContext context) {
    E expr = context.resolveKey(this).orElse(null);
    if (expr == null) {
      throw new IllegalStateException("Key not provided to context: " + this);
    }
    return expr;
  }

  @Override
  public SqlPart write(SqlContext context) {
    return evaluate(context).write(context);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof LazyKey && ((LazyKey) obj).uuid.equals(uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(LazyKey.class, uuid);
  }

  @Override
  public String toString() {
    return "LazyKey(" + declaration + ")";
  }
}
