package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Vehicle;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.validation.UserValidation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VehicleDAO {
    public  Vehicle assignVehicle(ResultSet rs) throws DAOException{
        Vehicle vehicle = new Vehicle();
        UserDAO userDao = new UserDAO();
        try {

                vehicle.setVehicleCompany(rs.getString("company"));
                vehicle.setUserId(rs.getInt("user_id"));
                vehicle.setVehicleYear(rs.getInt("year"));
                vehicle.setVehicleType(rs.getInt("vehicle_type"));
                vehicle.setVehicleModel(rs.getString("model"));
                vehicle.setVehicleId(rs.getInt("id"));
                vehicle.setVehicleNumber(rs.getString("vehicle_number"));
                vehicle.setUser(userDao.assignUser(rs));


            return vehicle ;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }public  boolean insertVehicle(Vehicle use) throws DAOException {
            String  query = "insert into vehicles (model,company,vehicle_number,vehicle_type,user_id,year) values (?,?,?,?,?,?)";

        try ( Connection connect = ConnectionDb.getConnection();
              PreparedStatement pre = connect.prepareStatement(query)){
            UserValidation usValid = new UserValidation();
            if(usValid.userValidVehicle(use)){
                pre.setString(1,use.getVehicleModel());
                pre.setString(2,use.getVehicleCompany());
                pre.setString(3,use.getVehicleNumber());
                pre.setInt(4,use.getVehicleType());
                pre.setInt(5,use.getUserId());
                pre.setInt(6,use.getVehicleYear());

                int i = pre.executeUpdate();
                return (i==1);}
            else return false;
        }catch (SQLException | DTBException | InvalidEntryException e){
            throw new DAOException(e);
        }


    }
    public boolean removeVehicle(int id) throws DAOException {
        String query = "delete from vehicles where user_id = ?";
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query)){
            pre.setInt(1,id);
            int i = pre.executeUpdate();
            return i ==1 ;
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }


    }
    public  Vehicle findVehicleByUserId(int id) throws DAOException {
        String query = "select * from vehicles inner join user on user.id = vehicles.user_id  where vehicles.user_id = ?";
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query)){
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            Vehicle vehicle =  new Vehicle();
            if(rs.next())vehicle = assignVehicle(rs);
            return vehicle;

        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }


    }
    public  Vehicle findVehicleById(int id) throws DAOException {
        String query = "select * from vehicles inner join user on user.id = vehicles.user_id  where vehicles.id = ?";
        Vehicle vehicle =  new Vehicle();
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query)){
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
           if(rs.next())vehicle = assignVehicle(rs);
           return vehicle;
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }


    }
    public List<Vehicle>getAllVehicles()throws DAOException {
        String query = "select * from vehicles inner join user on user.id = vehicles.user_id ";
        List<Vehicle> vehicles =  new ArrayList<>();
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query)){
            ResultSet rs = pre.executeQuery();

            while (rs.next()){
               Vehicle vehicle  = assignVehicle(rs);
                vehicles.add(vehicle);
            }



        }catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }
        return vehicles;
    }
//

}
