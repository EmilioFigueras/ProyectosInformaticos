 public class Practica{
 	//String codigoAsig_;
 	//private Asignatura asig_;
 	private int id_;
 	private String nombre_grupo_;
 	private double hora_inicio_;
 	private double hora_fin_;
 	private int dia_; //0 = Lunes, 1 = Martes, 2 = Mier, 3 = Jueves, 4 = Viernes

 	public Practica(int id, String nombre_grupo, double hora_inicio, double hora_fin, int dia){
 		//codigoAsig_ = codigoAsig_;
 		//asig_ = asig;
 		id_ = id;
 		nombre_grupo_ = nombre_grupo;
 		hora_inicio_ = hora_inicio;
 		hora_fin_ = hora_fin;
 		dia_ = dia;
 	}

 	//Observadores
 	public double hora_inicio(){
 		return hora_inicio_;
 	}

 	public double hora_fin(){
 		return hora_fin_;
 	}

 	/*public String codigoAsig(){
 		return codigoAsig_;
 	}*/

 	public String nombre(){
 		return nombre_grupo_;
 	}
 	/*public Asignatura asignatura(){
 		return asig_;
 	}*/

 	public int id(){
 		return id_;
 	}

 	public int dia(){
 		return dia_;
 	}

 	//Modificadores
 	public void hora_inicio(double hora_inicio){
 		hora_inicio_ = hora_inicio;
 	}

 	public void hora_fin(double hora_fin){
 		hora_fin_ = hora_fin;
 	}

 	/*public void codigoAsig(String codigoAsig){
 		codigoAsig_ = codigoAsig;
 	}*/

 	public void grupo(String grupo){
 		nombre_grupo_ = grupo;
 	}

 } 
