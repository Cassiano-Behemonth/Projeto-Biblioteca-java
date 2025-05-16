package model;

public class Usuario extends Pessoa {
    public Usuario(String nome, String telefone, String email, String endereco) {
        super(nome, telefone, email, endereco);
    }

    @Override
    public String toString() {
        return getNome() + " (" + getEmail() + ")";
    }
}
