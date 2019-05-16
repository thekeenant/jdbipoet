package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.DeleteStatementBuilderStep1;
import app.firelab.jdbipoet.InsertStatementBuilderStep1;
import app.firelab.jdbipoet.LazyExpression;
import app.firelab.jdbipoet.SelectStatementBuilderStep1;
import app.firelab.jdbipoet.Statement;
import app.firelab.jdbipoet.StatementFactory;
import app.firelab.jdbipoet.UpdateStatementBuilderStep1;
import java.util.List;

public interface StatementFactoryMixin extends StatementFactory {
  @Override
  default InsertStatementBuilderStep1 insertInto(LazyExpression<?> table) {
    return null;
  }

  @Override
  default Statement statement(String raw, List<Object> bindings) {
    return Sql.statements().statement(raw, bindings);
  }

  @Override
  default SelectStatementBuilderStep1 select() {
    return Sql.statements().select();
  }

  @Override
  default <E extends LazyExpression<?>> SelectStatementBuilderStep1 select(List<E> what) {
    return Sql.statements().select(what);
  }

  @Override
  default UpdateStatementBuilderStep1 update(LazyExpression<?> table) {
    return null;
  }

  @Override
  default DeleteStatementBuilderStep1 deleteFrom(LazyExpression<?> table) {
    return null;
  }
}
