import java.util.*;

public class SimulationDB{

	public ArrayList<Asignatura> todas_asig;

	public SimulationDB(){
		todas_asig = new ArrayList<Asignatura>();
		createSimulation();
	}

	void createSimulation(){

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
 		todas_asig.add(pinf);
 		todas_asig.add(da);
 		todas_asig.add(csi);
 		todas_asig.add(iiss);
 		todas_asig.add(in);

 		//FIN DE LA SIMULACION DE LA BASE DE DATOS

 	}

	ArrayList<Asignatura> getDB(){
		return todas_asig;
	}
}