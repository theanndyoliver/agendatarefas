package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuariosDao;
import model.entities.Usuarios;

public class UsuariosDaoJDBC implements UsuariosDao {
	
	private Connection conn;
	
	
	
	public UsuariosDaoJDBC(Connection conn) {
		this.conn= conn;
	}

	@Override
	public void inserirUsuario(Usuarios user) {
		PreparedStatement st = null ;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO usuarios (nome) values (?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, user.getNome());
			
			int arrows = st.executeUpdate();
			
			if(arrows > 0) {
				rs = st.getGeneratedKeys();
				System.out.println("Novo usuario inserido ao banco de dados!");
				if(rs.next()) {
					int id = rs.getInt(1);
					user.setId(id);
				}
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
			DB.CloseResulSet(rs);
		}
	}
				
			
			
		

	@Override
	public void atualizarUsuario(Usuarios user) {
		PreparedStatement st = null;
		try {
			st=conn.prepareStatement("UPDATE usuarios SET nome = ? "
					+"WHERE id = ?");
			
			st.setString(1, user.getNome());
			st.setInt(2, user.getId());
			
			int arrows = st.executeUpdate();
			if(arrows > 0) {
				System.out.println("Dados do usuario atualizados com sucesso.");
			}
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
		}
		
	}

	@Override
	public void deletarUsuario(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("delete from usuarios where usuarios.id= ? "
					+ "LIMIT 1;");
			
			st.setInt(1, id);
			
			int arrows = st.executeUpdate();
			if(arrows > 0) {
				System.out.println("Usuario apagado do banco de dados com sucesso!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
		}
		
	}

	@Override
	public Usuarios buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st=conn.prepareStatement("SELECT usuarios.id,usuarios.nome from usuarios where usuarios.id = ?");
			
			st.setInt(1, id);
			
			rs=st.executeQuery();
			
			if(rs.next()) {
				Usuarios user = new Usuarios();
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				return user;
				
				
			}
			return null;
			
				
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
			DB.CloseResulSet(rs);
		}
	}

	@Override
	public List<Usuarios> mostrarUsuarios() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st= conn.prepareStatement("SELECT * from usuarios");
			
			rs = st.executeQuery();
			
			List<Usuarios> user= new ArrayList<>();
			while(rs.next()) {
				Usuarios userxd = new Usuarios();
				userxd.setId(rs.getInt("id"));
				userxd.setNome(rs.getString("nome"));
				
				user.add(userxd);
				
				
			}
			return user;
		
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
			DB.CloseResulSet(rs);
		}
	}
	

}
