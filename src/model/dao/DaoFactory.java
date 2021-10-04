package model.dao;

import db.DB;
import model.dao.impl.BancoDaoJDBC;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.TipoCadastroDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

	//Dessa forma eu consigo instaciar o objeto UsuarioDao sem saber a implementação
	public static UsuarioDao createUserDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static TipoCadastroDao createTipoCadastroDao() {
		return new TipoCadastroDaoJDBC(DB.getConnection());
	}
	
	public static BancoDao createBancoDao() {
		return new BancoDaoJDBC(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
}
