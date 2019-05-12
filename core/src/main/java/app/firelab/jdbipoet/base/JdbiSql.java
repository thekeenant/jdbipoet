package app.firelab.jdbipoet.base;

import app.firelab.jdbipoet.JdbiExpressionFactory;
import app.firelab.jdbipoet.SqlContext.Config;
import org.jdbi.v3.core.Handle;

public class JdbiSql {
  private static final JdbiExpressionFactory jdbiExpressions = new BaseJdbiExpressionFactory();
  private static final Config<Handle> handleConfig = Config.create(Handle.class);

  public static JdbiExpressionFactory expressions() {
    return jdbiExpressions;
  }

  public static Config<Handle> handleConfig() {
    return handleConfig;
  }
}
