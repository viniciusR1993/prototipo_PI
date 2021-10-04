package entities;

public class Cargo {
	
	private String descricao;
	private Integer id_cargo;
	public String getTipo() {
		return descricao;
	}
	public void setTipo(String tipo) {
		this.descricao = tipo;
	}
	public Integer getId() {
		return id_cargo;
	}
	public void setId(Integer id) {
		this.id_cargo = id;
	}
	
	public Cargo(String tipo, Integer id) {
		super();
		this.descricao = tipo;
		this.id_cargo = id;
	}
	
	public Cargo() {
		
	}
	
	public Cargo(String tipo) {
		super();
		this.descricao = tipo;
	}
	

}
