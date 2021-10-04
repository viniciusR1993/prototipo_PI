package model.service;

import java.util.List;

import entities.Cliente;
import model.dao.ClienteDao;
import model.dao.DaoFactory;

public class ClienteService {
	
	private ClienteDao dao = DaoFactory.createClienteDao();
	
	public List<Cliente> findAll(){
		return dao.findAll();	//Essa implementaçao foi feita em projeto anterior
		/*MOCK (Estou retornando só os dados ficticios)
		List<Seller> list = new ArrayList<>();
		list.add(new Seller(1,"Books"));
		list.add(new Seller(2,"Computer"));
		list.add(new Seller(3,"Eletronics"));
		return list;*/
	}
	
	public void saveOrUpdate(Cliente obj) {
		if(obj.getId_cliente() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void remove(Cliente obj) {
		dao.delete(obj.getId_cliente());
	}

}
