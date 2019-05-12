package app.firelab.jdbipoet;

import java.util.function.Consumer;
import javax.annotation.CheckReturnValue;

/**
 * Something that that can be lazily evaluated to an expression at write-time.
 * @param <E> the type of expression to lazily evaluate to
 */
public interface LazyExpression<E extends Expression> {
  E evaluate(SqlContext context);

  default SqlPart write(Consumer<SqlContext.Builder> builderConsumer) {
    SqlContext.Builder builder = SqlContext.builder();
    builderConsumer.accept(builder);
    return write(builder.build());
  }

  default SqlPart write(SqlContext context) {
    return evaluate(context).write(context);
  }

  default SqlPart write() {
    return write(SqlContext.empty());
  }

  default SqlStatement writeStatement(SqlContext context) {
    return evaluate(context).write(context).toStatement(context);
  }

  default SqlStatement writeStatement() {
    return writeStatement(SqlContext.empty());
  }

  LazyExpression<?> dot(LazyExpression<?> nested);

  @CheckReturnValue
  LazyExpression<?> paren();

  @CheckReturnValue
  LazyExpression<?> asc();

  @CheckReturnValue
  LazyExpression<?> desc();

  @CheckReturnValue
  LazyExpression<?> lessThan(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> lessThanOrEqualTo(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> equalTo(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> notEqualTo(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> greaterThan(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> greaterThanOrEqualTo(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> is(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> like(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> notLike(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> in(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> between(LazyExpression<?> min, LazyExpression<?> max);

  @CheckReturnValue
  LazyExpression<?> and(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> or(LazyExpression<?> other);

  @CheckReturnValue
  LazyExpression<?> as(LazyExpression<?> other);
}