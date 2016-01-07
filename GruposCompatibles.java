import java.util.*;

public class GruposCompatibles{

	private ArrayList<Asignatura> seleccion;
	private Asignatura asignatura;
	private int asig, dia;
	private boolean encontrado,compatible, ocupado;

	public GruposCompatibles(ArrayList<Asignatura> seleccion, Asignatura asignatura){
		this.seleccion = new ArrayList<Asignatura>(seleccion);
		this.asignatura = asignatura;
		asig = -1;
		compatible = true;
		ocupado = false;
	}

	public int getAsig(){return asig;}


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
		//System.out.println("Tamaño grupoconcoincidnecia: "+gruposConCoincidencia.size()); //Las que ya estan
		//System.out.println("Tamaño grupos: "+grupos.size()); //La que quieres meter
		int futuro = k+1;
		String asignatura_a_sobreescribir;
		boolean fin = false;
		if(grupos.size() < gruposConCoincidencia.size()){
			asignatura_a_sobreescribir = horario_previo[k][dia];
			horario_previo[k][dia] = asignatura.getNombre()+" "+grupos.get(j).getNombre();

			//Buscamos si hay restos de grupos ya eliminados
			if(k==(hora_a_fila(grupos.get(j).getHora_fin())-1)){
				while(futuro<25 && !fin){
					if(horario_previo[futuro][dia] == null)
						fin = true;
					else{
						if(asignatura_a_sobreescribir.compareTo(horario_previo[futuro][dia]) == 0){
							horario_previo[futuro][dia] = null;
						}
					}
					futuro++;
				}
			}
		}else
			if(gruposConCoincidencia.size()<=1 && grupos.size()<=1)
				compatible=false;


		ocupado = true;
		//Ocupado = true porque si puede sobreescribirse la asignatura ya lo habra hecho anteriormente, y si no puede sobreescribirse, 
		//pues significa que esta asignatura no entra, por lo que no se agregara en la matriz final.

	}

	/*
	grupos = asignatura que intentamos colocar
	todas_asig = asignaturas ya colocaddas
	*/
	public boolean sonCompatibles(String[][] horario_previo, String[][] horario_resultado, ArrayList<? extends Grupo> grupos, ArrayList<Asignatura> todas_asig){

		int tipoSub = 0; //1 = teoria, 2 = practica, 3 = seminario
		for(int j=0; j<grupos.size(); j++){ //Bucle para recorrer los grupos de teoria dentro de la asignatura seleccionada
		 				ocupado=false;
		 				//Recorremos el horario de este grupo
		 				dia = grupos.get(j).getDia(); //Dia del grupo seleccionado

		 				//Bucle para recorrer las horas de este grupo
		 				for(int k=hora_a_fila(grupos.get(j).getHora_inicio()); k<hora_a_fila(grupos.get(j).getHora_fin()); k++){
		 					if(horario_resultado[k][dia] != null){ //Si hay una asignatura en esta posicion
		 						String[] coincidente = horario_resultado[k][dia].split(" "); //coincidente[0] = nombre y coincidente[1] = grupo
		 						encontrado = false;
		 						for(int q=0; q<todas_asig.size() && encontrado==false; q++){ //Bucle para encontrar la asignatura que nos choca
		 							if((todas_asig.get(q).getNombre()).compareTo(coincidente[0]) == 0){
		 								encontrado=true;
		 								asig=q; //Posicion en el ArrayList seleccion de la asignatura que nos choca
		 							}
		 						}//fin for q

		 						tipoSub=0;
		 						encontrado=false;

		 						//Ahora buscamos el subgrupo al que pertenece la asignatura que nos choca
		 						for(int q=0; q<todas_asig.get(asig).getTeoria().size() && encontrado==false; q++){
		 							if((todas_asig.get(asig).getTeoria().get(q).getNombre()).compareTo(coincidente[1]) ==0){
		 								encontrado=true;
		 								tipoSub=1;
		 							}
		 						}
		 						for(int q=0; q<todas_asig.get(asig).getPractica().size() && encontrado==false; q++){
		 							if((todas_asig.get(asig).getPractica().get(q).getNombre()).compareTo(coincidente[1]) ==0){
		 								encontrado=true;
		 								tipoSub=2;
		 							}
		 						}
		 						for(int q=0; q<todas_asig.get(asig).getSeminario().size() && encontrado==false; q++){
		 							if((todas_asig.get(asig).getSeminario().get(q).getNombre()).compareTo(coincidente[1]) ==0){
		 								encontrado=true;
		 								tipoSub=3;
		 							}
		 						}


		 						if(tipoSub==1)
		 							buscarGrupo(todas_asig.get(asig).getTeoria(), grupos, coincidente, k, horario_previo, j);

		 						if(tipoSub==2)
		 							buscarGrupo(todas_asig.get(asig).getPractica(), grupos, coincidente, k, horario_previo, j);


		 						if(tipoSub==3)
		 							if(todas_asig.get(asig).getSeminario().size() !=0)
		 								buscarGrupo(todas_asig.get(asig).getSeminario(), grupos, coincidente, k, horario_previo, j);
															
		 					}//fin if horario_resultado != null
		 				}//fin for k

		 				//Si hay espacio libre pues la agregamos a la matriz previa, a la espera de que haya espacio tambien para el resto de horas de este grupo
		 				if(ocupado==false){
		 					for(int k=hora_a_fila(grupos.get(j).getHora_inicio()); k<hora_a_fila(grupos.get(j).getHora_fin()); k++){
		 						horario_previo[k][dia]=asignatura.getNombre()+" "+grupos.get(j).getNombre();
		 					}
		 				}
		}//fin for j}
	return compatible;
	}
}