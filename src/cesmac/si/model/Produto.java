package Produt;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
  public class Produto{ 
	  
	public Produto() {
		  
	  }
	  public Produto(String nome) {
		  this.nome=nome;
	  }
	  String nome; 
	  private int id;
	  private String preco="AAaaa"; 
	  public String descricao="ADDDDDDDDDDDDDDDs";
	  String tipo="OOO";
	  public String subTipo;
	  public int quantidadeNoEstoque;

	  String tipoDeCondicao;
	  String condicao;
	  ArrayList tipos;
	  
	  public ResultSet ResultadoDaProcura;
	  public String Mensagem;//Mensagem de resposta(Aviso de sucesso ou de fracasso)
	  public ResultSet ResultadoDeDadosRelacionamento;//Listar dados relacionados com produto(Fornecedores)
	  
	  public List<Produto> lista=new ArrayList<Produto>();
	
	  
		public List<Produto> getLista() {
			return lista;
		}
		public void setLista(List<Produto> lista) {
			lista = lista;
		}
	  public String getNome() { 
		  return this.nome; 
		  } 
	  
	  public void setNome(String nome) { 
		  this.nome = nome; 
		 } 
		 
	  public String getPreco() { 
		  return this.preco; 
		  } 
	  
	  public void setPreco(String preco) { 
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
	    public void init(){//Ira ser executado antes do XHTML
			//ProdutoDAO p=new ProdutoDAO();
			//Lista=p.t();
			
		}

		public String CadastrarProduto() throws SQLException, ClassNotFoundException {
		  ProdutoDAO p=new ProdutoDAO(); 
		
		  this.Mensagem=p.Insert(this.nome,this.preco,this.descricao,this.quantidadeNoEstoque,this.tipo);
		  return "pesquisa";

		} 

		public String AbrirPaginaAtualizar() {
			
			ProdutoDAO p = new ProdutoDAO();
			//p.ListarDadosDeUmProduto(this.id);
			
			return "Atualizar.xhtml";
		}
		public String AtualizarProduto() throws SQLException, ClassNotFoundException {
			ProdutoDAO p=new ProdutoDAO(); 
		
		  this.Mensagem=p.Update(this.id,this.nome,this.preco,this.descricao,this.quantidadeNoEstoque);
		  return "Atualizar.xhtml";

		} 

        public String PesquisarProduto() throws ClassNotFoundException, SQLException{
        	ProdutoDAO p=new ProdutoDAO();
			p.Select(this.tipoDeCondicao,this.condicao,this.tipos);
			return Mensagem;
			

		}

		public String RemoverProduto() throws ClassNotFoundException{
			ProdutoDAO p=new ProdutoDAO();

			return this.Mensagem=p.Remove(this.id);
		}
		public String test() {
			ProdutoDAO p=new ProdutoDAO();
			lista=p.t();
			return "NewFile.xhtml";//Mensagem de cadastro bem sucedido,depois(com javascript)redirecionar depois de um tempo para outra pagina
			//return "/NewFile.xhtml?faces-redirect=true"; //https://stackoverflow.com/questions/3605238/how-do-you-pass-view-parameters-when-navigating-from-an-action-in-jsf2
			//http://respostas.guj.com.br/18701-redirect-com-mensagens
		}

		


	  }
