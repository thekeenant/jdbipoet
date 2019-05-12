package app.firelab.jdbipoet;

import java.util.function.Function;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

public interface SqlStatement extends SqlPart {
  SqlContext context();

  Query toQuery(Handle handle);

  default Query toQuery(HandleWrapper wrapper) {
    return toQuery(wrapper.handle());
  }

  Update toUpdate(Handle handle);

  default Update toUpdate(HandleWrapper wrapper) {
    return toUpdate(wrapper.handle());
  }

  default <T> T map(Function<SqlStatement, ? extends T> mapper) {
    return mapper.apply(this);
  }
}
