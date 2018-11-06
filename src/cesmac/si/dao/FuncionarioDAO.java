package cesmac.si.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.controller.UtilitariosController;
import cesmac.si.model.Funcionario;

public class FuncionarioDAO {
	private PreparedStatement pst;
	private Connection conn;
	private ResultSet rs;
	private Funcionario modelFuncionario;
	private UtilitariosController utilitarios = new UtilitariosController();

	public Boolean cadastrarFuncionario(Funcionario model) {
		String sql = "insert into funcionarios(nome, cpf, cep, data_de_nascimento, telefone, ddd, email,"
				+ " endereco, cidade, bairro, uf, cargo, quantidade_hora, salario, foto)" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.conn = ConnectionFactory.getConnection();
		try 
		{
			this.pst = this.conn.prepareStatement(sql);
			this.pst.setString(1, model.getNome());
			this.pst.setString(2, model.getCpf());
			this.pst.setString(3, model.getCep());
			this.pst.setDate(4, new Date(model.getDataNascimento().getTime()));
			this.pst.setString(5, model.getTelefone());
			this.pst.setString(6, model.getDdd());
			this.pst.setString(7, model.getEmail());
			this.pst.setString(8, model.getEndereco());
			this.pst.setString(9, model.getCidade());
			this.pst.setString(10, model.getBairro());
			this.pst.setString(11, model.getUf());
			this.pst.setString(12, model.getCargo());
			this.pst.setInt(13, model.getQtdHoras());
			this.pst.setDouble(14, model.getSalario());
			this.pst.setBytes(15, model.getImagem());
			this.pst.executeUpdate();
			this.conn.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.pst.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public List<Funcionario> listarTodosFuncionarios()
	{
		this.conn = ConnectionFactory.getConnection();
		String sql = "select * from funcionarios where ativo = true";
		ArrayList<Funcionario> arrayFuncionario = new ArrayList<>();
		try {
			this.pst = this.conn.prepareStatement(sql);
			this.rs = this.pst.executeQuery();
			while(this.rs.next())
			{
				this.modelFuncionario = new Funcionario();
				this.modelFuncionario.setId(this.rs.getInt("id"));
				this.modelFuncionario.setNome(this.rs.getString("nome"));
				this.modelFuncionario.setCpf(this.utilitarios.putMascara(this.rs.getString("cpf"), "###.###.###-##"));
				this.modelFuncionario.setCep(this.utilitarios.putMascara(this.rs.getString("cep"), "#####-###"));
				this.modelFuncionario.setDataNascimento(this.rs.getDate("data_de_nascimento"));
				this.modelFuncionario.setTelefone(this.utilitarios.putMascara(""+this.rs.getString("telefone") 
				+ ""+this.rs.getString("ddd"), "(##) #####-####"));
				this.modelFuncionario.setEmail(this.rs.getString("email"));
				this.modelFuncionario.setEndereco(this.rs.getString("endereco"));
				this.modelFuncionario.setCidade(this.rs.getString("cidade"));
				this.modelFuncionario.setBairro(this.rs.getString("bairro"));
				this.modelFuncionario.setUf(this.rs.getString("uf"));
				this.modelFuncionario.setCargo(this.rs.getString("cargo"));
				this.modelFuncionario.setQtdHoras(this.rs.getInt("quantidade_hora"));
				this.modelFuncionario.setSalario(this.rs.getDouble("salario"));
				this.modelFuncionario.setImagem(this.rs.getBytes("foto"));
				
				arrayFuncionario.add(this.modelFuncionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pst.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return arrayFuncionario;
	}
	
	public Boolean editarFuncionario(Funcionario funcionario)
	{
		this.conn = ConnectionFactory.getConnection();
		String sql = "update funcionarios set nome = ?, cpf = ?, cep = ?, data_de_nascimento = ?, telefone = ?, ddd = ?, email = ?, "
				+ "endereco = ?, cidade = ?, bairro = ?, uf = ?, cargo = ?, quantidade_hora = ?, salario = ?"
				+ "where id = ?";
		try {
			this.pst = this.conn.prepareStatement(sql);
			this.pst.setString(1, funcionario.getNome());
			this.pst.setString(2, funcionario.getCpf());
			this.pst.setString(3, funcionario.getCep());
			this.pst.setDate(4, new Date(funcionario.getDataNascimento().getTime()));
			this.pst.setString(5, funcionario.getTelefone());
			this.pst.setString(6, funcionario.getDdd());
			this.pst.setString(7, funcionario.getEmail());
			this.pst.setString(8, funcionario.getEndereco());
			this.pst.setString(9, funcionario.getCidade());
			this.pst.setString(10, funcionario.getBairro());
			this.pst.setString(11, funcionario.getUf());
			this.pst.setString(12, funcionario.getCargo());
			this.pst.setInt(13, funcionario.getQtdHoras());
			this.pst.setDouble(14, funcionario.getSalario());
			this.pst.setInt(15, funcionario.getId());
			this.pst.executeUpdate();
			this.conn.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.pst.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public Boolean deletarFuncionario(int idFuncionario)
	{
		this.conn = ConnectionFactory.getConnection();
		String sql = "update funcionarios set ativo = ? where id = ?";
		try {
			this.pst = this.conn.prepareStatement(sql);
			this.pst.setBoolean(1, false);
			this.pst.setInt(2, idFuncionario);
			this.pst.executeUpdate();
			this.conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.pst.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
