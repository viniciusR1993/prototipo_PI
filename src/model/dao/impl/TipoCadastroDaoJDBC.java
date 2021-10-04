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
import entities.Cargo;
import model.dao.TipoCadastroDao;

public class TipoCadastroDaoJDBC implements TipoCadastroDao{
	
	private Connection conn = null;
	
	public TipoCadastroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Cargo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO cargo "
					+ "(Tipo) "
					+ "VALUES "
					+ "(?)", Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getTipo());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Não foi feito a inserção");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Cargo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE cargo "
					+ "SET descricao = ?"
					+ "WHERE id_cargo = ?"
					);
			st.setString(1, obj.getTipo());
			st.setInt(2, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM cargo WHERE id_cargo = ?");
			
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
	public Cargo findById(Integer Id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM cargo WHERE id_cargo = ?");
			st.setInt(1, Id);
			rs = st.executeQuery();
			if(rs.next()) {
				Cargo tipCad = new Cargo();
				tipCad.setId(rs.getInt("id_cargo"));
				tipCad.setTipo(rs.getString("descricao"));
				return tipCad;
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

	@Override
	public List<Cargo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM cargo ORDER BY descricao");
			rs = st.executeQuery();
			List<Cargo> list = new ArrayList<>();
			while(rs.next()) {
				Cargo tipCad = new Cargo();
				tipCad.setId(rs.getInt("id_cargo"));
				tipCad.setTipo(rs.getString("descricao"));
				list.add(tipCad);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeStatement(st);
		}
	}
}
