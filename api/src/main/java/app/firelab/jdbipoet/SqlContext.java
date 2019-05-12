package app.firelab.jdbipoet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SqlContext {
  <T> Optional<T> getConfig(Config<T> config);

  <E extends Expression> Optional<E> resolveKey(LazyExpression<E> key) throws IllegalStateException;

  Builder toBuilder();

  static SqlContext empty() {
    return SqlContext.builder().build();
  }

  static Builder builder() {
    return new Builder();
  }

  interface Config<T> {
    UUID uuid();

    Class<T> type();

    static <T> Config<T> create(Class<T> type) {
      return new Config<T>() {
        @Override
        public UUID uuid() {
          return UUID.randomUUID();
        }

        @Override
        public Class<T> type() {
          return type;
        }
      };
    }
  }

  class Builder {
    private final Map<Config<?>, Object> configuration = new HashMap<>();
    private final Map<LazyExpression<?>, Expression> keysToExpressions = new HashMap<>();

    private Builder() {

    }

    public <T> Builder withConfig(Config<T> config, T value) {
      this.configuration.put(config, value);
      return this;
    }

    public <E extends Expression> Builder withKey(LazyExpression<E> key, E expression) {
      keysToExpressions.put(key, expression);
      return this;
    }

    public SqlContext build() {
      // Make copies of builder variables to prevent dynamic changes to context.
      Map<Config<?>, Object> configuration = new HashMap<>(this.configuration);
      Map<LazyExpression<?>, Expression> keysToExpressions = new HashMap<>(this.keysToExpressions);

      return new SqlContext() {
        @SuppressWarnings("unchecked")
        @Override
        public <T> Optional<T> getConfig(Config<T> config) {
          return Optional.ofNullable((T) configuration.get(config));
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Expression> Optional<E> resolveKey(LazyExpression<E> key) throws IllegalStateException {
          return Optional.ofNullable((E) keysToExpressions.get(key));
        }

        @Override
        public Builder toBuilder() {
          Builder builder = new Builder();
          builder.configuration.putAll(configuration);
          builder.keysToExpressions.putAll(keysToExpressions);
          return builder;
        }
      };
    }
  }
}
