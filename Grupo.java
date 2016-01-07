public abstract class Grupo{
	//String codigoAsig_;
 	//protected Asignatura asig_;
 	protected int id_;
 	protected String nombre_grupo_;
 	protected double hora_inicio_;
 	protected double hora_fin_;
 	protected int dia_; //0 = Lunes, 1 = Martes, 2 = Mier, 3 = Jueves, 4 = Viernes
 	protected String tipoSub_;

 	//Observadores
 	public double getHora_inicio(){
 		return hora_inicio_;
 	}

 	public double getHora_fin(){
 		return hora_fin_;
 	}

 	public String getTipoSub(){
 		return tipoSub_;
 	}

 	/*public String codigoAsig(){
 		return codigoAsig_;
 	}*/

 	public String getNombre(){
 		return nombre_grupo_;
 	}
 	/*public Asignatura asignatura(){
 		return asig_;
 	}*/

 	public int getId(){
 		return id_;
 	}

 	public int getDia(){
 		return dia_;
 	}

 	//Modificadores
 	public void setHora_inicio(double hora_inicio){
 		hora_inicio_ = hora_inicio;
 	}

 	public void setHora_fin(double hora_fin){
 		hora_fin_ = hora_fin;
 	}

 	/*public void codigoAsig(String codigoAsig){
 		codigoAsig_ = codigoAsig;
 	}*/

 	public void setGrupo(String grupo){
 		nombre_grupo_ = grupo;
 	}
}