package cesmac.si.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Funcionario;

public class FuncionarioDAO {
	
	public void cadastrarFuncionario(Funcionario model)
	{
		String sql = "insert into funcionarios(nome, cpf, cep, data_de_nascimento, telefone, ddd, email,"
				+ " endereco, cidade, uf, cargo, quantidade_hora, salario)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, model.getNome());
			pst.setString(2, model.getCpf());
			pst.setString(3, model.getCep());
			pst.setDate(4, new Date(model.getDataNascimento().getTime()));
			pst.setString(5, model.getTelefone());
			pst.setString(6, model.getDdd());
			pst.setString(7, model.getEmail());
			pst.setString(8, model.getEndereco());
			pst.setString(9, model.getCidade());
			pst.setString(10, model.getUf());
			pst.setString(11, model.getCargo());
			pst.setInt(12, model.getQtdHoras());
			pst.setDouble(13, model.getSalario());
			pst.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
