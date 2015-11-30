 import java.util.*;



 public class App{

 	static SimulationDB simulatedDB = new SimulationDB();
 	static GruposCompatibles gruposCompatibles;
 	static Scanner sc = new Scanner(System.in);
 	
 	//Vector con los nombre de los dias de la semana
 	static String weekDay[] = {"lunes", "martes", "miércoles", "jueves", "viernes"};


 	//funcion que convierte una fila en una hora
 	//Recibe fila (de 0 a 24) y devuelve una hora
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
 		for(int i=0; i<24; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}
 	}

 	public static void main(String[] args){
 		//Simulacion de datos de la base de datos

 		
 		int dia; //Dia del 0 al 4 en el que tiene lugar la clase indicada
 		int asig; //Posocion en el arraylist "seleccion" de la asignatura coincidente.
 		int gru_t; //Posocion en el arraylist "seleccion" del grupo de teoria de la asignatura coincidente.
 		int gru_p; //Posocion en el arraylist "seleccion" del grupo de practicas de la asignatura coincidente.
 		int gru_s; //Posocion en el arraylist "seleccion" del grupo de seminario de la asignatura coincidente.
 		boolean ocupado; //Indica si el hueco ya esta ocupado en el horario final
 		boolean encontrado; //Indica si se ha encontrado una Asignatura que se esta buscando
 		boolean entra_t, entra_s, entra_p; //Comprueba que de una asignatura hay sitio para, al menos, un grupo de teoria,seminario (si lo tuviese) y practica.
 		int gr_coin; //Indica el numero de grupos de una asginatura coincidente con otra.

 		//OBTENEMOS ASIGNATURAS DE UNA BASE DE DATOS SIMULADA
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>(simulatedDB.getDB());


 		//Hay 24 franjas horarios. Creamos matriz que filas = franjas horaris y columnas = dias
 		String horario_resultado[][] = new String[24][5]; //Horario final
 		String horario_previo[][] = new String[24][5]; //Horario previo al final

 		//Matriz de posibles incorporaciones
 		
 		ArrayList<Asignatura> seleccion = new ArrayList<Asignatura>();

 		//Mostrar asignaturas
 		//Vamos mostrando las asignaturas y el usuario va eligiendo en orden de preferencia
 		int opc;
 		do{
 			System.out.println();
 			System.out.println("Asignaturas disponibles: ");
 			for(int i=0; i<todas_asig.size(); i++){
 				System.out.println((i+1)+".- "+todas_asig.get(i).get_nombre());
 			}
 			System.out.println("0.-Hecho.");
 			System.out.println("Seleccione la asignatura que mas le interese o pulse 0 para finalizar:");
 			opc = sc.nextInt();
 			if(opc<=todas_asig.size() && opc>0){
 				seleccion.add(todas_asig.get(opc-1));
 				todas_asig.remove(opc-1);
 			}else if(opc>0){
 				System.out.println("Eleccion erronea");
 			}

 		}while(opc!=0);


 		//Ya tenemos todas las asignaturas que el usuario ha seleccionado en el ArrayList seleccion
 		//Recorremos todas las asignaturas
 		int i=0;
 		//for(int i=0; i<seleccion.size(); i++){ //Bucle para recorrer la asignaturas seleccionadas
 		while(i<seleccion.size()){ //Bucle para recorrer la asignaturas seleccionadas

 			//Pasamos el resultado obtenido hasta ahora a la matriz previa que sera la que vamos a ir modificando
 			//Las funciones para copiar matriz copian la posicion de memoria, por lo que hay que copiarla con bucle :=(
 			for(int j=0; j<24; j++)
 				for(int q=0; q<5; q++)
 					horario_previo[j][q]=horario_resultado[j][q];
 			
 			gruposCompatibles = new GruposCompatibles(seleccion,seleccion.get(i));
 			entra_t = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_teoria());
 			gru_t = gruposCompatibles.getGru();
 			int gru2_t = gruposCompatibles.getGru2();
 			entra_p = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_practica());
 			gru_p = gruposCompatibles.getGru();
 			int gru2_p = gruposCompatibles.getGru2();
 			entra_s = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).get_seminario());
 			gru_s = gruposCompatibles.getGru();
 			int gru2_s = gruposCompatibles.getGru2();
 			asig = gruposCompatibles.getAsig();

 			if(entra_t && entra_s && entra_p){ //Si al asignatura tiene espacio libre para la teoria, el seminario y la practica, pues la incorporamos.
 				for(int j=0; j<24; j++)
 					for(int k=0; k<5; k++)
 						if(horario_previo[j][k]!=null)
 							horario_resultado[j][k]=horario_previo[j][k]; //Lo copiamos
 				if(asig!=-1){ //Si ha habido alguna asignatura que chocase
	 				if(gru_t!=237) //Si ha chocado algun grupo de teoria
	 					seleccion.get(asig).get_teoria().remove(gru_t);
	 				if(gru2_t!=237) //Si sobra un grupo de teoria de esta asignatura
	 					seleccion.get(i).get_teoria().remove(gru2_t);
	 				if(gru_p!=237) //Si ha chocado algun grupo de practicas
	 					seleccion.get(asig).get_practica().remove(gru_p);
	 				if(gru2_p!=237) //Si sobra un grupo de teoria de esta asignatura
	 					seleccion.get(i).get_practica().remove(gru2_p);
	 				if(gru_s!=237) //Si ha chocado algun grupo de seminario
	 					seleccion.get(asig).get_seminario().remove(gru_s);
	 				if(gru2_s!=237) //Si sobra un grupo de teoria de esta asignatura
	 					seleccion.get(i).get_seminario().remove(gru2_s);
	 			}//fin if asig!=-1
	 		//Esto lo hacemos para que la sobreescritura en caso de que haya mas de un grupo de la asignatura "i" (la que se analiza)
	 		//tenga sentido. Es decir, la variable gr_coin y su uso tenga utilidad.

	 		i++; //Si añadimos la asginaturas, incrementamos i para que pase a la siguiente
 			}else{ //fin if
 				System.out.println("La asignatura "+seleccion.get(i).get_nombre()+" no es compatible con el resto.");
 				seleccion.remove(i); //Si no añadimos la asignatura, la borramos de la seleccion y no hace falta incrementar para pasar a la siguiente
 			}
	 		
 		}//fin for i

 		System.out.println("Este es el horario provisional: ");
 		//Muestra de resultados provisionales
 		mostrar_horario(horario_resultado);

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