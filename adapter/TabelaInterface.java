package adapter;

import classes.Tabela;
import classes.TabelaAssociativa;
import java.sql.SQLException;
import java.util.List;

public interface TabelaInterface {
    void createTable(String nomeBanco, Tabela tabela) throws SQLException;
    List<Tabela> listTables(String nomeBanco) throws SQLException;
    void addForeignKey(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException;
}
