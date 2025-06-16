package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarTarefa(Tarefas obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTarefa(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarStatus(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tarefas buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
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

	
	
	
		
	
	@Override
	public List<Tarefas> buscarPorUsuario(Usuarios user) {
		// TODO Auto-generated method stub
		return null;
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

	




		
		
		


