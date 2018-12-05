package cesmac.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Pessoa;

public class LoginDAO {
	private PreparedStatement pst;
	private ResultSet rs;
	
	public Pessoa autenticarLogin(Pessoa modelLogin)
	{
		String sql = "select * from usuarios u join permissao p on u.id = p.id_usuario where email = ? and senha = ? limit 1";
		String sqlCliente = "select * from usuarios where email = ? and senha = ? limit 1";  
		Connection conn = ConnectionFactory.getConnection();
		Pessoa usuario = new Pessoa();
		try {
			this.pst = conn.prepareStatement(sql);
			pst.setString(1, modelLogin.getEmail());
			pst.setString(2, modelLogin.getSenha());
			this.rs = pst.executeQuery();
			if(rs.next()) {
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setRestrito(true);
			}
			
			else {
				this.pst = conn.prepareStatement(sqlCliente);
				pst.setString(1, modelLogin.getEmail());
				pst.setString(2, modelLogin.getSenha());
				this.rs = pst.executeQuery();
				if(rs.next()) {
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setRestrito(false);
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
		
		
		return usuario;
	}
}
