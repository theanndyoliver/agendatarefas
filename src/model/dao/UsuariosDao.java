package model.dao;

import java.util.List;

import model.entities.Usuarios;

public interface UsuariosDao {
	
	void inserirUsuario(Usuarios user);
	void atualizarUsuario(Usuarios user);
	void deletarUsuario(Integer id);
	Usuarios buscarPorId(Integer id);
	List<Usuarios> mostrarUsuarios();
	

}
