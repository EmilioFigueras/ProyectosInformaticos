 import java.util.*;



 public class app{

 	//funcion que convierte una fila en una hora
 	//Recibe fila (de 0 a 24) y devuelve una hora
 	public double convertir(int n){
 		//n+8.5-n*0.5
 		return (n+8.5)-(n*0.5);
 	}

 	public static void main(String[] args){
 		//Simulacion de datos de la base de datos

 		//PINF
 		ArrayList<Teoria> teoria_pinf = new ArrayList<Teoria>();
 		Teoria t_pinf_1 = new Teoria("X1", 11.30, 13.30, 0);
 		teoria_pinf.add(t_pinf_1);
 		ArrayList<Practica> practica_pinf = new ArrayList<Practica>();
 		Practica p_pinf_1 = new Practica("C1", 18.00, 20.30, 4);
 		Practica p_pinf_2 = new Practica("C2", 17.30, 20.00, 3);
 		practica_pinf.add(p_pinf_1);
 		practica_pinf.add(p_pinf_2);
 		Asignatura pinf = new Asignatura("PINF", "url", "Obligatoria", teoria_pinf, practica_pinf, "sin comentarios");

 		//DA
 		ArrayList<Teoria> teoria_da = new ArrayList<Teoria>();
 		Teoria t_da_1 = new Teoria("A1", 15.00, 16.30, 3);
 		teoria_da.add(t_pinf_1);
 		ArrayList<Practica> practica_da = new ArrayList<Practica>();
 		Practica p_da_1 = new Practica("C1", 16.30, 19.00, 1);
 		Practica p_da_2 = new Practica("C2", 17.30, 20.00, 3);
 		Practica p_da_3 = new Practica("C3", 15.30, 18.00, 4);
 		practica_da.add(p_da_1);
 		practica_da.add(p_da_2);
 		practica_da.add(p_da_3);
 		ArrayList<Seminario> sem_da = new ArrayList<Seminario>();
 		Seminario s_da_1 = new Seminario("B1", 16.30, 17.30, 3);
 		Seminario s_da_2 = new Seminario("B2", 15.30, 16.30, 1);
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
 		//Mostrar asignaturas
 		for(int i=0; i<todas_asig.size(); i++){
 			todas_asig.get(i).nombre();
 		}




 	}
 }