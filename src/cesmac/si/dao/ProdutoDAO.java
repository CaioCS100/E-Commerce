package Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
	public String Insert(String nome,float preco,String descricao,int quantidade,String tipo) throws SQLException{ 
		
		ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
		
		String status="";
	    String sql="INSERT INTO Produto(nome,preco,descricao,quantidade) VALUES(?,?,?,?)";

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1,nome);
	        ps.setFloat(2,preco);
	        ps.setString(3,descricao);
	        ps.setInt(4,quantidade);
			ps.setString(5,tipo);
	        ps.execute();

			status="Cadastro realizado com sucesso";

		} catch (SQLException e) {

            status="Banco com erro ou inativo";
			
        }
	    

	        return status;
	}
	public ResutSet Select(String tipoDeCondicao,String condicao,ArrayList tipos){
		
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

	                for(int i=0;i<tipo.size();i++){
	                sql+="SELECT * FROM produto WHERE nome ILIKE %"+condicao+"% AND tipo="+tipo.get(i)+"";  
	                    if(i!=tipo.size()){//Quando for o ultimo nao adicionara union
	                        sql+=" UNION ";
	                    }
	                }
	                
	            PreparedStatement ps = connection.prepareStatement(sql);
	             RS=ps.executeQuery();
	            

	        }else{
	            sql="SELECT * FROM produto WHERE nome ILIKE %"+condicao+"%";
	            PreparedStatement ps = connection.prepareStatement(sql);
	             RS=ps.executeQuery();
	            
	        }

	    }
       return RS;
	}
	public String Update(int id,String nome,float preco,String descricao,int quantidadeNoEstoque) throws SQLException{
	    String status="";

	    ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
       
	    try{

			String sql="UPDATE Produto SET nome=?,preco=?,descricao=?,quantidadeNoEstoque=? WHERE id=?";
				PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1,nome);
					ps.setFloat(2,preco);
					ps.setString(3,descricao);
					ps.setInt(4,quantidadeNoEstoque);
					ps.setInt(5,id);
					ps.execute();

			status="Atualizacao concluida";

		} catch (SQLException e) {

            status="Banco com erro ou inativo";
			
        }
			


	        return status;

	}
	public String Remove(int id){
		
		ConnectionFactory CF = new ConnectionFactory();
		Connection connection=CF.getConnection();
		
		return null;

	}
	public ArrayList VerificarEstoque(){
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

	}


/*
	-produto
	id
	nome VARCHAR
	descriçao VARCHAR
	tipo VARCHAR
	//subtipo VARCHAR
	preço FLOAT
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
*/
}
