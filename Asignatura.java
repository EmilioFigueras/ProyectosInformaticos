 import java.util.*;


public class Asignatura{
	private String nombre_;
	private String datos_;
	private String tipo_; //Obligatoria, Optativa, Básica
	private ArrayList<Teoria> teoria_;
	private ArrayList<Practica> practica_;
	private ArrayList<Seminario> seminario_;
	private String coment_;

	public Asignatura(String nombre, String datos, String tipo, ArrayList<Teoria> teoria, ArrayList<Practica> practica, ArrayList<Seminario> seminario, String coment){
		nombre_ = nombre;
		datos_ = datos;
		tipo_ = tipo;
		teoria_ = new ArrayList<Teoria>();
		for(int i=0; i<teoria.size(); i++)
			teoria_.add(teoria.get(i));
		practica_ = new ArrayList<Practica>();
		for(int i=0; i<practica.size(); i++)
			practica_.add(practica.get(i));
		seminario_ = new ArrayList<Seminario>();
		for(int i=0; i<seminario_.size(); i++)
			seminario_.add(seminario.get(i));
		coment_ = coment;

	}

	public Asignatura(String nombre, String datos, String tipo, ArrayList<Teoria> teoria, ArrayList<Practica> practica, String coment){
		nombre_ = nombre;
		datos_ = datos;
		tipo_ = tipo;
		teoria_ = new ArrayList<Teoria>();
		for(int i=0; i<teoria.size(); i++)
			teoria_.add(teoria.get(i));
		practica_ = new ArrayList<Practica>();
		for(int i=0; i<practica.size(); i++)
			practica_.add(practica.get(i));
		seminario_ = new ArrayList<Seminario>();
		coment_ = coment;

	}

	//Devuelve el nombre de la asignatura
	public String nombre(){return nombre_;}

	public String datos(){return datos_;}

	public String tipo(){return tipo_;}

	public ArrayList<Teoria> teoria(){return teoria_;}

	public ArrayList<Practica> practica(){return practica_;}

	public ArrayList<Seminario> seminario(){return seminario_;}

	public String comentario(){return coment_;}

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

    //Recibe la id del grupo
	public double hora_fin_t(int gr){
		return teoria_.get(gr).hora_fin();
	}

	public double hora_fin_p(int gr){
		return practica_.get(gr).hora_fin();
	}

	public double hora_fin_s(int gr){
		return seminario_.get(gr).hora_fin();
	}

	public int dia_t(int gr){
		return teoria_.get(gr).dia();
	}

	public int dia_p(int gr){
		return practica_.get(gr).dia();
	}

	public int dia_s(int gr){
		return seminario_.get(gr).dia();
	}

	public String nombre_grupo_t(int gr){
		return teoria_.get(gr).nombre();
	}

	public String nombre_grupo_p(int gr){
		return practica_.get(gr).nombre();
	}

	public String nombre_grupo_s(int gr){
		return seminario_.get(gr).nombre();
	}






} 
