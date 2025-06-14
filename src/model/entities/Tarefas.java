package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Tarefas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String titulo;
	private String descricao;
	private Date datah;
	private StatusTarefas status ;
	private Usuarios usuarios;
	
	
	
	
	public Tarefas() {
		
	}


	public Tarefas(Integer id, String titulo, String descricao, Date datah,StatusTarefas status,Usuarios usuarios) {
		Id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.datah = datah;
		this.status = StatusTarefas.PENDENTE;
		this.usuarios = usuarios;
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDatah() {
		return datah;
	}


	public void setDatah(Date datah) {
		this.datah = datah;
	}
	
	
	public Usuarios getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}


	public StatusTarefas getStatus() {
		return status;
	}


	public void setStatus(StatusTarefas status) {
		this.status = status;
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
		Tarefas other = (Tarefas) obj;
		return Objects.equals(Id, other.Id);
	}


	@Override
	public String toString() {
		return "Tarefas [Id=" + Id + ", titulo=" + titulo + ", descricao=" + descricao + ", datah=" + datah
				+ ", status=" + status + ", usuarios=" + usuarios + "]";
	}




		

	
	






}
