package cesmac.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Usuario;

public class LoginDAO {
	private Usuario model = new Usuario();
	private PreparedStatement pst;
	private ResultSet rs;
	
	public Boolean autenticarLogin(Usuario modelLogin)
	{
		String sql = "select * from usuarios  where nome = ? and senha = ? limit 1";
		Connection conn = ConnectionFactory.getConnection();
		try {
			this.pst = conn.prepareStatement(sql);
			pst.setString(1, modelLogin.getNome());
			pst.setString(2, modelLogin.getSenha());
			this.rs = pst.executeQuery();
			while(rs.next())
			{
				this.model.setId(rs.getInt("id"));
				this.model.setNome(rs.getString("nome"));
				this.model.setSenha(rs.getString("senha"));
				
				if(modelLogin.getNome().equals(this.model.getNome()) && modelLogin.getSenha().equals(this.model.getSenha()))
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
