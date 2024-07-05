
package classes;

public class Coluna<T extends Tipo> {
    private String nome;
    private T tipo;
    private int tamanho;
    private boolean primaria;
    private boolean notnull;
    private boolean unique;
    private boolean autoincre;

    public Coluna(String nome, T tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public T getTipo() {
        return tipo;
    }

    public void setTipo(T tipo) {
        this.tipo = tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isPrimaria() {
        return primaria;
    }

    public void setPrimaria(boolean primaria) {
        this.primaria = primaria;
    }

    public boolean isNotnull() {
        return notnull;
    }

    public void setNotnull(boolean notnull) {
        this.notnull = notnull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isAutoincre() {
        return autoincre;
    }

    public void setAutoincre(boolean autoincre) {
        this.autoincre = autoincre;
    }
}
