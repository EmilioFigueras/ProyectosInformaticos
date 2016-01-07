 import java.util.*;


public class Asignatura{
	private int id_;
	private String nombre_;
	private String datos_;
	private String tipo_; //Obligatoria, Optativa, Básica
	private ArrayList<Teoria> teoria_;
	private ArrayList<Practica> practica_;
	private ArrayList<Seminario> seminario_;
	private String coment_;

	public Asignatura(int id, String nombre, String datos, String tipo, ArrayList<Teoria> teoria, ArrayList<Practica> practica, ArrayList<Seminario> seminario, String coment){
		id_ = id;
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
		for(int i=0; i<seminario.size(); i++)
			seminario_.add(seminario.get(i));
		coment_ = coment;

	}

	public Asignatura(int id, String nombre, String datos, String tipo, ArrayList<Teoria> teoria, ArrayList<Practica> practica, String coment){
		id_ = id;
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

	public Asignatura(Asignatura asig){
		id_ = asig.getId();
		nombre_ = asig.getNombre();
		datos_ = asig.getDatos();
		tipo_ = asig.getTipo();
		coment_ = asig.getComentario();
		teoria_ = new ArrayList<Teoria>();
		for(int i=0; i<asig.getTeoria().size(); i++)
			teoria_.add(asig.getTeoria().get(i));
		practica_ = new ArrayList<Practica>();
		for(int i=0; i<asig.getPractica().size(); i++)
			practica_.add(asig.getPractica().get(i));
		seminario_ = new ArrayList<Seminario>();
		for(int i=0; i<asig.getSeminario().size(); i++)
			seminario_.add(asig.getSeminario().get(i));

	}

	/*public void clear(ArrayList<Teoria> teoria, ArrayList<Practica> practica, ArrayList<Seminario> seminario){
		teoria_ = new ArrayList<Teoria>();
		for(int i=0; i<teoria.size(); i++)
			teoria_.add(teoria.get(i));
		practica_ = new ArrayList<Practica>();
		for(int i=0; i<practica.size(); i++)
			practica_.add(practica.get(i));
		seminario_ = new ArrayList<Seminario>();
		for(int i=0; i<seminario.size(); i++)
			seminario_.add(seminario.get(i));
	}*/

	public int getId(){return id_;}

	//Devuelve el nombre de la asignatura
	public String getNombre(){return nombre_;}

	public String getDatos(){return datos_;}

	public String getTipo(){return tipo_;}

	public ArrayList<Teoria> getTeoria(){return teoria_;}

	public ArrayList<Practica> getPractica(){return practica_;}

	public ArrayList<Seminario> getSeminario(){return seminario_;}

	public String getComentario(){return coment_;}

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


	//Indica la hora de comienzo del grupo "gr" (empieza en 0)
	public double hora_inicio_t(int gr){
		return teoria_.get(gr).getHora_inicio();
	}

	public double hora_inicio_p(int gr){
		return practica_.get(gr).getHora_inicio();
	}

	public double hora_inicio_s(int gr){
		return seminario_.get(gr).getHora_inicio();
	}

    //Recibe la id del grupo
	public double hora_fin_t(int gr){
		return teoria_.get(gr).getHora_fin();
	}

	public double hora_fin_p(int gr){
		return practica_.get(gr).getHora_fin();
	}

	public double hora_fin_s(int gr){
		return seminario_.get(gr).getHora_fin();
	}

	public int dia_t(int gr){
		return teoria_.get(gr).getDia();
	}

	public int dia_p(int gr){
		return practica_.get(gr).getDia();
	}

	public int dia_s(int gr){
		return seminario_.get(gr).getDia();
	}

	public String nombre_grupo_t(int gr){
		return teoria_.get(gr).getNombre();
	}

	public String nombre_grupo_p(int gr){
		return practica_.get(gr).getNombre();
	}

	public String nombre_grupo_s(int gr){
		return seminario_.get(gr).getNombre();
	}






} 
