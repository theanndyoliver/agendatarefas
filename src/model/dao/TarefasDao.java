package model.dao;

import java.util.List;

import model.entities.Tarefas;
import model.entities.Usuarios;

public interface TarefasDao {
	
	void inserirTarefa(Tarefas obj);
	void atualizarTarefa(Tarefas obj);
	void deleteTarefa(Integer id);
	void atualizarStatus(Tarefas obj);
	Tarefas buscarPorId(Integer id);
	List<Tarefas> buscarTodas();
	

}
