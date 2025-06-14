package model.dao;

import model.entities.Usuarios;

public interface UsuariosDao {
	
	void inserirUsuario(Usuarios user);
	void atualizarUsuario(Usuarios user);
	void deletarUsuario(Integer id);
	Usuarios buscarPorId(Integer id);
	

}
