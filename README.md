# jdbipoet

`Write SQL statements fluently!`

Writing SQL statements:

```java
public class Example implements SqlFactoryMixin {
  private final TableReference users = tableName("users");
  private final ColumnReference id = columnRef("id");
  private final ColumnReference name = columnRef("name");
  private final ColumnReference age = columnRef("age");

  @Test
  public void simpleExample() {
    SqlStatement select = select(id, name, age)
        .from(users)
        .where(id.equalTo(Param.of(5)).or(age.greaterThan(Param.of(10))))
        .write();

    assertThat(select.sql()).isEqualTo("SELECT id, name, age FROM users WHERE (id = ? OR age > ?)");
    assertThat(select.bindings()).containsExactly(5, 10);
  }
}
```


Mapping to JDBI:

```java
public class JdbiExample {
  @Test
  public void findAdam() {
    Jdbi jdbi = null;

    String name = jdbi.withHandle(handle -> {
      UsersTable users = new UsersTable();

      TableRow<UsersTable> adam = users.wrapHandle(handle)
          .findByName("Adam")
          .orElse(null);

      return adam.get(table -> table.name);
    });

    assertThat(name).isEqualTo("Adam");
  }

  @Test
  public void findSeniors() {
    Jdbi jdbi = null;

    List<TableRow<UsersTable>> seniors = jdbi.withHandle(handle -> {
      UsersTable users = new UsersTable();

      return users.wrapHandle(handle)
          .findOlderThan(65)
          .list();
    });

    assertThat(seniors).extracting(row -> row.get(table -> table.age)).allMatch(i -> i >= 65);
  }
  
  // The table & handle belong in separate classes...

  private static class UsersTable extends UnqualifiedTable<UsersHandle> {
    private final Column<Integer> id = registerColumn(Integer.class, "id");
    private final Column<String> name = registerColumn(String.class, "name");
    private final Column<Integer> age = registerColumn(Integer.class, "age");

    public UsersTable() {
      super("users");
    }

    @Override
    public UsersHandle wrapHandle(Handle raw) {
      return new UsersHandle(this, raw);
    }
  }

  private static class UsersHandle extends AbstractTableHandle<UsersTable> implements SqlFactoryMixin {
    private UsersHandle(UsersTable table, Handle handle) {
      super(table, handle);
    }

    public Optional<TableRow<UsersTable>> findByName(String name) {
      return select()
          .from(table)
          .where(table.name.equalTo(Param.of(name)))
          .write()
          .toQuery(this)
          .map(this)
          .findOne();
    }

    public ResultIterable<TableRow<UsersTable>> findOlderThan(int minAge) {
      return select()
          .from(table)
          .where(table.age.greaterThan(Param.of(minAge)))
          .write()
          .toQuery(this)
          .map(this);
    }
  }
}
```