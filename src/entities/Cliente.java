package entities;

public class Cliente {
	
	private Integer id_cliente;
	private String nome;
	private String documento;
	
	public Cliente(Integer id_cliente, String nome, String documento) {
		super();
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.documento = documento;
	}
	
	public Cliente() {
		
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	

}
