package cesmac.si.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cesmac.si.connection.ConnectionFactory;


public class FornecedorDAO {
	
	public Boolean cadastrarFornecedor() {
		boolean cadastrou = false;
		
		String sql = "INSERT INTO fornecedores (nome, cnpj) VALUES (?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeQuery();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return cadastrou;
	}
}
