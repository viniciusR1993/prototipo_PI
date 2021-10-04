package tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Usuario;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;

public class bd {
	public static Map<String,Integer> validaUsuario(String name, String senha) {
		UsuarioDao user = DaoFactory.createUserDao();
		List<Usuario> listUser = user.findAll();
		Map<String,Integer> mapResult = new HashMap<>();
		boolean resultUsuario = false;
		boolean resultSenha = false;
		for(Usuario u: listUser) {
			if(u.getName().equals(name)) {
				resultUsuario = true;
				if(u.getPassword().equals(senha)) {
					resultSenha = true;
				}
			}
		}
		mapResult.put("chave", resultUsuario?1:0);
		mapResult.put("senha", resultSenha?1:0);
		return mapResult;
	}
	
	public static String consultaTipoUsuario(String name){
		UsuarioDao user = DaoFactory.createUserDao();
		List<Usuario> listUser = user.findAll();
		String result = "";
		for(Usuario u: listUser) {
			if(u.getName().equals(name)) {
				result = u.getTipoCadastro();
			}
		}
		return result;
	}

}
