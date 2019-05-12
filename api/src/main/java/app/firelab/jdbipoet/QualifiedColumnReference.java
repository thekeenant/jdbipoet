package app.firelab.jdbipoet;

public interface QualifiedColumnReference extends ColumnReference {
  TableReference table();

  ColumnReference unqualify();

  @Override
  default QualifiedColumnReference qualifyWith(TableReference table) {
    throw new UnsupportedOperationException("Cannot qualifyWith already qualified column");
  }
}
