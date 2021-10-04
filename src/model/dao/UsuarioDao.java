package model.dao;

import java.util.List;

import entities.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario obj);
	void update(Usuario obj);
	void delete(Integer id);
	Usuario findById(Integer Id);
	List<Usuario> findAll();

}
