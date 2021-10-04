package model.service;

import java.util.List;

import entities.Cargo;
import model.dao.DaoFactory;
import model.dao.TipoCadastroDao;

public class TipoCadastroService {
	
	private TipoCadastroDao dao = DaoFactory.createTipoCadastroDao();
	
	public List<Cargo> findAll(){
		return dao.findAll();	//Essa implementaçao foi feita em projeto anterior
		/*MOCK (Estou retornando só os dados ficticios)
		List<Department> list = new ArrayList<>();
		list.add(new Department(1,"Books"));
		list.add(new Department(2,"Computer"));
		list.add(new Department(3,"Eletronics"));
		return list;*/
	}
	
	public void saveOrUpdate(Cargo obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void remove(Cargo obj) {
		dao.delete(obj.getId());
	}

}
