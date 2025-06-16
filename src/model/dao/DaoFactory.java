package model.dao;

import db.DB;
import model.dao.impl.TarefasDaoJDBC;
import model.entities.Tarefas;

public class DaoFactory {
		
	public static TarefasDao createTarefasDao() {
		return new TarefasDaoJDBC(DB.getConnection());
		
	}

}
