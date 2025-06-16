package model.entities;

public enum StatusTarefas {
	
	PENDENTE,
	CONCLUIDO,
	CANCELADO;
	
	
	  public static StatusTarefas getStatusTafas(String statustarefas){
          for(StatusTarefas st:StatusTarefas.values()){
              if(st.toString().equals(statustarefas.toUpperCase())){
                  return st;
              }
          }
          return null;
      }

}
