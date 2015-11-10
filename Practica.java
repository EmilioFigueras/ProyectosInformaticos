 public class Practica{
 	String codigoAsig_;
 	String grupo_;
 	double hora_inicio_;
 	double hora_fin_;

 	public Practica(String codigoAsig, String grupo, double hora_inicio, double hora_fin){
 		codigoAsig_ = codigoAsig_;
 		grupo_ = grupo;
 		hora_inicio_ = hora_inicio;
 		hora_fin_ = hora_fin;
 	}	

 	//Observadores
 	public double hora_inicio(){
 		return hora_inicio_;
 	}

 	public double hora_fin(){
 		return hora_fin_;
 	}

 	public String codigoAsig(){
 		return codigoAsig_;
 	}

 	public String grupo(){
 		return grupo_;
 	}

 	//Modificadores
 	public void hora_inicio(double hora_inicio){
 		hora_inicio_ = hora_inicio;
 	}

 	public void hora_fin(double hora_fin){
 		hora_fin_ = hora_fin;
 	}

 	public void codigoAsig(String codigoAsig){
 		codigoAsig_ = codigoAsig;
 	}

 	public void grupo(String grupo){
 		grupo_ = grupo;
 	}


 } 
