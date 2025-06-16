package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TarefasDao;
import model.entities.Tarefas;

public class Program {

	public static void main(String[] args) {
		
		TarefasDao tf = DaoFactory.createTarefasDao();
		
		List<Tarefas> list = tf.buscarTodas();
		
		for(Tarefas x : list) {
			System.out.println(x);
		}
		
		
	

	}
}
