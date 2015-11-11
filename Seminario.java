 public class Seminario{
	//String codigoAsig_;
 	//private Asignatura asig_;
 	private String grupo_;
 	private double hora_inicio_;
 	private double hora_fin_;
 	private int dia_; //0 = Lunes, 1 = Martes, 2 = Mier, 3 = Jueves, 4 = Viernes

 	public Seminario(String grupo, double hora_inicio, double hora_fin, int dia){
 		//codigoAsig_ = codigoAsig_;
 		//asig_ = asig;
 		grupo_ = grupo;
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

 	public String grupo(){
 		return grupo_;
 	}
 	/*public Asignatura asignatura(){
 		return asig_;
 	}*/

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
 		grupo_ = grupo;
 	}

 } 
