package cesmac.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Pessoa;

public class LoginDAO {
	private Pessoa model = new Pessoa();
	private PreparedStatement pst;
	private ResultSet rs;
	
	public Boolean autenticarLogin(Pessoa modelLogin)
	{
		String sql = "select * from usuarios  where nome = ? and senha = ? limit 1";
		Connection conn = ConnectionFactory.getConnection();
		try {
			this.pst = conn.prepareStatement(sql);
			pst.setString(1, modelLogin.getEmail());
			pst.setString(2, modelLogin.getSenha());
			this.rs = pst.executeQuery();
			while(rs.next())
			{
				this.model.setId(rs.getInt("id"));
				this.model.setEmail(rs.getString("nome"));
				this.model.setSenha(rs.getString("senha"));
				
				if(modelLogin.getEmail().equals(this.model.getEmail()) && modelLogin.getSenha().equals(this.model.getSenha()))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
}
