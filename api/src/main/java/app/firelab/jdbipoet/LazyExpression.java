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

  default SqlStatement writeStatement(Consumer<SqlContext.Builder> builderConsumer) {
    SqlContext.Builder builder = SqlContext.builder();
    builderConsumer.accept(builder);
    SqlContext context = builder.build();
    return writeStatement(context);
  }

  default SqlStatement writeStatement(SqlContext context) {
    return evaluate(context).write(context).toStatement(context);
  }

  default SqlStatement writeStatement() {
    return writeStatement(SqlContext.empty());
  }

  /**
   * @return separates this expression and {@param nested} with a dot, "."
   */
  LazyExpression<?> dot(LazyExpression<?> nested);

  /**
   * @return parenthesizes this expression
   */
  @CheckReturnValue
  LazyExpression<?> paren();

  /**
   * @return this expression with "ASC" suffix
   */
  @CheckReturnValue
  LazyExpression<?> asc();

  /**
   * @return this expression with "DESC" suffix
   */
  @CheckReturnValue
  LazyExpression<?> desc();

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the less than operator
   */
  @CheckReturnValue
  LazyExpression<?> lessThan(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the less than or equal to operator
   */
  @CheckReturnValue
  LazyExpression<?> lessThanOrEqualTo(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the equal to operator
   */
  @CheckReturnValue
  LazyExpression<?> equalTo(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the not equal to operator
   */
  @CheckReturnValue
  LazyExpression<?> notEqualTo(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the greater than operator
   */
  @CheckReturnValue
  LazyExpression<?> greaterThan(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the greater than or equal to operator
   */
  @CheckReturnValue
  LazyExpression<?> greaterThanOrEqualTo(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "IS" operator
   */
  @CheckReturnValue
  LazyExpression<?> is(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "LIKE" operator
   */
  @CheckReturnValue
  LazyExpression<?> like(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "NOT LIKE" operator
   */
  @CheckReturnValue
  LazyExpression<?> notLike(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "IN" operator
   */
  @CheckReturnValue
  LazyExpression<?> in(LazyExpression<?> other);

  /**
   * @param min the lower bound
   * @param max the upper bound
   * @return creates a between expression, i.e. "this BETWEEN {@param min} AND {@param max}"
   */
  @CheckReturnValue
  LazyExpression<?> between(LazyExpression<?> min, LazyExpression<?> max);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "AND" operator
   */
  @CheckReturnValue
  LazyExpression<?> and(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "OR" operator
   */
  @CheckReturnValue
  LazyExpression<?> or(LazyExpression<?> other);

  /**
   * @param other the right hand side
   * @return separates this expression and {@param other} with the "AS" operator
   */
  @CheckReturnValue
  LazyExpression<?> as(LazyExpression<?> other);
}