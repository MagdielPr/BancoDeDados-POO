package adapter;

import classes.BancoDados;
import java.sql.SQLException;
import java.util.List;

public interface ClientBancoDadosInterface {
    void criarBancoDados(BancoDados bancoDados) throws SQLException;
    void removerBancoDados(String nomeBanco) throws SQLException;
    List<BancoDados> listarBancosDados() throws SQLException;
}
