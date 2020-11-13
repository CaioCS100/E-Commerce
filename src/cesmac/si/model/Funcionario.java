package cesmac.si.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private String sexo;
	private String ddd;
	private String telefone;
	private String email;
	private String senha;
	private Boolean restrito;
	private Date dataHoraCadastro;
	private Date dataHoraUltimaModificacao;
	private String cargo;
	private Double salario;
	private Integer qtdHoras;
	private Endereco endereco;
	private Imagem imagem;

	public Funcionario() {
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getQtdHoras() {
		return qtdHoras;
	}

	public void setQtdHoras(Integer qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Funcionario that = (Funcionario) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(nome, that.nome) &&
				Objects.equals(cpf, that.cpf) &&
				Objects.equals(dataNascimento, that.dataNascimento) &&
				Objects.equals(sexo, that.sexo) &&
				Objects.equals(ddd, that.ddd) &&
				Objects.equals(telefone, that.telefone) &&
				Objects.equals(email, that.email) &&
				Objects.equals(senha, that.senha) &&
				Objects.equals(restrito, that.restrito) &&
				Objects.equals(dataHoraCadastro, that.dataHoraCadastro) &&
				Objects.equals(dataHoraUltimaModificacao, that.dataHoraUltimaModificacao) &&
				Objects.equals(endereco, that.endereco) &&
				Objects.equals(imagem, that.imagem) &&
				Objects.equals(cargo, that.cargo) &&
				Objects.equals(salario, that.salario) &&
				Objects.equals(qtdHoras, that.qtdHoras);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpf, email);
	}

	@Override
	public String toString() {
		return "Funcionario{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", dataNascimento=" + dataNascimento +
				", sexo='" + sexo + '\'' +
				", ddd='" + ddd + '\'' +
				", telefone='" + telefone + '\'' +
				", email='" + email + '\'' +
				", senha='" + senha + '\'' +
				", restrito=" + restrito +
				", dataHoraCadastro=" + dataHoraCadastro +
				", dataHoraUltimaModificacao=" + dataHoraUltimaModificacao +
				", cargo='" + cargo + '\'' +
				", salario=" + salario +
				", qtdHoras=" + qtdHoras +
				", endereco=" + endereco +
				", imagem=" + imagem +
				'}';
	}
}
