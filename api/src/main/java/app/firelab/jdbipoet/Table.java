package app.firelab.jdbipoet;

import java.util.List;
import org.jdbi.v3.core.Handle;

public interface Table<H extends TableHandle<?>> {
  List<Column<?>> columns();

  H wrapHandle(Handle raw);

  default H wrapHandle(HandleWrapper wrapper) {
    return wrapHandle(wrapper.handle());
  }
}
