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
 					System.out.println(+(j+1)+".- "+dia_a_string(grupo.get(j).getDia())+" de "+hora_a_string(grupo.get(j).getHora_inicio())
 						+" horas a "+hora_a_string(grupo.get(j).getHora_fin())+" horas.");
 				}
 				opc = sc.nextInt();
 				for(int j=0; j<num_grupos; j++)
 					if(j!=(opc-1))
 						for(int k=hora_a_fila(grupo.get(j).getHora_inicio()); k<hora_a_fila(grupo.get(j).getHora_fin()); k++)
 							horario_resultado[k][grupo.get(j).getDia()] = null;
 			 				
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
 		entra_t = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).getTeoria(), todas_asig);
 		entra_p = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).getPractica(), todas_asig);
 		if(seleccion.get(i).getSeminario().size() ==0)
 			entra_s=true;
 		else
 			entra_s = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).getSeminario(), todas_asig);

 		if(entra_t && entra_s && entra_p){ //Si al asignatura tiene espacio libre para la teoria, el seminario y la practica, pues la incorporamos.
 			
 			if(tipo!=1)
 				return true;
 			for(int j=0; j<25; j++)
 				for(int k=0; k<5; k++)
 					horario_resultado[j][k]=horario_previo[j][k]; //Lo copiamos
 			seleccion.clear();
 			asig_finales(horario_resultado, seleccion);
	 		return true;
 		}else{ //fin if
 			//La asignatura no es compatible
 			return false;
 		}
 	}

 	/*Devuelve un ArrayList con las asignaturas y subgrupos que estan en el horario final*/
 	public static void asig_finales(String[][] horario_resultado, ArrayList<Asignatura> sel_final){
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
 						if((sel_final.get(k).getNombre()).compareTo(asignatura[0]) == 0){
 							posicion_sel_final=k;
 							seguir_buscando=false;
 							primera_vez=false;
 						}
 					}//fin for k
 					seguir_buscando=true;
 					//Si no tenemos la asignatura ya agregada, pues la agregamos
 					for(int k=0; k<todas_asig.size() && seguir_buscando==true; k++){
 						if((todas_asig.get(k).getNombre()).compareTo(asignatura[0]) == 0){
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
 						sel_final.get(posicion_sel_final).getTeoria().clear();
 						sel_final.get(posicion_sel_final).getPractica().clear();
 						sel_final.get(posicion_sel_final).getSeminario().clear();
 					}
 					seguir_buscando=true;
 					//Ahora buscamos el subgrupo al que pertenece la asignatura
 					//Buscamos si ese subgrupo es de teoria
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).getTeoria().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).getTeoria().get(k).getNombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).getTeoria().add(todas_asig.get(posicion_todas_asig).getTeoria().get(k));
 							seguir_buscando=false;
 						}
 					}
 					//Buscamos si ese subgrupo es de practica
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).getPractica().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).getPractica().get(k).getNombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).getPractica().add(todas_asig.get(posicion_todas_asig).getPractica().get(k));
 							seguir_buscando=false;
 						}
 					}
 					//Buscamos si ese subgrupo es de seminario
 					for(int k=0; k<todas_asig.get(posicion_todas_asig).getSeminario().size() && seguir_buscando==true; k++){
 						if((todas_asig.get(posicion_todas_asig).getSeminario().get(k).getNombre()).compareTo(asignatura[1]) == 0){
 							sel_final.get(posicion_sel_final).getSeminario().add(todas_asig.get(posicion_todas_asig).getSeminario().get(k));
 							seguir_buscando=false;
 						}
 					}

 				}//fin if horario_resultado
 			}//fin for j
 	}

 	/*public static void actualizar_asig_disponibles(ArrayList<Asignatura> seleccion, ArrayList<Asignatura> asig_disponibles){
 		boolean eliminar;
 		for(int i=0; i<seleccion.size(); i++){
 			for(int j=0; j<asig_disponibles.size(); j++){
 				if((seleccion.get(i).getNombre()).compareTo(asig_disponibles.get(j).getNombre()) == 0){
 					//Teoria
 					//Si se ha eliminado algun grupo de teoria de las asignaturas seleccionada
 					if(seleccion.get(i).getTeoria().size() < asig_disponibles.get(j).getTeoria().size()){
 						//Buscamos cual es el grupo que falta
 						int k=0;
 						while(k<asig_disponibles.get(j).getTeoria().size()){
 							eliminar=true;
 							for(int q=0; q<seleccion.get(i).getTeoria().size(); q++){
 								if((asig_disponibles.get(j).getTeoria().get(k).getNombre()).compareTo(seleccion.get(i).getTeoria().get(q).getNombre()) == 0){
 									eliminar=false;
 								}
 							}
 							if(eliminar){
 								asig_disponibles.get(j).getTeoria().remove(k);
 							}else
 								k++;
 						}
 					}
 				}
 			}
 		}
 	}*/

 	public static int posicion_id(int id, ArrayList<Asignatura> buscar){
 		for(int i=0; i<buscar.size(); i++)
 			if(buscar.get(i).getId() == id)
 				return i;
 		return -1;
 	}

 	public static void main(String[] args){
 		//OBTENEMOS ASIGNATURAS DE UNA BASE DE DATOS SIMULADA
 		SimulationDB simulacion1 = new SimulationDB();
 		ArrayList<Asignatura> asig_disponibles = new ArrayList<Asignatura>(simulacion1.getDB());
 		ArrayList<Asignatura> seleccion_post = new ArrayList<Asignatura>(); //Estado anterior de la seleccion antes de agregar una nueva
 		int num_total_asig = asig_disponibles.size();

 		ArrayList<Integer> accion_usuario = new ArrayList<Integer>();

 		//Hay 25 franjas horarios. Creamos matriz que filas = franjas horaris y columnas = dias
 		String horario_resultado[][] = new String[25][5]; //Horario final
 		String horario_previo[][] = new String[25][5]; //Horario previo al final

 		//Matrices para la actualizacion en directo de asignaturas disponibles
 		String horario_resultado_live[][] = new String[25][5];
 		String horario_previo_live[][] = new String[25][5];

 		
 		ArrayList<Asignatura> seleccion = new ArrayList<Asignatura>(); //ArrayList de asignaturas insertadas
 		ArrayList<Asignatura> incompatibles = new ArrayList<Asignatura>(); //Asignaturas que se van eliminando
 		int insertar = 0; //Asignatura del ArrayList seleccion que se va a insertar

 		//Mostrar asignaturas
 		//Vamos mostrando las asignaturas y el usuario va eligiendo en orden de preferencia
 		int opc;
 		int i=0;
 		do{
 			System.out.println();
 			System.out.println("Asignaturas seleccionadas: ");
 			System.out.println("Seleccione una para eliminarla: ");
 			for(i=0; i<seleccion.size(); i++){
 				System.out.println((num_total_asig+seleccion.get(i).getId())+".- "+seleccion.get(i).getNombre());
 			}
 			System.out.println("Asignaturas incompatibles: ");
 			for(i=0; i<incompatibles.size(); i++){
 				System.out.println((i+1)+".- "+incompatibles.get(i).getNombre());
 			}
 			System.out.println("Asignaturas disponibles: ");
 			for(i=0; i<asig_disponibles.size(); i++){
 				System.out.println((asig_disponibles.get(i).getId())+".- "+asig_disponibles.get(i).getNombre());
 			}
 			System.out.println("0.-Hecho.");
 			System.out.println("Seleccione la asignatura disponible que mas le interese o pulse 0 para finalizar:");
 			opc = sc.nextInt();
 			if(opc<=num_total_asig && opc>0){
 				accion_usuario.add(0, opc); //Insertamos siempre en la primera posicion

 				seleccion_post = seleccion;
 				seleccion.add(new Asignatura(asig_disponibles.get(posicion_id(opc, asig_disponibles))));
 				//Insertamos y comparamos a lo que habia antes de agregar la nueva asignatura
 				factibilidad(seleccion, seleccion_post, horario_previo, horario_resultado, insertar, 1);
 				insertar++;
 				asig_disponibles.remove(posicion_id(opc, asig_disponibles));
 				i=0;
 				//actualizar_asig_disponibles(seleccion, asig_disponibles);

 				while(i<asig_disponibles.size()){
 					//Pasamos el horario tal como lo dejamos, en cada iteraccion se modificara el horario_resultado_live, por lo que
 					//tendremos que volverlo a poner como estaba
 					for(int j=0; j<25; j++)
 						for(int q=0; q<5; q++)
 							horario_resultado_live[j][q] = horario_resultado[j][q];
 					
 					//Llamamos a la funcion de factibilidad
 					if(factibilidad(asig_disponibles, seleccion, horario_previo_live, horario_resultado_live, i, 2))
 						i++; //Si añadimos la asginaturas, incrementamos i para que pase a la siguiente
 					else{
 						incompatibles.add(new Asignatura(asig_disponibles.get(i)));
 						asig_disponibles.remove(i);
 					}
 						
 					//Si no la añadimos, se eliminara la asignatura y no hará falta incrementar i para pasar a la siguiente

 				}

 			}else if(opc<=(num_total_asig*2) && opc>0){
 				accion_usuario.remove(accion_usuario.indexOf(opc-num_total_asig));
 				//Limpiamos todas las variables
 				insertar=0;
 				seleccion.clear();
 				seleccion_post.clear();
 				asig_disponibles.clear();
 				asig_disponibles = new ArrayList<Asignatura>(simulacion1.getDB());
 				incompatibles.clear();
 				for(int j=0; j<25; j++)
 					for(int q=0; q<5; q++)
 						horario_resultado[j][q]=null;

 				for(int asig_auto=0; asig_auto<accion_usuario.size(); asig_auto++){
 					seleccion_post = seleccion;
 					seleccion.add(new Asignatura(asig_disponibles.get(posicion_id(accion_usuario.get(asig_auto), asig_disponibles))));
 					//Insertamos y comparamos a lo que habia antes de agregar la nueva asignatura
 					factibilidad(seleccion, seleccion_post, horario_previo, horario_resultado, insertar, 1);
 					insertar++;
 					asig_disponibles.remove(posicion_id(accion_usuario.get(asig_auto), asig_disponibles));
 					i=0;

 					while(i<asig_disponibles.size()){
 						//Pasamos el horario tal como lo dejamos, en cada iteraccion se modificara el horario_resultado_live, por lo que
 						//tendremos que volverlo a poner como estaba
 						for(int j=0; j<25; j++)
 							for(int q=0; q<5; q++)
 								horario_resultado_live[j][q] = horario_resultado[j][q];
 					
 						//Llamamos a la funcion de factibilidad
 						if(factibilidad(asig_disponibles, seleccion, horario_previo_live, horario_resultado_live, i, 2))
 							i++; //Si añadimos la asginaturas, incrementamos i para que pase a la siguiente
 						else{
 							incompatibles.add(new Asignatura(asig_disponibles.get(i)));
 							asig_disponibles.remove(i);
 						}
 						
 						//Si no la añadimos, se eliminara la asignatura y no hará falta incrementar i para pasar a la siguiente

 					}
 				}

 			}

 		}while(opc!=0);

 		System.out.println("Este es el horario provisional: ");
 		//Muestra de resultados provisionales
 		mostrar_horario(horario_resultado);

 		//SELECCION POR PARTE DEL USUARIO
 		System.out.println("Ahora vamos a elegir entre las distintas opciones posibles. ");
 		//Comprobamos si hay diferente opciones validas de cada grupo y le pregunta al usuario cual prefiere
 		for(i=0; i<seleccion.size(); i++){ //Recorremos las asignaturas que son compatibles entre ellas.
 			seleccionar_grupo(seleccion.get(i).getNombre(), horario_resultado, "teoria", seleccion.get(i).grupos_teoria(), seleccion.get(i).getTeoria()); //Teoria
 			seleccionar_grupo(seleccion.get(i).getNombre(), horario_resultado, "seminario", seleccion.get(i).grupos_seminario(), seleccion.get(i).getSeminario()); //Seminario
 			seleccionar_grupo(seleccion.get(i).getNombre(), horario_resultado, "practica", seleccion.get(i).grupos_practica(), seleccion.get(i).getPractica()); //Practica
 		}


 		//MUESTRA FINAL
 		System.out.println("Este es tu horario final: ");
 		//Muestra de resultados
 		mostrar_horario(horario_resultado);

 	}//fin main
 	 	
} 