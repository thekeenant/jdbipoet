package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SqlContext;
import app.firelab.jdbipoet.SqlPart;

class BinaryExpression implements BaseExpression {

  private final LazyExpression<?> left;
  private final LazyExpression<?> right;
  private final Comparator comparator;

  BinaryExpression(LazyExpression<?> left, LazyExpression<?> right, Comparator comparator) {
    this.left = left;
    this.right = right;
    this.comparator = comparator;
  }

  public enum Comparator {
    LESS,
    LESS_OR_EQUAL,
    EQUAL,
    NOT_EQUAL,
    GREATER_OR_EQUAL,
    GREATER,
    IS,
    LIKE,
    NOT_LIKE,
    IN,
    BETWEEN,
    AND,
    OR,
    AS
  }

  @Override
  public SqlPart write(SqlContext context) {
    return BaseSqlPart.builder(context)
        .append(left)
        .append(' ')
        .append(symbol())
        .append(' ')
        .append(right)
        .build();
  }

  private String symbol() {
    switch (comparator) {
      case LESS:
        return "<";
      case LESS_OR_EQUAL:
        return "<=";
      case EQUAL:
        return "=";
      case NOT_EQUAL:
        return "<>";
      case GREATER_OR_EQUAL:
        return ">=";
      case GREATER:
        return ">";
      case IS:
        return "IS";
      case LIKE:
        return "LIKE";
      case NOT_LIKE:
        return "NOT LIKE";
      case IN:
        return "IN";
      case BETWEEN:
        return "BETWEEN";
      case AND:
        return "AND";
      case OR:
        return "OR";
      case AS:
        return "AS";
      default:
        throw new IllegalStateException();
    }
  }
}
