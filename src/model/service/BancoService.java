package model.service;

import java.util.List;

import entities.Banco;
import model.dao.BancoDao;
import model.dao.DaoFactory;

public class BancoService {
	
	private BancoDao dao = DaoFactory.createBancoDao();
	
	public List<Banco> findAll(){
		return dao.findAll();	//Essa implementaçao foi feita em projeto anterior
		/*MOCK (Estou retornando só os dados ficticios)
		List<Seller> list = new ArrayList<>();
		list.add(new Seller(1,"Books"));
		list.add(new Seller(2,"Computer"));
		list.add(new Seller(3,"Eletronics"));
		return list;*/
	}
	
	public void saveOrUpdate(Banco obj) {
		if(obj.getId_banco() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void remove(Banco obj) {
		dao.delete(obj.getId_banco());
	}

}
