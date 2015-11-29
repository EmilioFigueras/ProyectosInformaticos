import java.util.*;

public class GruposCompatibles{

	private ArrayList<Asignatura> seleccion;
	private Asignatura asignatura;
	private int asig, gru, gru2, dia;
	private boolean encontrado,compatible, ocupado;

	public GruposCompatibles(ArrayList<Asignatura> seleccion, Asignatura asignatura){
		this.seleccion = new ArrayList<Asignatura>(seleccion);
		this.asignatura = asignatura;
		asig = -1;
		compatible = true;
		ocupado = false;
		//gru = 237;
	}

	public int getAsig(){return asig;}

	public int getGru(){return gru;}

	public int getGru2(){return gru2;}

	public static double fila_a_hora(int n){
 		//n+8.5-n*0.5
 		return (double)((n+8.5)-(n*0.5));
 	}
 	public static int hora_a_fila(double hora){
 		return (int)(2*(hora-8.5));
 	}

	private void buscarGrupo(ArrayList<? extends Grupo> gruposConCoincidencia, ArrayList<? extends Grupo> grupos, String[] coincidente, int k, String[][] horario_previo, int j)
	{
		//gr_coin = seleccion.get(asig).grupos_teoria(); //Numeros de grupos de la asignatura que nos choca

		//Ahora buscamos el grupo concreto que coincide
		if(gruposConCoincidencia.size()>1 || grupos.size()>1){ //Si tiene mas de un grupo
			for(int q=0; q<gruposConCoincidencia.size(); q++){
				//Entreamos en asignaturas seleccionadas->Asignatura coincidente->ArrayList de Teoria->Y recorremos sus nombres
				if((gruposConCoincidencia.get(q).get_nombre()).compareTo(coincidente[1]) == 0){
					gru = q;
				}
			}//fin for q
			if(gruposConCoincidencia.size()>1) //Si el grupo que esta ya puesto tiene otra opcion, lo sobreescribimos
				horario_previo[k][dia] = asignatura.get_nombre()+" "+grupos.get(j).get_nombre();
			else{ //Si no tiene otra opcion, volvemos a poner gru como que no concuerda
				gru = 237;
				gru2 = j; //Indicamos que en gru2 el grupo que sobra de la asignatura que estamos analizando
			}

		}else //Si no tiene mas de un grupo, pues entonces la asignatura "i" no entra.
			if(gruposConCoincidencia.size()==1)
				compatible=false;

		ocupado = true;
		//Ocupado = true porque si puede sobreescribirse la asignatura ya lo habra hecho anteriormente, y si no puede sobreescribirse, 
		//pues significa que esta asignatura no entra, por lo que no se agregara en la matriz final.

	}

	public boolean sonCompatibles(String[][] horario_previo, String[][] horario_resultado, ArrayList<? extends Grupo> grupos){
		
		gru = 237;
		gru2 = 237;

		for(int j=0; j<grupos.size(); j++){ //Bucle para recorrer los grupos de teoria dentro de la asignatura seleccionada
		 				ocupado=false;
		 				//Recorremos el horario de este grupo
		 				dia = grupos.get(j).get_dia(); //Dia del grupo seleccionado

		 				//Bucle para recorrer las horas de este grupo
		 				for(int k=hora_a_fila(grupos.get(j).get_hora_inicio()); k<hora_a_fila(grupos.get(j).get_hora_fin()); k++){
		 					if(horario_resultado[k][dia] != null){ //Si hay una asignatura en esta posicion
		 						String[] coincidente = horario_resultado[k][dia].split(" "); //coincidente[0] = nombre y coincidente[1] = grupo
		 						encontrado = false;
		 						for(int q=0; q<seleccion.size() && encontrado==false; q++){ //Bucle para encontrar la asignaura que nos choca
		 							if((seleccion.get(q).get_nombre()).compareTo(coincidente[0]) == 0){
		 								encontrado=true;
		 								asig=q; //Posicion en el ArrayList seleccion de la asignatura que nos choca
		 							}
		 						}//fin for q

		 						if(grupos.get(0) instanceof Teoria){
		 							buscarGrupo(seleccion.get(asig).get_teoria(), grupos, coincidente, k, horario_previo, j);
		 						}
		 						else if(grupos.get(0) instanceof Practica){
		 							buscarGrupo(seleccion.get(asig).get_practica(), grupos, coincidente, k, horario_previo, j);
		 						}
		 						else if(grupos.get(0) instanceof Seminario){
		 							buscarGrupo(seleccion.get(asig).get_seminario(), grupos, coincidente, k, horario_previo, j);
		 						}
															
		 					}//fin if horario_resultado != null
		 				}//fin for k

		 				//Si hay espacio libre pues la agregamos a la matriz previa, a la espera de que haya espacio tambien para el resto de horas de este grupo
		 				if(ocupado==false){
		 					for(int k=hora_a_fila(grupos.get(j).get_hora_inicio()); k<hora_a_fila(grupos.get(j).get_hora_fin()); k++){
		 						horario_previo[k][dia]=asignatura.get_nombre()+" "+grupos.get(j).get_nombre();
		 					}
		 				}
		}//fin for j}
	return compatible;
	}
}