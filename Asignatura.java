public class Asignatura{
	private String nombre_;
	private String datos_;
	private String tipo_; //Obligatoria, Optativa, Básica
	private ArrayList<Teoria> teoria_;
	private ArrayList<Practica> practica_;
	private ArrayList<Seminario> seminario_;
	private Comentarios coment_;

	public Asignatura(String nombre, String datos, String tipo, ArrayList<Teoria> teoria, ArrayList<Practica> practica, ArrayList<Seminario> seminario, Comentarios coment){
		nombre_ = nombre;
		datos_ = datos;
		tipo_ = tipo;
		teoria_ = new ArrayList<Teoria>;
		teoria_ = teoria.clone();
		practica_ = new ArrayList<Practica>;
		practica_ = practica.clone();
		seminario_ = new ArrayList<Seminario>;
		seminario_ = seminario.clone();
		coment_ = coment;

	}

	//Devuelve el nombre de la asignatura
	public String Nombre(){return nombre_;}

	public String Datos(){return datos_;}

	public String Tipo(){return tipo_;}

	public ArrayList<Teoria> Teoria(){return teoria_;}

	public ArrayList<Practica> Practica(){return practica_;}

	public ArrayList<Seminario> Seminario(){return seminario_;}

	public Comentarios Comentario(){return coment_;}

	//Devuelve el numero de grupos de teoria/practica/sem
	public int grupos_teoria(){
		return teoria_.size();
	}

	public int grupos_practica(){
		return practica_.size();
	}

	public int grupos_seminario(){
		return seminario_.size();
	}


	//Double?? O tipo de dato que sea hora_comienzo en la clase Teoria
	//Indica la hora de comienzo del grupo "gr" (empieza en 0)
	public double hora_inicio_t(int gr){
		return teoria_.get(gr).hora_inicio();
	}

	public double hora_inicio_p(int gr){
		return practica_.get(gr).hora_inicio();
	}

	public double hora_inicio_s(int gr){
		return seminario_.get(gr).hora_inicio();
	}

	//Double? o tipo de dato que sea hora_fin en la clase Toeria/Pr/Sem
	public double hora_fin_t(int gr){
		return teoria_.get(gr).hora_fin();
	}

	public double hora_fin_p(int gr){
		return practica_.get(gr).hora_fin();
	}

	public double hora_fin_s(int gr){
		return seminario_.get(gr).fin();
	}




} 
