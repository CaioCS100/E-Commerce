package cesmac.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Produto;

public class ProdutoDAO {

	Produto model = new Produto();
	private PreparedStatement pst;
	private Connection conn;
	private ResultSet rs;

	public boolean insert(Produto produto) 
	{
		this.conn = ConnectionFactory.getConnection();
		boolean status;
		String sql = "INSERT INTO produto(nome,preco,descricao,quantidadeNoEstoque,tipo) " + "VALUES(?,?,?,?,?)";

		try {
			this.pst = this.conn.prepareStatement(sql);
			this.pst.setString(1, produto.getNome());
			this.pst.setFloat(2, produto.getPreco());
			this.pst.setString(3, produto.getDescricao());
			this.pst.setInt(4, produto.getQuantidadeNoEstoque());
			this.pst.setString(5, produto.getTipo());
			this.pst.executeUpdate();
			this.conn.commit();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}

		return status;
	}

	public List<Produto> select(Produto produto) throws SQLException 
	{
		String sql = "";
		this.conn = ConnectionFactory.getConnection();
		List<Produto> objetos = new ArrayList<Produto>();
		if (produto.getTipoDeCondicao().equals("id")) 
		{

			int id = Integer.parseInt(produto.getCondicao());

			sql = "SELECT * FROM produto WHERE id=?";

			this.pst = this.conn.prepareStatement(sql);
			this.pst.setInt(1, id);
			this.rs = this.pst.executeQuery();

		}
		else if (produto.getTipoDeCondicao().equals("nome")) 
		{
			
				sql = "SELECT * FROM produto WHERE nome ILIKE ?";
				this.pst = this.conn.prepareStatement(sql);
				this.pst.setString(1, "%" + produto.getCondicao() + "%");
				this.rs = this.pst.executeQuery();
		}
		
		while (this.rs.next()) 
		{
			Produto prod = new Produto();

			prod.setId(this.rs.getInt("id"));
			prod.setNome(this.rs.getString("nome"));
			prod.setDescricao(this.rs.getString("descricao"));
			prod.setTipo(this.rs.getString("tipo"));
			prod.setPreco(this.rs.getFloat("preco"));
			prod.setQuantidadeNoEstoque(0);

			objetos.add(prod);
		}
		
		return objetos;
	}

	public boolean update(Produto produto)
	{
		boolean status;

		this.conn = ConnectionFactory.getConnection();

		try {

			String sql = "UPDATE produto SET nome = ?,preco = ?,descricao = ?,quantidadeNoEstoque = ? WHERE id = ?";
			this.pst = this.conn.prepareStatement(sql);
			this.pst.setString(1, produto.getNome());
			this.pst.setFloat(2, produto.getPreco());
			this.pst.setString(3, produto.getDescricao());
			this.pst.setInt(4, produto.getQuantidadeNoEstoque());
			this.pst.setInt(5, produto.getId());
			this.pst.execute();
			this.conn.commit();
			status = true;

		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}

		return status;
	}

	public void listarProduto(int id, Produto produto) throws SQLException 
	{
		this.conn = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM produto WHERE id=?";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id);
		this.rs = this.pst.executeQuery();
		while (this.rs.next()) 
		{
			produto.setId(this.rs.getInt("id"));
			produto.setNome(this.rs.getString("nome"));
			produto.setDescricao(this.rs.getString("descricao"));
			produto.setTipo(this.rs.getString("tipo"));
			produto.setPreco(this.rs.getFloat("preco"));
			produto.setQuantidadeNoEstoque(this.rs.getInt("quantidadeNoEstoque"));
		}

	}

	public String remove(int id) 
	{

		this.conn = ConnectionFactory.getConnection();
		

		return null;

	}

	public List<Produto> t() {
		List<Produto> guardar = new ArrayList<Produto>();

		Produto produto = new Produto(1, "Ian", "", "", 1, 1, 1);
		guardar.add(produto);
		produto = new Produto(1, "Jorge", "", "", 1, 1, 1);

		guardar.add(produto);

		return guardar;

	}

}
