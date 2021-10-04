package entities;

public class Usuario {
	private Integer id_usuario;
	private String name;
	private String senha;
	private String cargo;
	
	public Integer getId() {
		return id_usuario;
	}
	public void setId(Integer id) {
		this.id_usuario = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return senha;
	}
	public void setPassword(String password) {
		this.senha = password;
	}
	public String getTipoCadastro() {
		return cargo;
	}
	public void setTipoCadastro(String tipoCadastro) {
		this.cargo = tipoCadastro;
	}
	public Usuario(Integer id, String name, String password, String tipoCadastro) {
		super();
		this.id_usuario = id;
		this.name = name;
		this.senha = password;
		this.cargo = tipoCadastro;
	}
	
	public Usuario() {
		
	}
	
	
}
