package classes;

public class ChaveFK {
    private String nome;
    private String referenciaTabela;
    private String referenciaColuna;

    public ChaveFK(String nome, String referenciaTabela, String referenciaColuna) {
        this.nome = nome;
        this.referenciaTabela = referenciaTabela;
        this.referenciaColuna = referenciaColuna;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferenciaTabela() {
        return referenciaTabela;
    }

    public void setReferenciaTabela(String referenciaTabela) {
        this.referenciaTabela = referenciaTabela;
    }

    public String getReferenciaColuna() {
        return referenciaColuna;
    }

    public void setReferenciaColuna(String referenciaColuna) {
        this.referenciaColuna = referenciaColuna;
    }
}
