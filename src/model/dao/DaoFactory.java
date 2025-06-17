package model.dao;

import db.DB;
import model.dao.impl.TarefasDaoJDBC;
import model.dao.impl.UsuariosDaoJDBC;

public class DaoFactory {
		
	public static TarefasDao createTarefasDao() {
		return new TarefasDaoJDBC(DB.getConnection());
		
	}
	
	public static UsuariosDao createUsuariosDao() {
		return new UsuariosDaoJDBC(DB.getConnection());
	}

}
