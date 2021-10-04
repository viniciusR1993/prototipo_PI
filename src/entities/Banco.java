package entities;

public class Banco {

	private Integer id_banco;
	private String nome;
	private Integer codigo;
	
	public Banco(Integer id_banco, String nome, Integer codigo) {
		super();
		this.id_banco = id_banco;
		this.nome = nome;
		this.codigo = codigo;
	}
	public Banco() {
	}

	public Integer getId_banco() {
		return id_banco;
	}

	public void setId_banco(Integer id_banco) {
		this.id_banco = id_banco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}
