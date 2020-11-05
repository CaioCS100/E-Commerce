package cesmac.si.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String ddd;
    private String telefone;
    private String email;
    private String senha;
    private Boolean restrito;
    private Date dataHoraCadastro;
    private Date dataHoraUltimaModificacao;
    private Endereco endereco;
    private Imagem imagem;

    public Usuario() {
        this.endereco = new Endereco();
        this.imagem = new Imagem();
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getRestrito() {
        return restrito;
    }

    public void setRestrito(Boolean restrito) {
        this.restrito = restrito;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Date getDataHoraUltimaModificacao() {
        return dataHoraUltimaModificacao;
    }

    public void setDataHoraUltimaModificacao(Date dataHoraUltimaModificacao) {
        this.dataHoraUltimaModificacao = dataHoraUltimaModificacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(dataNascimento, usuario.dataNascimento) &&
                Objects.equals(ddd, usuario.ddd) &&
                Objects.equals(telefone, usuario.telefone) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(restrito, usuario.restrito) &&
                Objects.equals(dataHoraCadastro, usuario.dataHoraCadastro) &&
                Objects.equals(dataHoraUltimaModificacao, usuario.dataHoraUltimaModificacao) &&
                Objects.equals(endereco, usuario.endereco) &&
                Objects.equals(imagem, usuario.imagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", ddd='" + ddd + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", restrito=" + restrito +
                ", dataHoraCadastro=" + dataHoraCadastro +
                ", dataHoraUltimaModificacao=" + dataHoraUltimaModificacao +
                ", endereco=" + endereco +
                ", imagem=" + imagem +
                '}';
    }
}
