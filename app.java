 import java.util.*;



 public class app{

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
 		int dia;
 		boolean ocupado;

 		//PINF
 		ArrayList<Teoria> teoria_pinf = new ArrayList<Teoria>();
 		Teoria t_pinf_1 = new Teoria(0, "X1", 11.50, 13.50, 0);
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

 		//Otras asignaturas...

 		//Metemos en un arraylist todas las asignaturas
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>();
 		todas_asig.add(pinf);
 		todas_asig.add(da);

 		//Hay 24 franjas horarios. Creamos matriz que filas = franjas horaris y columnas = dias
 		String horario_resultado[][] = new String[24][5];
 		ArrayList<Asignatura> seleccion = new ArrayList<Asignatura>();

 		//Mostrar asignaturas
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
 				System.out.println("Deja de intentar joderme hijodeputa");
 			}

 		}while(opc!=0);

 		//Ya tenemos todas las asignaturas seleccionadas en el ArrayList seleccion
 		//Recorremos todas las asignaturas
 		for(int i=0; i<seleccion.size(); i++){
 			//Teoria
 			int grupos_teoria = seleccion.get(i).grupos_teoria();
 			ocupado=false;
 			//Recorremos los grupos de teoria
 			for(int j=0; j<grupos_teoria; j++){
 				//Recorremos el horario de este grupo
 				dia = seleccion.get(i).dia_t(j);
 				for(int k=hora_a_fila(seleccion.get(i).hora_inicio_t(j)); k<hora_a_fila(seleccion.get(i).hora_fin_t(j)); k++){
 					if(horario_resultado[k][dia] != null)
 						ocupado = true;
 				}
 				if(ocupado==false){
 					for(int k=hora_a_fila(seleccion.get(i).hora_inicio_t(j)); k<hora_a_fila(seleccion.get(i).hora_fin_t(j)); k++){
 						horario_resultado[k][dia]=seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_t(j);
 					}
 				}
 			}//fin for j


 			//Practica


 			//Seminario
 		}//fin for i

 		//Muestra de resultados
 		for(int i=0; i<24; i++){
 			for(int j=0; j<5; j++){
 				System.out.print(horario_resultado[i][j]+"  ");
 			}
 			System.out.println();
 		}




 	}//fin main
 }