package adapter;

import classes.Coluna;
import java.sql.SQLException;
import java.util.List;

public interface ColunaInterface {
    List<Coluna<?>> listColumns(String nomeBanco, String nomeTabela) throws SQLException;
}
