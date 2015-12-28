 import java.util.*;



 public class App{

 	static GruposCompatibles gruposCompatibles;
 	static Scanner sc = new Scanner(System.in);
 	
 	//Vector con los nombre de los dias de la semana
 	static String weekDay[] = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};


 	//funcion que convierte una fila en una hora
 	//Recibe fila (de 0 a 25) y devuelve una hora
 	public static double fila_a_hora(int n){
 		//n+8.5-n*0.5
 		return (double)((n+8.5)-(n*0.5));
 	}
 	public static int hora_a_fila(double hora){
 		return (int)(2*(hora-8.5));
 	}

 	//Convierte un dia en valor de valor numero a un dia en String
 	public static String dia_a_string(int dia){
 		return weekDay[dia];
 	}

 	//Convierte nuestro valor numero de las hora a una hora en String
 	public static String hora_a_string(double hora){
 		int hora_int = (int) hora;
 		String hora_final;
 		if((hora-hora_int) == 0) //Si la hora es en punto
 			hora_final = hora_int+":00";
 		else //Si es y media
 			hora_final = hora_int+":30";
 		return hora_final;
 	}

 	public static void seleccionar_grupo(String nombre, String[][] horario_resultado, String tipo, int num_grupos, ArrayList<? extends Grupo> grupo){
 			int opc;

 			if(num_grupos > 1){
 				System.out.println("Seleccione el grupo de "+tipo+" que desee de la asignatura "+nombre);
 				for(int j=0; j<num_grupos; j++){
 					System.out.println(+(j+1)+".- "+dia_a_string(grupo.get(j).get_dia())+" de "+hora_a_string(grupo.get(j).get_hora_inicio())
 						+" horas a "+hora_a_string(grupo.get(j).get_hora_fin())+" horas.");
 				}
 				opc = sc.nextInt();
 				for(int j=0; j<num_grupos; j++)
 					if(j!=(opc-1))
 						for(int k=hora_a_fila(grupo.get(j).get_hora_inicio()); k<hora_a_fila(grupo.get(j).get_hora_fin()); k++)
 							horario_resultado[k][grupo.get(j).get_dia()] = null;
 			 				
 			}//fin if
 	}//fin funcion

 	public static void mostrar_horario(String[][] horario_resultado){
 		//Muestra de resultados
 		System.out.println();
 		for(int i=0; i<25; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}
 	}

 	/*Devuelve true si es factible y la asignatura se puede insertar, o false si no lo es y la asignatura se ha eliminado*/
 	/*Parametros:
 		seleccion = ArrayList con las asignaturas a comprobar la factibilidad
 		todas_asig = ArrayList con todas las asignaturas con las que la asignatura que se esta analizando podría chocar
 		horario_previo = horario previo
 		horario_resultado = horario final
 		i = nos indica la asignatura que se analizara del Array seleccion
 		tipo = nos indica si se ha llamado a esta funcion para insertar una asignatura (tipo==1) o solo para comprobar la factibilidad (tipo!=1)
 	*/
 	public static boolean factibilidad(ArrayList<Asignatura> seleccion, ArrayList<Asignatura> todas_asig, String[][] horario_previo, String[][] horario_resultado, int i, int tipo){
 		boolean entra_t, entra_s, entra_p; //Comprueba que de una asignatura hay sitio para, al menos, un grupo de teoria,seminario (si lo tuviese) y practica.
 		

 		//Pasamos el resultado obtenido hasta ahora a la matriz previa que sera la que vamos a ir modificando
 		//Las funciones para copiar matriz copian la posicion de memoria, por lo que hay que copiarla con bucle :=(
 		
 		

 		for(int j=0; j<25; j++)
 			for(int q=0; q<5; q++)
 				horario_previo[j][q]=horario_resultado[j][q];
 			
 		gruposCompatibles = new GruposCompatibles(seleccion,seleccion.get(i));
 		entra_t = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_teoria(), todas_asig);
 		entra_p = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_practica(), todas_asig);
 		if(seleccion.get(i).get_seminario().size() ==0)
 			entra_s=true;
 		else
 			entra_s = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_seminario(), todas_asig);


 		/*System.out.println(entra_t);
 		System.out.println(entra_p);
 		System.out.println(entra_s);*/
 		if(entra_t && entra_s && entra_p){ //Si al asignatura tiene espacio libre para la teoria, el seminario y la practica, pues la incorporamos.
 			
 			if(tipo!=1)
 				return true;
 			for(int j=0; j<25; j++)
 				for(int k=0; k<5; k++)
 					//if(horario_previo[j][k]!=null)
 						horario_resultado[j][k]=horario_previo[j][k]; //Lo copiamos
 			//mostrar_horario(horario_resultado);
 			seleccion.clear();
 			asig_finales(horario_resultado, seleccion);
	 		return true;
 		}else{ //fin if
 			//System.out.println("La asignatura "+seleccion.get(i).get_nombre()+" no es compatible con el resto.");
 			return false;
 		}
 	}

 	/*Devuelve un ArrayList con las asignaturas y subgrupos que estan en el horario final*/
 	public static void asig_finales(String[][] horario_resultado, ArrayList<Asignatura> sel_final){
 		//ArrayList<Asignatura> sel_final = new ArrayList<Asignatura>(); //Arraylist con los grupos que se meten finalmente
 		boolean seguir_buscando;
 		boolean primera_vez;
 		String ultimo_analizado = " ";
 		int posicion_sel_final=0; //Posicion en el arraylist sel_final de la asignatura que se esta analizando del arraylist sel_final
 		int posicion_todas_asig=0; //Posicion en el arraylist todas_asig de la asignatura que se esta agregando al arraylist sel_final
 		SimulationDB simulacion = new SimulationDB();
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>(simulacion.getDB());
 		for(int j=0; j<5; j++)
 			for(int i=0; i<25; i++){
 				if(horario_resultado[i][j] != null && horario_resultado[i][j].compareTo(ultimo_analizado)!=0){
 					ultimo_analizado=horario_resultado[i][j];
 					primera_vez=true;
 					
 					String[] asignatura = horario_resultado[i][j].split(" ");

 					seguir_buscando=true;
 					//Buscamos si tenemos la asignatura ya agregada
 					for(int k=0; k<sel_final.size() && seguir_buscando==true; k++){
 						if((sel_final.get(k).get_nombre()).compareTo(asignatura[0]) == 0){
 							posicion_sel_final=k;
 							seguir_buscando=false;
 							primera_vez=false;
 						}
 					}//fin for k
 					seguir_buscando=true;
 					//Si no tenemos la asignatura ya agregada, pues la agregamos
 					for(int k=0; k<todas_asig.size() && seguir_buscando==true; k++){
 						if((todas_asig.get(k).get_nombre()).compareTo(asignatura[0]) == 0){
 							seguir_buscando=false;
 							posicion_todas_asig=k;
 							if(primera_vez==true){
 								sel_final.add(new Asignatura(todas_asig.get(k)));
 								posicion_sel_final = sel_final.size()-1;
 							}
 								
 						}
 					}//fin for k
 					//Si es la primera vez que entra esa asignatura, limpiamos sus grupos
 					if(primera_vez){
 						sel_final.get(posicion_sel_final).get_teoria().clear();
 						sel_final.get(posicion_sel_final).get_practica().clear();
 						sel_final.get(posicion_sel_final).get_seminario().clear();
 					}
 					seguir_buscando=true;
 					//Ahora buscamos el subgrupo al que pertenece la asignatura
 					//Buscamos si ese subgrupo es de teoria
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).get_teoria().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).get_teoria().get(k).get_nombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).get_teoria().add(todas_asig.get(posicion_todas_asig).get_teoria().get(k));
 							seguir_buscando=false;
 						}
 					}
 					//Buscamos si ese subgrupo es de practica
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).get_practica().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).get_practica().get(k).get_nombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).get_practica().add(todas_asig.get(posicion_todas_asig).get_practica().get(k));
 							seguir_buscando=false;
 						}
 					}
 					//Buscamos si ese subgrupo es de seminario
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).get_seminario().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).get_seminario().get(k).get_nombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).get_seminario().add(todas_asig.get(posicion_todas_asig).get_seminario().get(k));
 							seguir_buscando=false;
 						}
 					}

 				}//fin if horario_resultado
 			}//fin for j

 			//return sel_final;


 	}

 	/*public static void actualizar_asig_disponibles(ArrayList<Asignatura> seleccion, ArrayList<Asignatura> asig_disponibles){
 		boolean eliminar;
 		for(int i=0; i<seleccion.size(); i++){
 			for(int j=0; j<asig_disponibles.size(); j++){
 				if((seleccion.get(i).get_nombre()).compareTo(asig_disponibles.get(j).get_nombre()) == 0){
 					//Teoria
 					//Si se ha eliminado algun grupo de teoria de las asignaturas seleccionada
 					if(seleccion.get(i).get_teoria().size() < asig_disponibles.get(j).get_teoria().size()){
 						//Buscamos cual es el grupo que falta
 						int k=0;
 						while(k<asig_disponibles.get(j).get_teoria().size()){
 							eliminar=true;
 							for(int q=0; q<seleccion.get(i).get_teoria().size(); q++){
 								if((asig_disponibles.get(j).get_teoria().get(k).get_nombre()).compareTo(seleccion.get(i).get_teoria().get(q).get_nombre()) == 0){
 									eliminar=false;
 								}
 							}
 							if(eliminar){
 								asig_disponibles.get(j).get_teoria().remove(k);
 							}else
 								k++;
 						}
 					}
 				}
 			}
 		}
 	}*/

 	public static void main(String[] args){
 		//OBTENEMOS ASIGNATURAS DE UNA BASE DE DATOS SIMULADA
 		SimulationDB simulacion1 = new SimulationDB();
 		SimulationDB simulacion2 = new SimulationDB();
 		ArrayList<Asignatura> asig_disponibles = new ArrayList<Asignatura>(simulacion1.getDB());
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>(simulacion2.getDB());
 		ArrayList<Asignatura> seleccion_post = new ArrayList<Asignatura>();

 		//Hay 25 franjas horarios. Creamos matriz que filas = franjas horaris y columnas = dias
 		String horario_resultado[][] = new String[25][5]; //Horario final
 		String horario_previo[][] = new String[25][5]; //Horario previo al final

 		//Matrices para la actualizacion en directo de asignaturas disponibles
 		String horario_resultado_live[][] = new String[25][5];
 		String horario_previo_live[][] = new String[25][5];

 		
 		ArrayList<Asignatura> seleccion = new ArrayList<Asignatura>(); //ArrayList de asignaturas insertadas
 		int insertar = 0; //Asignatura del ArrayList seleccion que se va a insertar

 		//Mostrar asignaturas
 		//Vamos mostrando las asignaturas y el usuario va eligiendo en orden de preferencia
 		int opc;
 		int i=0;
 		do{
 			System.out.println();
 			System.out.println("Asignaturas disponibles: ");
 			for(i=0; i<asig_disponibles.size(); i++){
 				System.out.println((i+1)+".- "+asig_disponibles.get(i).get_nombre());
 			}
 			System.out.println("0.-Hecho.");
 			System.out.println("Seleccione la asignatura que mas le interese o pulse 0 para finalizar:");
 			opc = sc.nextInt();
 			if(opc<=asig_disponibles.size() && opc>0){
 				seleccion_post = seleccion;
 				seleccion.add(new Asignatura(asig_disponibles.get(opc-1)));
 				factibilidad(seleccion, seleccion_post, horario_previo, horario_resultado, insertar, 1);
 				insertar++;
 				asig_disponibles.remove(opc-1);
 				i=0;
 				//actualizar_asig_disponibles(seleccion, asig_disponibles);

 				while(i<asig_disponibles.size()){
 					//Pasamos el horario tal como lo dejamos, en cada iteraccion se modificara el horario_resultado_live, por lo que
 					//tendremos que volverlo a poner como estaba
 					for(int j=0; j<25; j++)
 						for(int q=0; q<5; q++)
 							horario_resultado_live[j][q] = horario_resultado[j][q];
 					
 					//Llamamos a la funcion de factibilidad
 					/*System.out.println(asig_disponibles.get(i).get_nombre());
 					System.out.println("Tamaño practica: "+asig_disponibles.get(i).get_practica().size());*/
 					if(factibilidad(asig_disponibles, seleccion, horario_previo_live, horario_resultado_live, i, 2))
 						i++; //Si añadimos la asginaturas, incrementamos i para que pase a la siguiente
 					else
 						asig_disponibles.remove(i);
 					//Si no la añadimos, se eliminara la asignatura y no hará falta incrementar i para pasar a la siguiente

 				}

 			}else if(opc>0){
 				System.out.println("Eleccion erronea");
 			}

 		}while(opc!=0);

 		System.out.println("Este es el horario provisional: ");
 		//Muestra de resultados provisionales
 		mostrar_horario(horario_resultado);
 		seleccion.clear();
 		asig_finales(horario_resultado, seleccion);

 		//SELECCION POR PARTE DEL USUARIO
 		System.out.println("Ahora vamos a elegir entre las distintas opciones posibles. ");
 		//Comprobamos si hay diferente opciones validas de cada grupo y le pregunta al usuario cual prefiere
 		for(i=0; i<seleccion.size(); i++){ //Recorremos las asignaturas que son compatibles entre ellas.
 			seleccionar_grupo(seleccion.get(i).get_nombre(), horario_resultado, "teoria", seleccion.get(i).grupos_teoria(), seleccion.get(i).get_teoria()); //Teoria
 			seleccionar_grupo(seleccion.get(i).get_nombre(), horario_resultado, "seminario", seleccion.get(i).grupos_seminario(), seleccion.get(i).get_seminario()); //Seminario
 			seleccionar_grupo(seleccion.get(i).get_nombre(), horario_resultado, "practica", seleccion.get(i).grupos_practica(), seleccion.get(i).get_practica()); //Practica
 		}


 		//MUESTRA FINAL
 		System.out.println("Este es tu horario final: ");
 		//Muestra de resultados
 		mostrar_horario(horario_resultado);

 	}//fin main
 	 	
} 