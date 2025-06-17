package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UsuariosDao;
import model.entities.Usuarios;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		UsuariosDao us = DaoFactory.createUsuariosDao();
		
		
		System.out.println("-=== Mostrar todos os Usuarios ===-");
		List<Usuarios> list = us.mostrarUsuarios();
		for(Usuarios x: list) {
			System.out.println(x);
		}
		
		System.out.println();
		System.out.println("-=== Inserir novo usuario ===-");
		Usuarios inserir = new Usuarios(null,"Juba");
		us.inserirUsuario(inserir);
		
		System.out.println();
		System.out.println("-=== Atualizar usuario por Id ===-");
		Usuarios updatex = new Usuarios(5,"Jaspion");
		us.atualizarUsuario(updatex);
		
		
		System.out.println();
		System.out.println("-=== Buscar usuario por Id ===-");
		System.out.println(us.buscarPorId(2));
		

	}

}
