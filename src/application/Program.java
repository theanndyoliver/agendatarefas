package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.TarefasDao;
import model.entities.StatusTarefas;
import model.entities.Tarefas;
import model.entities.Usuarios;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		
		TarefasDao tf = DaoFactory.createTarefasDao();
		
		
		
		System.out.println("-=== Mostrar todas as tarefas ===-");
		List<Tarefas> list = tf.buscarTodas();
		for(Tarefas x : list) {
			System.out.println(x);
		}
		
		
		System.out.println();
		System.out.println("-=== Deletar uma tarefa por Id ===-");
		tf.deleteTarefa(7);
		
		
		System.out.println();
		System.out.println("-=== Buscar uma tarefa por Id ===-");
		System.out.println(tf.buscarPorId(2));
		
		
	
		System.out.println();
		System.out.println("-=== Atualizar status de uma tarefa por Id ===-");
		Tarefas tft = new Tarefas(12,StatusTarefas.CONCLUIDO);
		tf.atualizarStatus(tft);
		
		System.out.println();
		
		String dt ="2025-06-29";
		System.out.println("-=== Inserir nova tarefa  ===-");
		Usuarios user = new Usuarios(2,null);
		Tarefas xbbb = new Tarefas(null,"Vistoriar os buracos das ruas","OBS: Fotografar ou filmar.",sdf.parse(dt),StatusTarefas.PENDENTE,user);
		tf.inserirTarefa(xbbb);
		
		System.out.println();
		System.out.println("-=== Atualizar Tarefa  ===-");
		Usuarios userxbb = new Usuarios(1,null);
		Tarefas xblll = new Tarefas(13,"Varrer","Ruas do Bairro",new Date(),StatusTarefas.CONCLUIDO,userxbb);
		tf.atualizarTarefa(xblll);
		
	

	}
}

