package aa;
 import javax.faces.bean.ManagedBean; 
  
  @ManagedBean 
  public class ProdutoBean { 
	  private String nome; 
	  private float preco; 
	  public String descricao;
	  public String tipo;
	  public String subTipo;
	  public int quantidadeNoEstoque;

	  public ResultSet ResultadoDaProcura;
	  public String Mensagem;//Mensagem de resposta(Aviso de sucesso ou de fracasso)
	  public ResultSet ResultadoDeDadosRelacionamento;//Listar dados relacionados com produto(Fornecedores)

	  
	  
	  
	  public String getNome() { 
		  return this.nome; 
		  } 
	  
	  public void setNome(String texto) { 
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
		 
		@PostConstruct
	    public void init() {//Ira ser executado antes do XHTML
			  this.nome="NOMA";
			 
		}

		public String CadastrarProduto() {
		  Produto p=new Produto(); 
		
		  this.Mensagem=p.Insert(this.nome,this.preco,this.descriçao,this.quantidadeNoEstoque,this.tipo);
		  return "pesquisa";

		} 

		public String AtualizarProduto() {
		  Produto p=new Produto(); 
		
		  this.Mensagem=p.Update(this.id,this.nome,this.preco,this.descriçao,this.quantidadeNoEstoque);
		  return "pesquisa";

		} 

        public String PesquisarProduto(){
			Produto p=new Produto();
             
			this.Mensagem=p.Select(this.tipoDeCondicao,this.condicao,this.tipos);


		}

		public String RemoverProduto(){
			Produto p=new Produto();

			this.Mensagem=p.Remove(this.id);
		}



	  }