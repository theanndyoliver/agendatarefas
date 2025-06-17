package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String Nome;
	
	
	public Usuarios() {
		
	}
	
	public Usuarios(Integer id) {
		this.Id = id;
	}


	public Usuarios(Integer id, String nome) {
		this.Id = id;
		Nome = nome;
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getNome() {
		return Nome;
	}


	public void setNome(String nome) {
		Nome = nome;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(Id, other.Id);
	}


	@Override
	public String toString() {
		return "Usuarios [Id=" + Id + ", Nome=" + Nome + "]";
	}
}