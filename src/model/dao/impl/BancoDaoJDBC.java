package model.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import entities.Banco;
import model.dao.BancoDao;

public class BancoDaoJDBC implements BancoDao{
	
	private Connection conn;
	
	public BancoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Banco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO prototipo_pi.banco "
					+ "(nome,codigo) "
					+ "VALUES "
					+ "(?,?)", Statement.RETURN_GENERATED_KEYS
					);			
			
			st.setString(1, obj.getNome());
			System.out.println(obj.getCodigo().toString());
			st.setString(2, obj.getCodigo().toString());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_banco(id);	//isso serve para finalizar os dados do obj, ele já vai ter o id
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unxpected error! No row affected!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Banco obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE banco "
					+ "SET nome = ?, codigo = ? "
					+ "WHERE id_banco = ?"
					);
			st.setString(1,obj.getNome());
			st.setInt(2, obj.getCodigo());
			st.setInt(3, obj.getId_banco());
			
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void delete(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM banco WHERE id_banco = ?");
			
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if(rows<=0) {
				throw new DbException("Nenhum dado deletado");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Banco findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM prototipo_pi.banco WHERE id_banco = ?");
					/*
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ " ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);	//Coloca o código*/
			st.setInt(1, Id);	//Troca o interrogação pelo Id
			rs = st.executeQuery();	//Executa o comando
			if(rs.next()) {	//usa o rs.next para pular pra proxima linha da tabela retorna, isso devido na consulta o rs vem na posição 0 (sem nada)
				Banco obj = instatiateBanco(rs);
				return obj;
			}else {
				return null;
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	/*@Override
	public List<Seller> findByDepartmet(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"
					);
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();	//Para controlar a instaciação dos departamentos (para não ter varios departamentos iguais na memoria)
			while(rs.next()) {
				//Essa é um das forma de fazer o controle de memoria, para não ter varios department na memoria
				Department dep = map.get(rs.getInt("DepartmentId"));	//Tenta buscar no metodo get se tem o Id do departamento
				if(dep == null) {	//Se não tiver na lista do Map o dep então ele instacia
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"),dep);
				}
				
				Seller obj = instatiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeStatement(st);
		}
	}*/
	
	
	@Override
	public List<Banco> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Banco> list = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * FROM prototipo_pi.banco");
					/*"SELECT teste.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name"
					);*/
			rs = st.executeQuery();
			
			while(rs.next()) {
				Banco user = instatiateBanco(rs);
				list.add(user);
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return list;
	}
	
	/*//Instancia o Seller a partir de um retorno do BD 
	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {	//Nesse caso eu propago a exceção deivod ter tramento no metodo que é usado
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}*/
	
	//Instancia o Department a partir de um retorno do BD
	private Banco instatiateBanco(ResultSet rs) throws SQLException {	//Nesse caso eu propago a exceção deivod ter tramento no metodo que é usado
		Banco user = new Banco(rs.getInt("id_banco"), rs.getString("nome"), rs.getInt("codigo"));
		return user;
	}

}
