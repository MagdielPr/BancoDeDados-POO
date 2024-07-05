package classes;

public class ChavePK extends Coluna {

    public ChavePK(String nome, Tipo tipo) {
        super(nome, tipo);
        this.setPrimaria(true);
    }
}