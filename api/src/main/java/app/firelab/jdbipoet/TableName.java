package app.firelab.jdbipoet;

public interface TableName extends Identifier, TableReference {
  @Override
  default TableName name() {
    return this;
  }
}
