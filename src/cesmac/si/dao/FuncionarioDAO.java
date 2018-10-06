package cesmac.si.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Funcionario;

public class FuncionarioDAO {
	private PreparedStatement pst;
	private Connection conn;
	private ResultSet rs;
	private Funcionario modelFuncionario;

	public Boolean cadastrarFuncionario(Funcionario model) {
		String sql = "insert into funcionarios(nome, cpf, cep, data_de_nascimento, telefone, ddd, email,"
				+ " endereco, cidade, uf, cargo, quantidade_hora, salario)" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			this.pst.setString(10, model.getUf());
			this.pst.setString(11, model.getCargo());
			this.pst.setInt(12, model.getQtdHoras());
			this.pst.setDouble(13, model.getSalario());
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
		String sql = "select * from funcionarios";
		ArrayList<Funcionario> arrayFuncionario = new ArrayList<>();
		try {
			this.pst = this.conn.prepareStatement(sql);
			this.rs = this.pst.executeQuery();
			while(this.rs.next())
			{
				this.modelFuncionario = new Funcionario();
				this.modelFuncionario.setNome(this.rs.getString("nome"));
				this.modelFuncionario.setCpf(this.rs.getString("cpf"));
				this.modelFuncionario.setCep(this.rs.getString("cep"));
				this.modelFuncionario.setDataNascimento(this.rs.getDate("data_de_nascimento"));
				this.modelFuncionario.setTelefone(this.rs.getString("telefone"));
				this.modelFuncionario.setDdd(this.rs.getString("ddd"));
				this.modelFuncionario.setEmail(this.rs.getString("email"));
				this.modelFuncionario.setEndereco(this.rs.getString("endereco"));
				this.modelFuncionario.setCidade(this.rs.getString("cidade"));
				this.modelFuncionario.setUf(this.rs.getString("uf"));
				this.modelFuncionario.setCargo(this.rs.getString("cargo"));
				this.modelFuncionario.setQtdHoras(this.rs.getInt("quantidade_hora"));
				this.modelFuncionario.setSalario(this.rs.getDouble("salario"));
				
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
	
}
