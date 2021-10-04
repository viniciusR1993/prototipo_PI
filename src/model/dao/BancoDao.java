package model.dao;

import java.util.List;

import entities.Banco;

public interface BancoDao {
	
	void insert(Banco obj);
	void update(Banco obj);
	void delete(Integer id);
	Banco findById(Integer Id);
	List<Banco> findAll();

}
