 import java.util.*;



 public class App{

 	static GruposCompatibles gruposCompatibles;

 	//funcion que convierte una fila en una hora
 	//Recibe fila (de 0 a 24) y devuelve una hora
 	public static double fila_a_hora(int n){
 		//n+8.5-n*0.5
 		return (double)((n+8.5)-(n*0.5));
 	}
 	public static int hora_a_fila(double hora){
 		return (int)(2*(hora-8.5));
 	}

 	public static void main(String[] args){
 		//Simulacion de datos de la base de datos

 		Scanner sc = new Scanner(System.in);
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
 		ArrayList<Practica> practica_in = new ArrayList<Practica>();
 		Practica p_in_1 = new Practica(0, "C1", 18.00, 20.50, 0); //Coincide con IISS
 		practica_in.add(p_in_1);
 		Practica p_in_2 = new Practica(1, "C2", 15.50, 18.00, 4); //Coincide con DA C3
 		practica_in.add(p_in_2);
 		Practica p_in_3 = new Practica(2, "C3", 16.50, 19.00, 1); //Coincide con DA C1

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
 				System.out.println((i+1)+".- "+todas_asig.get(i).nombre());
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
 		for(int i=0; i<seleccion.size(); i++){ //Bucle para recorrer la asignaturas seleccionadas

 			gruposCompatibles = new GruposCompatibles(seleccion,seleccion.get(i));
 			entra_t = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).teoria());
 			gru_t = gruposCompatibles.getGru();
 			entra_p = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).practica());
 			gru_p = gruposCompatibles.getGru();
 			entra_s = gruposCompatibles.sonCompatibles(horario_previo,horario_resultado,seleccion.get(i).seminario());
 			gru_s = gruposCompatibles.getGru();
 			asig = gruposCompatibles.getAsig();

 			
 			if(entra_t && entra_s && entra_p){ //Si al asignatura tiene espacio libre para la teoria, el seminario y la practica, pues la incorporamos.
 				for(int j=0; j<24; j++)
 					for(int k=0; k<5; k++)
 						if(horario_previo[j][k]!=null){//Si hay algo escrito
 							horario_resultado[j][k]=horario_previo[j][k]; //Lo copiamos
 							//Ahora eliminaremos TODAS las asignaciones de grupos sobreescritos
 							if(gru_t!=237){//Ha chocado algun grupo de teoria
 								if((horario_resultado[j][k].compareTo(seleccion.get(asig).nombre()+" "+seleccion.get(asig).nombre_grupo_t(gru_t))) == 0)
 									horario_resultado[j][k] = null;
 							}
 							if(gru_p!=237){//Ha chocado algun grupo de teoria
 								if((horario_resultado[j][k].compareTo(seleccion.get(asig).nombre()+" "+seleccion.get(asig).nombre_grupo_p(gru_p))) == 0)
 									horario_resultado[j][k] = null;
 							}
 							if(gru_s!=237){//Ha chocado algun grupo de teoria
 								if((horario_resultado[j][k].compareTo(seleccion.get(asig).nombre()+" "+seleccion.get(asig).nombre_grupo_s(gru_s))) == 0)
 									horario_resultado[j][k] = null;
 							}
 						}//fin if horario_previo != null
 			}else{ //fin if
 				System.out.println("La asignatura "+seleccion.get(i).nombre()+" no es compatible con el resto.");
 			}


 			//Ahora buscamos los grupos eliminados
	 		if(asig!=-1){ //Si ha habido alguna asignatura que chocase
	 			if(gru_t!=237) //Si ha chocado algun grupo de teoria
	 				seleccion.get(asig).teoria().remove(gru_t);
	 			if(gru_p!=237) //Si ha chocado algun grupo de practicas
	 				seleccion.get(asig).practica().remove(gru_p);
	 			if(gru_s!=237) //Si ha chocado algun grupo de seminario
	 				seleccion.get(asig).seminario().remove(gru_s);
	 		}
	 		//Esto lo hacemos para que la sobreescritura en caso de que haya mas de un grupo de la asignatura "i" (la que se analiza)
	 		//tenga sentido. Es decir, la variable gr_coin y su uso tenga utilidad.
 		}//fin for i


 		//Muestra de resultados
 		System.out.println();
 		for(int i=0; i<24; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}

 	}//fin main
 }