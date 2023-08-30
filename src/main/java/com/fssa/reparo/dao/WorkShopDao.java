package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.WorkShop;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkShopDao {

	public WorkShop assignWorkShop(ResultSet rs) throws DAOException {
		WorkShop work = new WorkShop();
		try {

			work.setName(rs.getString("name"));
			long lNum = Long.parseLong(rs.getString("number"));
			work.setNumber(lNum);
			work.setId(rs.getInt("id"));
			work.setAddress(rs.getString("address"));
			work.setCity(rs.getString("city"));
			work.setState(rs.getString("state"));
			work.setType(rs.getInt("workshop_type"));

			return work;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	public boolean insertWorkShop(WorkShop work) throws DAOException {
		// This method is used to create user data in db table
		String query = "insert into workshop (name,number,password,address,city,state,workshop_type) values (?,?,?,?,?,?,?)";

		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement pre = connect.prepareStatement(query)) {
			pre.setString(1, work.getName());
			String num = Long.toString(work.getNumber());

			pre.setString(2, num);

			pre.setString(3, work.getPassword());
			pre.setString(4, work.getAddress());
			pre.setString(5, work.getCity());
			pre.setString(6, work.getState());
			pre.setInt(7, work.getType());
			int i = pre.executeUpdate();
			return (i == 1);
		} catch (SQLException | DTBException e) {
			throw new DAOException(e);
		}

	}

	public WorkShop findWorkShopByNumber(long num) throws DAOException {
		WorkShop workshop = new WorkShop();

		String query = "Select * from workshop where number = ?";

		String number = Long.toString(num);

		try (

				Connection connect = ConnectionDb.getConnection();

				PreparedStatement prep = connect.prepareStatement(query)) {

			prep.setString(1, number);
			try (ResultSet rs = prep.executeQuery()) {
				while (rs.next()) {
					workshop = assignWorkShop(rs);
					workshop.setPassword(rs.getString("password"));
				}

			}catch(SQLException e) {
				throw new DAOException("problem in result set");
				
				
			}

			return workshop;

		} catch (SQLException | DTBException e) {
			throw new DAOException(e);
		}

	}

	public boolean removeWorkShopAccount(long number) throws DAOException {
		String query = "delete from workshop where number = ? ;";
		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement pre = connect.prepareStatement(query)) {

			String num = Long.toString(number);
			pre.setString(1, num);
			int i = pre.executeUpdate();
			return i == 1;

		} catch (SQLException | DTBException e) {
			throw new DAOException(e);
		}

	}

	public boolean updateWorkShopPassword(int id, String password) throws DAOException {

		String query = "update workshop set password = ? where id = ?";
		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement pre = connect.prepareStatement(query)) {
			pre.setString(1, password);

			pre.setInt(2, id);
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException | DTBException e) {
			throw new DAOException(e);
		}
	}

	public List<WorkShop> getAllWorkShops() throws DAOException {
		String query = "Select * from workshop";
		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement pre = connect.prepareStatement(query)) {
			ResultSet rs = pre.executeQuery();
			List<WorkShop> workshops = new ArrayList<>();
			while (rs.next()) {
				WorkShop work = assignWorkShop(rs);
				workshops.add(work);
			}
			rs.close();
			return workshops;

		} catch (DTBException | SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Integer> findWorkshopsByArea(String area) throws DAOException {
		String query = "select * from workshop where city = ?";

		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement con = connect.prepareStatement(query)) {

			con.setString(1, area);
			ResultSet rs = con.executeQuery();
			ArrayList<Integer> workshops = new ArrayList<>();
			while (rs.next()) {
				int book = rs.getInt("id");

				workshops.add(book);

			}
			rs.close();
			return workshops;
		} catch (DTBException | SQLException e) {
			throw new DAOException(e);
		}
	}

	public WorkShop getWorkShopsById(int id) throws DAOException {
		String query = "Select * from workshop where id = ?";
		WorkShop workshop = null;
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement pre = connection.prepareStatement(query)) {
			pre.setInt(1, id);
			try (ResultSet rs = pre.executeQuery()) {
				while (rs.next()) {
					workshop = assignWorkShop(rs);
				}

			}catch(SQLException e) {
				throw new DAOException("problem in result set");
				
				
			}
			return workshop;

		} catch (DTBException | SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Integer> getWorkshopsByType(int t) throws DAOException {
		String query = "select id from workshop where workshop_type = ?";

		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement con = connect.prepareStatement(query)) {

			con.setInt(1, t);
			ResultSet rs = con.executeQuery();
			List<Integer> workshops = new ArrayList<>();
			while (rs.next()) {
				int book = rs.getInt("id");
				workshops.add(book);
			}
			return workshops;
		} catch (DTBException | SQLException e) {
			throw new DAOException(e);
		}

	}

	public boolean updateLoginStatus(int id, boolean status) throws DAOException {
		String query = "update workshop set is_login = ? where id = ?";

		try (Connection connect = ConnectionDb.getConnection();
				PreparedStatement pre = connect.prepareStatement(query)) {

			pre.setBoolean(1, status);
			pre.setInt(2, id);
			return pre.executeUpdate() == 1;
		} catch (SQLException | DTBException e) {
			throw new DAOException(e);
		}
	}

}
