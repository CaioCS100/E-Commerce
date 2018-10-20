package cesmac.si.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.dao.ProdutoDAO;

public class ProdutoController {

	public ProdutoController() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoController(int id,String nome,String descricao,String tipo,float preco,int quantidadeNoEstoque,int quantidadeConsideradaBaixa) {
		  this.id=id;
		  this.nome=nome;
		  this.preco=preco;
	  }
	  String nome;

	public String mensagem;
	private int id;
	private float preco;
	public String descricao;
	String tipo = "OOO";
	public String subTipo;
	public int quantidadeNoEstoque;
	String tipoDeCondicao;
	String condicao;
	ArrayList tipos;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public List<ProdutoController> lista = new ArrayList<ProdutoController>();

	public List<ProdutoController> getLista() {
		return lista;
	}

	public void setLista(List<ProdutoController> lista) {
		this.lista = lista;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return this.preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@PostConstruct
	public void init() throws ClassNotFoundException {// Ira ser executado antes do XHTML
		// ProdutoDAO p=new ProdutoDAO();
		// Lista=p.t();
		Connection CF = ConnectionFactory.getConnection();
		// Connection connection=CF.getConnection();
		System.out.println(mensagem);
	}

	public String AbrirPaginaCadastrarProduto() {
		return "Cadastro";
		// return "index?faces-redirect=true";
	}

	public void CadastrarProduto() throws SQLException, ClassNotFoundException {
		ProdutoDAO p = new ProdutoDAO();

		this.mensagem = p.Insert(this.nome, this.preco, this.descricao, this.quantidadeNoEstoque, this.tipo);
		System.out.println(mensagem);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Sucedido", mensagem));

	}

	public String AbrirPaginaAtualizar() {

		ProdutoDAO p = new ProdutoDAO();
		// p.ListarDadosDeUmProduto(this.id);

		return "/Atualizar.xhtml?faces-redirect=true";
	}

	public String AtualizarProduto() throws SQLException, ClassNotFoundException {
		ProdutoDAO p = new ProdutoDAO();

//		this.mensagem = p.Update(this.id, this.nome, this.preco, this.descricao, this.quantidadeNoEstoque);
		return "Atualizar.xhtml";

	}

	public List PesquisarProduto() throws ClassNotFoundException, SQLException {
		ProdutoDAO p = new ProdutoDAO();
		lista = p.Select(this.tipoDeCondicao, this.condicao, this.tipos);
		return lista;

	}

	public String RemoverProduto() throws ClassNotFoundException {
		ProdutoDAO p = new ProdutoDAO();

		return this.mensagem = p.Remove(this.id);
	}

	public String test() {
		ProdutoDAO p = new ProdutoDAO();
		lista = p.t();
		return "NewFile.xhtml";// Mensagem de cadastro bem sucedido,depois(com javascript)redirecionar depois
								// de um tempo para outra pagina
		// return "/NewFile.xhtml?faces-redirect=true";
		// //https://stackoverflow.com/questions/3605238/how-do-you-pass-view-parameters-when-navigating-from-an-action-in-jsf2
		// http://respostas.guj.com.br/18701-redirect-com-mensagens
	}

	public String ajaxTest() {
		// https://www.primefaces.org/showcase/ui/message/messages.xhtml
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensagem));

		return mensagem;

	}

	public int ajaxTest2() {

		return id;

	}

}
