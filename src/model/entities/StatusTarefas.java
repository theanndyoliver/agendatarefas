package model.entities;

public enum StatusTarefas {
	
	PENDENTE,
	CONCLUIDO,
	CANCELADO;
	
	
	  public static StatusTarefas getStatusTarefas(String statustarefas){
          for(StatusTarefas st:StatusTarefas.values()){
              if(st.toString().equals(statustarefas.toUpperCase())){
                  return st;
              }
          }
          return null;
      }

}
