package cesmac.si.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Fornecedor;


public class FornecedorDAO {
	
	public Boolean cadastrarFornecedor() {
		boolean cadastrou = false;
		
		String sql = "INSERT INTO fornecedores (nome, cnpj) VALUES (?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
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
	
	public Fornecedor editarFornecedor(Fornecedor fornecedor) {
		
		String sql = "UPDATE fornecedores SET nome = ?, cnpj = ? WHERE id = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getCnpj());
			ps.setInt(3, fornecedor.getId());
			ps.executeUpdate();
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
		
		return fornecedor;
	}
	
	
	
	public Fornecedor listarFornecedor(Integer idFornecedor) {
		
		Fornecedor fornecedor = new Fornecedor();
		String sql = "SELECT id, nome, cnpj FROM fornecedores WHERE id = ? AND ativo = true";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
			}

			ps.setInt(1, fornecedor.getId());
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
		
		return fornecedor;
	}
	
	
	public List<Fornecedor> listarFornecedores() {
		
		Fornecedor fornecedor = new Fornecedor();
		List<Fornecedor> listaFornecedores = new ArrayList<>();
		
		String sql = "SELECT id, nome, cnpj FROM fornecedores AND ativo = true";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				
				listaFornecedores.add(fornecedor);
			}
			
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
		
		return listaFornecedores;
	}
	
	
	public boolean excluirFornecedores(Integer idFornecedor) {
		
		boolean excluido = false;
		
		String sql = "UPDATE fornecedores SET ativo = false WHERE id = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setInt(1, idFornecedor);
			ps.executeUpdate();
			
			con.commit();
			excluido = true;
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
		
		return excluido;
	}
}
