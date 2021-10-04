package model.dao;

import java.util.List;

import entities.Cliente;

public interface ClienteDao {
	void insert(Cliente obj);
	void update(Cliente obj);
	void delete(Integer id);
	Cliente findById(Integer Id);
	List<Cliente> findAll();
}
