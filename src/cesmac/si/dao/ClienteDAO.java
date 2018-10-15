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
		
		String sql = "INSERT INTO public.usuarios(nome, senha, cpf, data_nascimento, cep, telefone, ddd, email, endereco, bairro, complemento, cidade, uf) " + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getCpf());
			java.sql.Date birthDay = new java.sql.Date(usuario.getDataNascimento().getTime());
			ps.setDate(4, birthDay);
			ps.setString(5, usuario.getCep());
			ps.setString(6, usuario.getTelefone());
			ps.setString(7, usuario.getDdd());
			ps.setString(8, usuario.getEmail());
			ps.setString(9, usuario.getEndereco());
			ps.setString(10, usuario.getBairro());
			ps.setString(11, usuario.getComplemento());
			ps.setString(12, usuario.getCidade());
			ps.setString(13, usuario.getUf());
			ps.executeUpdate();
			con.commit();
			cadastrou = true;
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
	
	public Boolean editarUsuario(Pessoa usuario) {
		
		String sql = "UPDATE public.usuarios SET  nome = ? , senha = ?, cpf = ?, data_nascimento = ?, cep = ?, telefone = ?, ddd = ?, email = ?, endereco = ?, bairro = ?, complemento = ?," + 
				" cidade = ?, uf = ? WHERE id = ?";
		con = ConnectionFactory.getConnection();
		Boolean usuarioEditado = false;
		try {
			ps = con.prepareStatement(sql);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getCpf());
			java.sql.Date birthDay = new java.sql.Date(usuario.getDataNascimento().getTime());
			ps.setDate(4, birthDay);
			ps.setString(5, usuario.getCep());
			ps.setString(6, usuario.getTelefone());
			ps.setString(7, usuario.getDdd());
			ps.setString(8, usuario.getEmail());
			ps.setString(9, usuario.getEndereco());
			ps.setString(10, usuario.getBairro());
			ps.setString(11, usuario.getComplemento());
			ps.setString(12, usuario.getCidade());
			ps.setString(13, usuario.getUf());
			ps.setInt(14, usuario.getId());
			ps.executeUpdate();
			con.commit();
			con.close();
			usuarioEditado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return usuarioEditado;
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
		
		List<Pessoa> listaUsuariosAtivos = new ArrayList<>();
		
		String sql = "SELECT id, nome, senha, cpf, data_nascimento, cep, telefone, ddd, email, endereco, bairro, complemento, cidade, uf" + 
				"	FROM public.usuarios WHERE ativo = true";
		
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pessoa usuario = new Pessoa();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setDataNascimento(rs.getDate("data_nascimento"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setDdd(rs.getString("ddd"));
				usuario.setEmail(rs.getString("email"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setComplemento(rs.getString("complemento"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setUf(rs.getString("uf"));
				
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
