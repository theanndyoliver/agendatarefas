package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.TarefasDao;
import model.entities.StatusTarefas;
import model.entities.Tarefas;
import model.entities.Usuarios;

public class TarefasDaoJDBC implements TarefasDao {
	
	private Connection conn ;
	
	
	public TarefasDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	

	@Override
	public void inserirTarefa(Tarefas obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO tarefas (titulo,descricao,data,status,idUser) "
					+"values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getTitulo());
			st.setString(2, obj.getDescricao());
			st.setDate(3, new java.sql.Date(obj.getDatah().getTime()));
			st.setString(4, obj.getStatus().toString());
			st.setInt(5, obj.getUsuarios().getId());
			
			int arrows = st.executeUpdate();
			
			if(arrows > 0) {
				rs=st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					 obj.setId(id);
					 System.out.println("Tarefa Id:" +id +" inserida no banco de dados!");
				
			}
			else {
				throw new DbException("Nenhuma linha afetada!");
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
	public void atualizarTarefa(Tarefas obj) {
		PreparedStatement st = null;
		
		
		try {
			st= conn.prepareStatement("UPDATE tarefas "
					+"SET titulo = ? ,descricao = ?,data = ?,status = ?,idUser= ? "
				  +"WHERE id = ?",Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1, obj.getTitulo());
			st.setString(2, obj.getDescricao());
			st.setDate(3, new java.sql.Date(obj.getDatah().getTime()));
			st.setString(4, obj.getStatus().toString());
			st.setInt(5, obj.getUsuarios().getId());
			st.setInt(6, obj.getId());
			
			int arrows = st.executeUpdate();
			
			if(arrows > 0) {
				System.out.println("Tarefa atualizada com sucesso!");
			}
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.ClosePrepared(st);
		}
		
	}

	@Override
	public void deleteTarefa(Integer id) {
		PreparedStatement st = null;
		
		
		try {
			st = conn.prepareStatement("DELETE FROM tarefas "
					+"WHERE id = ? "
					+"LIMIT 1;");
			
			st.setInt(1, id);
			
			int arrows = st.executeUpdate();
			
			if(arrows == 0) {
				System.out.println("Id da tarefa não encontrado!");
			}else {
				System.out.println("Tarefa Id: "+id +" | deletada com sucesso!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
		}
		
		
	}

	@Override
	public void atualizarStatus(Tarefas obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
	
		
		
		try {
			st = conn.prepareStatement("UPDATE tarefas "
					+"SET status = ?"
					+"WHERE id = ?",Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,obj.getStatus().toString());
			st.setInt(2, obj.getId());
			
			 int arrows =st.executeUpdate();
			 
			 if(arrows > 0) {
				 
				 rs=st.getGeneratedKeys();
				 System.out.println("O status da tarefa foi atualizado com sucesso!");
				 if(rs.next()) {
					 int id = rs.getInt(1);
					

						 
					 }
						 
					 }
				
					 
				
				 
			
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}finally {
			DB.ClosePrepared(st);
			DB.CloseResulSet(rs);
			
		}
		
	}

	@Override
	public Tarefas buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT tarefas.*,usuarios.nome from tarefas INNER JOIN usuarios "
					+ "ON tarefas.idUser = usuarios.id "
					+ "WHERE tarefas.id= ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			
			if(rs.next()) {
				Usuarios user = instantiateUsuarios(rs);
				Tarefas tarefas = instantiateTarefas(rs,user);
			
				return tarefas;
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
	public List<Tarefas> buscarTodas() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT tarefas.*,usuarios.nome FROM tarefas "
					+ "INNER JOIN usuarios "
					+ "ON tarefas.idUser = usuarios.id "
					+ "ORDER by nome");
			
			rs = st.executeQuery();
			
			List<Tarefas> list = new ArrayList<>();
			Map<Integer,Usuarios> map = new HashMap<>();
			
			while(rs.next()) {
				Usuarios user = map.get(rs.getInt("idUser"));
				
				if(user == null) {
					
					user = instantiateUsuarios(rs);
					
					
					map.put(rs.getInt("idUser"), user);
				}
				
				Tarefas tf = instantiateTarefas(rs,user);
				
				list.add(tf);
				
					
				}
		
				return list;
				
			
			}catch(SQLException e) {
				throw new DbException (e.getMessage());
			}finally {
				DB.ClosePrepared(st);
				DB.CloseResulSet(rs);
	}
	}

	public Usuarios instantiateUsuarios(ResultSet rs) throws SQLException {
		Usuarios userx = new  Usuarios();
		userx.setId(rs.getInt("idUser"));
		userx.setNome(rs.getString("nome"));
		
		return userx;
		
	}
	
	public Tarefas instantiateTarefas(ResultSet rs,Usuarios user) throws SQLException {
		Tarefas tf = new Tarefas();
		StatusTarefas status =  StatusTarefas.valueOf(rs.getString("status"));
		
		tf.setId(rs.getInt("id"));
		tf.setTitulo(rs.getString("titulo"));
		tf.setDescricao(rs.getString("descricao"));
		tf.setDatah(rs.getDate("data"));
		tf.setStatus(status);
		tf.setUsuarios(user);
		
		return tf;
	}
}

	




		
		
		


