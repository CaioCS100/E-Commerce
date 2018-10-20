package cesmac.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.controller.ProdutoController;



public class ProdutoDAO {
	public String Insert(String nome,float preco,String descricao,int quantidade,String tipo) throws SQLException, ClassNotFoundException{ 
		
		Connection CF = ConnectionFactory.getConnection();
		
		int id=0;
		String status="";
	    String sql="INSERT INTO produto(nome,preco,descricao,quantidadeNoEstoque,tipo) "
	    		+ "VALUES(?,?,?,?,?) RETURNING id";//https://www.dbrnd.com/2015/11/postgresql-select-last-inserted-id-or-sequence-value/

		try {

			PreparedStatement ps = CF.prepareStatement(sql);
	        ps.setString(1,nome);
	        ps.setFloat(2,preco);
	        ps.setString(3,descricao);
	        ps.setInt(4,quantidade);
			ps.setString(5,tipo);
	        ResultSet RS=ps.executeQuery();
	        
	        while(RS.next()) {
	        	id=RS.getInt("id");
	        }

			status="Cadastro realizado com sucesso de produto de nome:"+nome+"(de ID:"+id+")";

		} catch (SQLException e) {

            status=e.toString();
			
        }
	    

	        return status;
	}
	public List Select(String tipoDeCondicao,String condicao,ArrayList<?> tipos) throws SQLException, ClassNotFoundException{
		
		ResultSet RS=null;
		
		ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
		
	    if(tipoDeCondicao.equals("id")){

	         int id=Integer.parseInt(condicao);

	        String sql="SELECT * FROM produto WHERE id=?";

	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1,id);

	         RS=ps.executeQuery();
	        

	    }if(tipoDeCondicao.equals("nome")){

	        String sql="";

	        if(tipos!=null){//se usuario selecionou "tipos"

	                for(int i=0;i<tipos.size();i++){
	                sql+="SELECT * FROM produto WHERE nome ILIKE %"+condicao+"% AND tipo="+tipos.get(i)+"";  
	                    if(i!=tipos.size()){//Quando for o ultimo nao adicionara union
	                        sql+=" UNION ";
	                    }
	                }
	                
	             PreparedStatement ps = connection.prepareStatement(sql);
	             RS=ps.executeQuery();
	            

	        }else{
	            sql="SELECT * FROM produto WHERE nomeProduto ILIKE %"+condicao+"%";
	            PreparedStatement ps = connection.prepareStatement(sql);
	             RS=ps.executeQuery();
	            
	        }

	    }
	    List <ProdutoController> Objetos=new ArrayList <ProdutoController> ();
		while(RS.next()){
			ProdutoController produto = new ProdutoController();
			produto.setNome(RS.getString("nome"));
			
			Objetos.add(produto);
//			Objetos.add(new Produto(RS.getString("nome")));
		}
		return Objetos;
	}
	public void ListarProduto(int id) throws SQLException, ClassNotFoundException{
		ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
		
		String sql="SELECT * FROM produto WHERE id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet RS=ps.executeQuery();
			while(RS.next()){
				/*new Produto(
				 * 		RS.getInt("id"),
						RS.getString("nome"),
						RS.getString("descricao"),
						RS.getString("tipo"),
						RS.getFloat("preco"),
						RS.getInt("quantidadeNoEstoque"),
						RS.getInt("quantidadeConsideradaBaixa"));
				*/
			}
	}
	public String Update(int id,String nome,String preco,String descricao,int quantidadeNoEstoque) throws SQLException, ClassNotFoundException{
	    String status="";

	    ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
       
	    try{

			String sql="UPDATE produto SET nome=?,preco=?,descricao=?,quantidadeNoEstoque=? WHERE id=?";
				PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1,nome);
					ps.setString(2,preco);
					ps.setString(3,descricao);
					ps.setInt(4,quantidadeNoEstoque);
					ps.setInt(5,id);
					ps.execute();

			status="Atualizacao concluida";

		} catch (SQLException e) {

            status="Banco com erro ou inativo:"+e;
			
        }
			


	        return status;

	}
	public String Remove(int id) throws ClassNotFoundException{
		
		ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
		
		return null;

	}
	
	public List<ProdutoController> t(){
		List <ProdutoController> Guardar = new ArrayList<ProdutoController>();
		
		ProdutoController produto = new ProdutoController(1,"Ian","","",1,1,1);
//		Guardar.add(new ProdutoController(1,"Ian","","",1,1,1));
		Guardar.add(produto);
		produto = new ProdutoController(1,"Jorge","","",1,1,1);
		
//		Guardar.add(new ProdutoController(1,"Jorge","","",1,1,1));
		Guardar.add(produto);
		
		System.out.println(Guardar.get(0).getNome());
		System.out.println(Guardar.get(1).getNome());
		return Guardar;
		
	}
	
	/*public ArrayList VerificarEstoque(){
	    String sql="SELECT * FROM produto";
	    ArrayList Aviso=new ArrayList();     
	    
	        while(RS.next()){
	        	
	            if(RS.getInt("quantidadeNoEstoque") >= RS.getInt("quantidadeConsideradaBaixa")){
	                Aviso.add("O produto "+RS.getString("nome")+"(de ID:"+RS.getInt("id")+") esta com estoque baixo\n"+
	                          "Estoque atual:"+RS.getInt("quantidadeNoEstoque")+
	                          "(Quantidade considerada baixa em "+RS.getInt("quantidadeConsideradaBaixa")+")");
	            }
	            
	        }
	        return Aviso;

	}*/


/*
	-produto
	id
	nome VARCHAR
	descricao VARCHAR
	tipo VARCHAR
	//subtipo VARCHAR
	preco FLOAT
	quantidadeNoEstoque INT
	quantidadeConsideradaBaixa INT

	-venda
	id
	id_cliente
	timestamp

	-venda_produto
	id_venda
	id_produto
	quantidadeComprada
	
	CREATE TABLE produto(
		 id serial PRIMARY KEY,
		 nome VARCHAR (50),
		 descricao VARCHAR (50),
		 tipo VARCHAR (50),
		 preco float(20),
		 quantidadeNoEstoque INTEGER,
		 quantidadeConsideradaBaixa INTEGER,
		 created_on TIMESTAMP
	);
	CREATE TABLE venda(
		 id serial PRIMARY KEY,
		 id_produto references produto(id),
		 id_cliente references cliente(id),
		 dataDeCompra date
	);
	CREATE TABLE venda_produto(
		 id serial PRIMARY KEY,
		 id_venda references venda(id),
		 id_produto references produto(id),
		 quantidadeComprada INTEGER
	);
	
*/
}
