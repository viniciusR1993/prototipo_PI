package model.service;

import java.util.List;

import entities.Usuario;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;

public class UsuarioService {
	
	private UsuarioDao dao = DaoFactory.createUserDao();
	
	public List<Usuario> findAll(){
		return dao.findAll();	//Essa implementaçao foi feita em projeto anterior
		/*MOCK (Estou retornando só os dados ficticios)
		List<Seller> list = new ArrayList<>();
		list.add(new Seller(1,"Books"));
		list.add(new Seller(2,"Computer"));
		list.add(new Seller(3,"Eletronics"));
		return list;*/
	}
	
	public void saveOrUpdate(Usuario obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void remove(Usuario obj) {
		dao.delete(obj.getId());
	}

}
