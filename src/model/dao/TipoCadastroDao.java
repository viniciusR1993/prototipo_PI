package model.dao;

import java.util.List;

import entities.Cargo;

public interface TipoCadastroDao {
	
	void insert(Cargo obj);
	void update(Cargo obj);
	void delete(Integer id);
	Cargo findById(Integer Id);
	List<Cargo> findAll();
}
