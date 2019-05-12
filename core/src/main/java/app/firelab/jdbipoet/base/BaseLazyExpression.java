package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.Expression;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.base.BinaryExpression.Comparator;

interface BaseLazyExpression<E extends Expression> extends LazyExpression<E> {

  static <E extends Expression> BaseLazyExpression<E> of(E expression) {
    return context -> expression;
  }

  @Override
  default LazyExpression<?> dot(LazyExpression<?> nested) {
    return (BaseExpression) context -> BaseSqlPart.builder(context)
        .append(this)
        .append('.')
        .append(nested)
        .build();
  }

  @Override
  default LazyExpression<?> paren() {
    return (BaseExpression) context -> BaseSqlPart.builder(context)
        .append('(')
        .append(this)
        .append(')')
        .build();
  }

  @Override
  default LazyExpression<?> asc() {
    return (BaseExpression) context -> BaseSqlPart.builder(context)
        .append(this)
        .append(" ASC")
        .build();
  }

  @Override
  default LazyExpression<?> desc() {
    return (BaseExpression) context -> BaseSqlPart.builder(context)
        .append(this)
        .append(" DESC")
        .build();
  }

  @Override
  default LazyExpression<?> lessThan(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.LESS);
  }

  @Override
  default LazyExpression<?> lessThanOrEqualTo(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.LESS_OR_EQUAL);
  }

  @Override
  default LazyExpression<?> equalTo(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.EQUAL);
  }

  @Override
  default LazyExpression<?> notEqualTo(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.NOT_EQUAL);
  }

  @Override
  default LazyExpression<?> greaterThan(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.GREATER);
  }

  @Override
  default LazyExpression<?> greaterThanOrEqualTo(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.GREATER_OR_EQUAL);
  }

  @Override
  default LazyExpression<?> is(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.IS);
  }

  @Override
  default LazyExpression<?> like(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.LIKE);
  }

  @Override
  default LazyExpression<?> notLike(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.NOT_LIKE);
  }

  @Override
  default LazyExpression<?> in(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.IN);
  }

  @Override
  default LazyExpression<?> between(LazyExpression<?> min, LazyExpression<?> max) {
    return (BaseExpression) context -> BaseSqlPart.builder(context)
        .append(this)
        .append(" BETWEEN ")
        .append(min)
        .append(" AND ")
        .append(max)
        .build();
  }

  @Override
  default LazyExpression<?> and(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.AND).paren();
  }

  @Override
  default LazyExpression<?> or(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.OR).paren();
  }

  @Override
  default LazyExpression<?> as(LazyExpression<?> other) {
    return new BinaryExpression(this, other, Comparator.AS);
  }
}
