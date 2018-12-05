package cesmac.si.controller;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cesmac.si.dao.ProdutoDAO;
import cesmac.si.model.Produto;

import javax.faces.application.FacesMessage;

@ManagedBean
@ViewScoped
public class ProdutoController {
	Produto produto = new Produto();
	ProdutoDAO dao = new ProdutoDAO();

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getDAO() {
		return dao;
	}

	public void setDAO(ProdutoDAO dao) {
		this.dao = dao;
	}

	public String abrirPaginaCadastrarProduto() 
	{
		return "concluirCadastroProduto.xhtml";
	}

	public String cadastrarProduto() 
	{
		String direcao;
		produto.setMensagem(this.dao.insert(produto));
		if (produto.isMensagem()) {
			produto = new Produto();
			direcao = "concluirCadastroProduto.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "contate suporte"));
			direcao = "";
		}
		return direcao;

	}

	public String telaDeCadastroConcluido() {
		return "concluirCadastroProduto";
	}

	public String abrirPaginaAtualizar() throws SQLException {

		this.dao.listarProduto(1, produto);
		return "atualizar";
	}

	public String atualizarProduto() 
	{
		ProdutoDAO p = new ProdutoDAO();

		produto.setMensagem(p.update(produto));
		return "atualizar.xhtml";

	}

	public String pesquisarProduto() throws SQLException {

		produto.setTipoDeCondicao("nome");
		produto.setLista(null);
		produto.setLista(this.dao.select(produto));

		return "pesquisar";

	}

	public String test() {
		return "newFile.xhtml";
	}

	public String ajaxTest() {
		// https://www.primefaces.org/showcase/ui/message/messages.xhtml

		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "contate suporte:" + produto.getNome()));
		return produto.getNome();

	}

	public String removerMascara(String Texto) 
	{
		Texto = Texto.replaceAll("", "-");
		Texto = Texto.replaceAll("", ".");
		return Texto;

	}

}