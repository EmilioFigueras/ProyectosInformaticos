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
 		int dia; //Dia del 0 al 4 en el que tiene lugar la clase indicada
 		int asig; //Posocion en el arraylist "seleccion" de la asignatura coincidente.
 		boolean ocupado; //Indica si el hueco ya esta ocupado en el horario final
 		boolean encontrado; //Indica si se ha encontrado una Asignatura que se esta buscando
 		boolean entra_t, entra_s, entra_p; //Comprueba que de una asignatura hay sitio para, al menos, un grupo de teoria,seminario (si lo tuviese) y practica.
 		int gr_coin; //Indica el numero de grupos de una asginatura coincidente con otra.


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
 		//Otras asignaturas...

 		//Metemos en un arraylist todas las asignaturas
 		ArrayList<Asignatura> todas_asig = new ArrayList<Asignatura>();
 		todas_asig.add(pinf);
 		todas_asig.add(da);
 		todas_asig.add(csi);
 		todas_asig.add(iiss);

 		//Hay 24 franjas horarios. Creamos matriz que filas = franjas horaris y columnas = dias
 		String horario_resultado[][] = new String[24][5];

 		//Matriz de posibles incorporaciones
 		String horario_previo[][] = new String[24][5];
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
 			entra_t=false;
 			entra_s=false;
 			entra_p=false;

 			//Teoria
 			ocupado=false;
 			//Recorremos los grupos de teoria
 			for(int j=0; j<seleccion.get(i).grupos_teoria(); j++){
 				//Recorremos el horario de este grupo
 				dia = seleccion.get(i).dia_t(j);
 				for(int k=hora_a_fila(seleccion.get(i).hora_inicio_t(j)); k<hora_a_fila(seleccion.get(i).hora_fin_t(j)); k++){
 					if(horario_resultado[k][dia] != null){
 						String[] coincidente = horario_resultado[k][dia].split(" "); //coincidente[0] = nombre y coincidente[1] = grupo
 						encontrado = false;
 						asig=-1;
 						for(int q=0; q<seleccion.size() && encontrado==false; q++){
 							if((seleccion.get(q).nombre()).compareTo(coincidente[0]) == 0){
 								encontrado=true;
 								asig=q;
 							}

 						}//fin for q
 						gr_coin = seleccion.get(asig).grupos_teoria();
 						if(gr_coin>1)
 							horario_previo[k][dia]=horario_resultado[k][dia]+"/"+seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_t(j);
 						ocupado = true;
 					}
 				}//fin for k
 				if(ocupado==false){
 					for(int k=hora_a_fila(seleccion.get(i).hora_inicio_t(j)); k<hora_a_fila(seleccion.get(i).hora_fin_t(j)); k++){
 						horario_previo[k][dia]=seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_t(j);
 					}
 					entra_t=true;
 				}
 			}//fin for j


 			//Practica
 			//Recorremos los grupos de practica
 			for(int j=0; j<seleccion.get(i).grupos_practica(); j++){
 				ocupado=false;
 				dia = seleccion.get(i).dia_p(j);
 				//Recorremos el horario de este grupo
 				for(int k=hora_a_fila(seleccion.get(i).hora_inicio_p(j)); k<hora_a_fila(seleccion.get(i).hora_fin_p(j)); k++){
 					if(horario_resultado[k][dia] != null){
 						String[] coincidente = horario_resultado[k][dia].split(" "); //coincidente[0] = nombre y coincidente[1] = grupo
 						encontrado = false;
 						asig=-1;
 						for(int q=0; q<seleccion.size() && encontrado==false; q++){
 							if((seleccion.get(q).nombre()).compareTo(coincidente[0]) == 0){
 								encontrado=true;
 								
 								asig=q;
 							}

 						}//fin for q
 						gr_coin = seleccion.get(asig).grupos_practica();
 						if(gr_coin>1)
 							horario_previo[k][dia]=horario_resultado[k][dia]+"/"+seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_p(j);
 						ocupado = true;
 					}
 				}
 				if(ocupado==false){
 					for(int k=hora_a_fila(seleccion.get(i).hora_inicio_p(j)); k<hora_a_fila(seleccion.get(i).hora_fin_p(j)); k++){
 						horario_previo[k][dia]=seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_p(j);
 					}
 					entra_p = true;
 				}
 			}//fin for j


 			//Seminario
 			//Recorremos los grupos de practica
 			if(seleccion.get(i).grupos_seminario() == 0)
 				entra_s=true;
 			for(int j=0; j<seleccion.get(i).grupos_seminario(); j++){
 				ocupado=false;
 				dia = seleccion.get(i).dia_s(j);
 				//Recorremos el horario de este grupo
 				for(int k=hora_a_fila(seleccion.get(i).hora_inicio_s(j)); k<hora_a_fila(seleccion.get(i).hora_fin_s(j)); k++){
 					if(horario_resultado[k][dia] != null){
 						String[] coincidente = horario_resultado[k][dia].split(" "); //coincidente[0] = nombre y coincidente[1] = grupo
 						encontrado = false;
 						asig=-1;
 						for(int q=0; q<seleccion.size() && encontrado==false; q++){
 							if((seleccion.get(q).nombre()).compareTo(coincidente[0]) == 0){
 								encontrado=true;
 								asig=q;
 							}

 						}//fin for q
 						gr_coin = seleccion.get(asig).grupos_seminario();
 						if(gr_coin>1)
 							horario_previo[k][dia]=horario_resultado[k][dia]+"/"+seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_s(j);
 						ocupado = true;
 					}
 				}
 				if(ocupado==false){
 					for(int k=hora_a_fila(seleccion.get(i).hora_inicio_s(j)); k<hora_a_fila(seleccion.get(i).hora_fin_s(j)); k++){
 						horario_previo[k][dia]=seleccion.get(i).nombre()+" "+seleccion.get(i).nombre_grupo_s(j);
 					}
 					entra_s=true;
 				}
 			}//fin for j

 			//Si al asignatura tiene espacio libre para la teoria, el seminario y la practica, pues la incorporamos.
 			if(entra_t && entra_s && entra_p)
 				for(int j=0; j<24; j++)
 					for(int k=0; k<5; k++)
 						if(horario_previo[j][k]!=null)
 							horario_resultado[j][k]=horario_previo[j][k];



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