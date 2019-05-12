package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.InsertStatement;
import app.firelab.jdbipoet.InsertStatementBuilderStep1;
import app.firelab.jdbipoet.InsertStatementBuilderStep2;
import app.firelab.jdbipoet.InsertStatementBuilderStep3;
import app.firelab.jdbipoet.LazyExpression;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

class BaseInsertStatementBuilderStep1 implements InsertStatementBuilderStep1, BaseExpression {
  private final LazyExpression<?> into;
  private final List<Map<String, LazyExpression<?>>> values;

  BaseInsertStatementBuilderStep1(LazyExpression<?> into, @Nullable List<Map<String, LazyExpression<?>>> values) {
    this.into = into;
    this.values = values;
  }

  @Override
  public LazyExpression<?> excluded() {
    return Sql.expressions().excluded();
  }

  @Override
  public InsertStatementBuilderStep1 defaultValues() {
    return new BaseInsertStatementBuilderStep1(into, null);
  }

  @Override
  public InsertStatementBuilderStep1 values(@Nullable Map<String, LazyExpression<?>> values) {
    List<Map<String, LazyExpression<?>>> copy = new ArrayList<>();
    if (this.values != null) {
      copy.addAll(this.values);
    }
    copy.add(values);
    return new BaseInsertStatementBuilderStep1(into, copy);
  }

  @Override
  public InsertStatementBuilderStep2 onConflictDoNothing() {
    return null;
  }

  @Override
  public InsertStatementBuilderStep2 onConflictDoUpdateSet(Map<String, LazyExpression<?>> values) {
    return null;
  }

  @Override
  public InsertStatementBuilderStep2 onConflictDoUpdateSet(Map<String, LazyExpression<?>> values,
      LazyExpression<?> where) {
    return null;
  }

  @Override
  public InsertStatementBuilderStep3 returning() {
    return null;
  }

  @Override
  public InsertStatementBuilderStep3 returning(LazyExpression<?> fields) {
    return null;
  }

  @Override
  public InsertStatement build() {
    return null;
  }
}
