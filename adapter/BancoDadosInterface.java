package adapter;

import classes.BancoDados;
import java.sql.SQLException;
import java.util.List;

public interface BancoDadosInterface {
    void createDatabase(BancoDados bancoDados) throws SQLException;
    void dropDatabase(String nomeBanco) throws SQLException;
    List<BancoDados> listDatabases() throws SQLException;
}
