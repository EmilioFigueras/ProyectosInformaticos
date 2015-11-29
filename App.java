 import java.util.*;



 public class App{

 	static GruposCompatibles gruposCompatibles;
 	static Scanner sc = new Scanner(System.in);
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
 		String dia_res;
 		switch(dia){
 			case 0:{
 				dia_res = "Lunes";
 				break;
 			}
 			case 1:{
 				dia_res = "Martes";
 				break;
 			}
 			case 2:{
 				dia_res = "Miércoles";
 				break;
 			}
 			case 3:{
 				dia_res = "Jueves";
 				break;
 			}
 			case 4:{
 				dia_res = "Viernes";
 				break;
 			}
 			default:{
 				dia_res = "Error";
 				break;
 			}
 		}
 		return dia_res;
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



 		//SIMULACION DE LA BASE DE DATOS
 		//PINF
 		ArrayList<Teoria> teoria_pinf = new ArrayList<Teoria>();
 		Teoria t_pinf_1 = new Teoria(0, "A1", 11.50, 13.50, 0);
 		teoria_pinf.add(t_pinf_1);
 		ArrayList<Practica> practica_pinf = new ArrayList<Practica>();
 		Practica p_pinf_1 = new Practica(0, "C1", 18.00, 20.50, 4);
 		Practica p_pinf_2 = new Practica(1, "C2", 17.50, 20.00, 3);
 		practica_pinf.add(p_pinf_1);
 		practica_pinf.add(p_pinf_2);
 		Asignatura pinf = new Asignatura("PINF", "url", "Obligatoria", teoria_pinf, practica_pinf, "sin comentarios");

 		//DA
 		ArrayList<Teoria> teoria_da = new ArrayList<Teoria>();
 		Teoria t_da_1 = new Teoria(0, "A1", 15.00, 16.50, 3);
 		teoria_da.add(t_da_1);
 		ArrayList<Practica> practica_da = new ArrayList<Practica>();
 		Practica p_da_1 = new Practica(0, "C1", 16.50, 19.00, 1);
 		Practica p_da_2 = new Practica(1, "C2", 17.50, 20.00, 3);
 		Practica p_da_3 = new Practica(2, "C3", 15.50, 18.00, 4);
 		practica_da.add(p_da_1);
 		practica_da.add(p_da_2);
 		practica_da.add(p_da_3);
 		ArrayList<Seminario> sem_da = new ArrayList<Seminario>();
 		Seminario s_da_1 = new Seminario(0, "B1", 16.50, 17.50, 3);
 		Seminario s_da_2 = new Seminario(1, "B2", 15.50, 16.50, 1);
 		sem_da.add(s_da_1);
 		sem_da.add(s_da_2);
 		Asignatura da = new Asignatura("DA", "url", "Obligatoria", teoria_da, practica_da, sem_da, "sin comentarios");

 		//CSI
 		ArrayList<Teoria> teoria_csi = new ArrayList<Teoria>();
 		Teoria t_csi_1 = new Teoria(0, "A1", 18.00, 20.00, 0);
 		teoria_csi.add(t_csi_1);
 		ArrayList<Practica> practica_csi = new ArrayList<Practica>();
 		Practica p_csi_1 = new Practica(0, "C1", 16.50, 19.00, 1);
 		practica_csi.add(p_csi_1);
 		Asignatura csi = new Asignatura("CSI", "url", "Optativa", teoria_csi, practica_csi, "sin comentarios");

 		//IISS
 		ArrayList<Teoria> teoria_iiss = new ArrayList<Teoria>();
 		Teoria t_iiss_1 = new Teoria(0, "A1", 16.00, 18.00, 0);
 		teoria_iiss.add(t_iiss_1);
 		ArrayList<Practica> practica_iiss = new ArrayList<Practica>();
 		Practica p_iiss_1 = new Practica(0, "C1", 18.00, 20.50, 0);
 		practica_iiss.add(p_iiss_1);
 		Asignatura iiss = new Asignatura("IISS", "url", "Optativa", teoria_iiss, practica_iiss, "sin comentarios");

 		//Inventada
 		ArrayList<Teoria> teoria_in = new ArrayList<Teoria>();
 		Teoria t_in_1 = new Teoria(0, "A1", 16.00, 18.00, 0); //Coincide con IISS
 		teoria_in.add(t_in_1);
 		Teoria t_in_2 = new Teoria(1, "A2", 9.00, 11.50, 4); //Viernes por la manana
 		teoria_in.add(t_in_2);
 		Teoria t_in_3 = new Teoria(2, "A3", 8.50, 10.50, 3);
 		teoria_in.add(t_in_3);
 		ArrayList<Practica> practica_in = new ArrayList<Practica>();
 		Practica p_in_1 = new Practica(0, "C1", 18.00, 20.50, 0); //Coincide con IISS
 		practica_in.add(p_in_1);
 		Practica p_in_2 = new Practica(1, "C2", 15.50, 18.00, 4); //Coincide con DA C3
 		practica_in.add(p_in_2);
 		//Practica p_in_3 = new Practica(2, "C3", 16.50, 19.00, 1); //Coincide con DA C1
 		//practica_in.add(p_in_3);

 		Asignatura in = new Asignatura("INV", "url", "Optativa", teoria_in, practica_in, "sin comentarios");
 		//Otras asignaturas...

 		//Metemos en un arraylist todas las asignaturas
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>();
 		todas_asig.add(pinf);
 		todas_asig.add(da);
 		todas_asig.add(csi);
 		todas_asig.add(iiss);
 		todas_asig.add(in);

 		//FIN DE LA SIMULACION DE LA BASE DE DATOS




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
 		System.out.println();
 		for(i=0; i<24; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}

 		//SELECCION POR PARTE DEL USUARIO
 		System.out.println("Ahora vamos a elegir entre las distintas opciones posibles. ");
 		//Comprobamos si hay diferente opciones validas de cada grupo y le pregunta al usuario cual prefiere
 		for(i=0; i<seleccion.size(); i++) //Recorremos las asignaturas que son compatibles entre ellas.
 			seleccionar_grupo(i, seleccion, horario_resultado);


 		//MUESTRA FINAL
 		System.out.println("Este es tu horario final: ");
 		//Muestra de resultados
 		System.out.println();
 		for(i=0; i<24; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}

 	}//fin main
 	
 	
 	public static void seleccionar_grupo(int i, ArrayList<Asignatura> seleccion, String[][] horario_resultado){
 			int opc;
 			//Teoria
 			if(seleccion.get(i).grupos_teoria() > 1){ //Recorremos los grupos de teoria que tengan mas de una opciones disponible
 				//Le mostramos al usuario los grupos disponibles y esperamos a que elija uno
 				System.out.println("Seleccione el grupo de teoria que desee de la asignatura "+seleccion.get(i).get_nombre());
 				for(int j=0; j<seleccion.get(i).grupos_teoria(); j++){
 					System.out.println(+(j+1)+".- "+dia_a_string(seleccion.get(i).dia_t(j))+" de "+hora_a_string(seleccion.get(i).hora_inicio_t(j))
 						+" horas a "+hora_a_string(seleccion.get(i).hora_fin_t(j))+" horas.");
 				}
 				opc = sc.nextInt();

 				//Ponemos a null en la matriz resultante los grupos que no ha elegido
 				for(int j=0; j<seleccion.get(i).grupos_teoria(); j++)
 					if(j!=(opc-1))
 						for(int k=hora_a_fila(seleccion.get(i).hora_inicio_t(j)); k<hora_a_fila(seleccion.get(i).hora_fin_t(j)); k++)
 							horario_resultado[k][seleccion.get(i).dia_t(j)] = null;

 			}//fin if

 			//Seminario
 			if(seleccion.get(i).grupos_seminario() > 1){ //Recorremos los grupos de seminario que tengan mas de una opciones disponible
 				//Le mostramos al usuario los grupos disponibles y esperamos a que elija uno
 				System.out.println("Seleccione el grupo de seminario que desee de la asignatura "+seleccion.get(i).get_nombre());
 				for(int j=0; j<seleccion.get(i).grupos_seminario(); j++){
 					System.out.println(+(j+1)+".- "+dia_a_string(seleccion.get(i).dia_s(j))+" de "+hora_a_string(seleccion.get(i).hora_inicio_s(j))
 						+" horas a "+hora_a_string(seleccion.get(i).hora_fin_s(j))+" horas.");
 				}
 				opc = sc.nextInt();

 				//Ponemos a null en la matriz resultante los grupos que no ha elegido
 				for(int j=0; j<seleccion.get(i).grupos_seminario(); j++)
 					if(j!=(opc-1))
 						for(int k=hora_a_fila(seleccion.get(i).hora_inicio_s(j)); k<hora_a_fila(seleccion.get(i).hora_fin_s(j)); k++)
 							horario_resultado[k][seleccion.get(i).dia_s(j)] = null;
 			}//fin if

 			//Practica
 			if(seleccion.get(i).grupos_practica() > 1){ //Recorremos los grupos de practica que tengan mas de una opciones disponible
 				//Le mostramos al usuario los grupos disponibles y esperamos a que elija uno
 				System.out.println("Seleccione el grupo de practica que desee de la asignatura "+seleccion.get(i).get_nombre());
 				for(int j=0; j<seleccion.get(i).grupos_practica(); j++){
 					System.out.println(+(j+1)+".- "+dia_a_string(seleccion.get(i).dia_p(j))+" de "+hora_a_string(seleccion.get(i).hora_inicio_p(j))
 						+" horas a "+hora_a_string(seleccion.get(i).hora_fin_p(j))+" horas.");
 				}
 				opc = sc.nextInt();

 				//Ponemos a null en la matriz resultante los grupos que no ha elegido
 				for(int j=0; j<seleccion.get(i).grupos_practica(); j++)
 					if(j!=(opc-1))
 						for(int k=hora_a_fila(seleccion.get(i).hora_inicio_p(j)); k<hora_a_fila(seleccion.get(i).hora_fin_p(j)); k++)
 							horario_resultado[k][seleccion.get(i).dia_p(j)] = null;
 			}//fin if
 	}//fin funcion
 }