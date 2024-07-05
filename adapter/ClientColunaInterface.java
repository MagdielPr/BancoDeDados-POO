package adapter;

import classes.Coluna;
import classes.Tipo;
import java.sql.SQLException;
import java.util.List;

public interface ClientColunaInterface {
    List<Coluna<Tipo>> listarColunas(String nomeBanco, String nomeTabela) throws SQLException;
}
