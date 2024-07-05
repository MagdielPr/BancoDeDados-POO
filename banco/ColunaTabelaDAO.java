package banco;

import java.sql.SQLException;
import java.util.List;

public interface ColunaTabelaDAO<T> {
    void adicionarColuna(String nomeBanco, String nomeTabela, T coluna) throws SQLException;
    void alterarColuna(String nomeBanco, String nomeTabela, T coluna) throws SQLException;
    void removerColuna(String nomeBanco, String nomeTabela, String nomeColuna) throws SQLException;
    List<T> listarColunas(String nomeBanco, String nomeTabela) throws SQLException;
}
