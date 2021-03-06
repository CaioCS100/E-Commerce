package cesmac.si.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Imagem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private byte[] foto;
    private String extensaoImagem;

    public Imagem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getExtensaoImagem() {
        return extensaoImagem;
    }

    public void setExtensaoImagem(String extensaoImagem) {
        this.extensaoImagem = extensaoImagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem1 = (Imagem) o;
        return Objects.equals(id, imagem1.id) &&
                Objects.equals(nome, imagem1.nome) &&
                Arrays.equals(foto, imagem1.foto) &&
                Objects.equals(extensaoImagem, imagem1.extensaoImagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", foto=" + Arrays.toString(foto) +
                ", extensaoImagem='" + extensaoImagem + '\'' +
                '}';
    }
}
