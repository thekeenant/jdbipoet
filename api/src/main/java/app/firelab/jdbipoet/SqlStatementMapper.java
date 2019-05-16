package app.firelab.jdbipoet;

import java.util.function.Function;

@FunctionalInterface
public interface SqlStatementMapper<T> extends Function<SqlStatement, T> {

}
