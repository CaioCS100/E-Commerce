package cesmac.si.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cesmac.si.connection.ConnectionFactory;
import cesmac.si.model.Pessoa;

public class ClienteDAO {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	
	public Boolean cadastrarUsuario(Pessoa usuario) {
		boolean cadastrou = false;
		
		String sql = "INSERT INTO public.usuarios(nome, senha, cpf, data_nascimento, cep, telefone, ddd, email, endereco, bairro, complemento) " + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getCpf());
			ps.setDate(4, (Date) usuario.getDataNascimento());
			ps.setString(5, usuario.getCep());
			ps.setString(6, usuario.getTelefone());
			ps.setString(7, usuario.getDdd());
			ps.setString(8, usuario.getEmail());
			ps.setString(9, usuario.getEndereco());
			ps.setString(10, usuario.getBairro());
			ps.setString(11, usuario.getComplemento());
			ps.executeUpdate();
			con.commit();
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
	
	public Pessoa editarUsuario(Pessoa usuario) {
		
		String sql = "UPDATE public.usuarios SET  nome = ? , senha = ?, cpf = ?, data_nascimento = ?, cep = ?, telefone = ?, ddd = ?, email = ?, endereco = ?, bairro = ?, complemento = ?" + 
				"	WHERE id = ?";
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getCpf());
			ps.setDate(4, (Date) usuario.getDataNascimento());
			ps.setString(5, usuario.getCep());
			ps.setString(6, usuario.getTelefone());
			ps.setString(7, usuario.getDdd());
			ps.setString(8, usuario.getEmail());
			ps.setString(9, usuario.getEndereco());
			ps.setString(10, usuario.getBairro());
			ps.setString(11, usuario.getComplemento());
			ps.setInt(12, usuario.getId());
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
		
		return usuario;
	}
	
	
	
	public Pessoa listarUsuarioAtivo(int idpessoa) {
		
		Pessoa usuario = new Pessoa();
		String sql = "SELECT id, nome, cpf, data_nascimento, cep, telefone, ddd, email, endereco, bairro, complemento" + 
				"	FROM public.usuarios WHERE id = ? AND ativo = true";
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			
			if(rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));;
				usuario.setDataNascimento(rs.getDate("data_nascimento"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setDdd(rs.getString("ddd"));
				usuario.setEmail(rs.getString("email"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setComplemento(rs.getString("complemento"));
			}

			ps.setInt(1, idpessoa);
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
		
		return usuario;
	}
	
	
	public List<Pessoa> listarUsuarios() {
		
		Pessoa usuario = new Pessoa();
		List<Pessoa> listaUsuariosAtivos = new ArrayList<>();
		
		String sql = "SELECT id, nome, cpf, data_nascimento, cep, telefone, ddd, email, endereco, bairro, complemento" + 
				"	FROM public.usuarios WHERE ativo = true";
		
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setDataNascimento(rs.getDate("data_nascimento"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setDdd(rs.getString("ddd"));
				usuario.setEmail(rs.getString("email"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setComplemento(rs.getString("complemento"));
				
				listaUsuariosAtivos.add(usuario);
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
		
		return listaUsuariosAtivos;
	}
	
	
	public boolean excluirUsuario(int idUsuario) {
		
		boolean excluido = false;
		
		String sql = "UPDATE public.usuarios SET ativo = false WHERE id = ?";
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
		
			ps.setInt(1, idUsuario);
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
