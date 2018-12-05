package cesmac.si.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

public class Produto {
	  private  String nome;
	  private  boolean mensagem;
	  private  int id;
	  private  float preco; 
	  private  String descricao;
	  private  String tipo;
	  private  String subTipo;
	  private  Integer quantidadeNoEstoque;
	  private  String tipoDeCondicao;
	  private  String condicao;
	  ArrayList tipos;
	  private List <Produto> lista=new ArrayList <Produto>();
	  
	  public Produto() {
		  
	  }
	  
	  public Produto(int id,String nome,String descricao,String tipo,float preco,Integer quantidadeNoEstoque,int quantidadeConsideradaBaixa) {
		  this.id=id;
		  this.nome=nome;
		  this.descricao=descricao;
		  this.tipo=tipo;
		  this.preco=preco;
		  this.quantidadeNoEstoque=quantidadeNoEstoque;
	  }
	public List<Produto> getLista() {
		return lista;
	}
	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isMensagem() {
		return mensagem;
	}
	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
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
	public String getSubTipo() {
		return subTipo;
	}
	public void setSubTipo(String subTipo) {
		this.subTipo = subTipo;
	}

	public Integer getQuantidadeNoEstoque() {
		return quantidadeNoEstoque;
	}

	public void setQuantidadeNoEstoque(Integer quantidadeNoEstoque) {
		this.quantidadeNoEstoque = quantidadeNoEstoque;
	}

	public String getTipoDeCondicao() {
		return tipoDeCondicao;
	}
	public void setTipoDeCondicao(String tipoDeCondicao) {
		this.tipoDeCondicao = tipoDeCondicao;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	public ArrayList getTipos() {
		return tipos;
	}
	public void setTipos(ArrayList tipos) {
		this.tipos = tipos;
	}
	
}

