package dao;

import exception.DaoException;
import model.Vehicle;
import util.ConnectionDb;
import util.DTBException;
import validation.InvalidEntryException;
import validation.UserValidation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    public static boolean insertVehicle(Vehicle use) throws DaoException {
        String  query = "insert into vehicles (model,company,vehicleNumber,vehicleType,user_id,year) values (?,?,?,?,?,?)";

        try ( Connection connect = ConnectionDb.getConnection();
              PreparedStatement pre = connect.prepareStatement(query);){
            UserValidation usValid = new UserValidation();
            if(usValid.userValidVehicle(use)){
                pre.setString(1,use.getVehicleModel());
                pre.setString(2,use.getVehicleCompany());
                pre.setString(3,use.getVehicleNumber());
                pre.setInt(4,use.getVehicleType());
                pre.setInt(5,use.getUser_id());
                pre.setInt(6,use.getVehicleYear());

                int i = pre.executeUpdate();
                return (i==1);}
            else return false;
        }catch (SQLException | DTBException | InvalidEntryException e){
            throw new DaoException(e);
        }


    }
    public static boolean removeVehicle(int id) throws DaoException{
        String query = "delete from vehicles where user_id = ?";
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query);){
            pre.setInt(1,id);
            int i = pre.executeUpdate();
            return i ==1 ;
        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }


    }
    public static Vehicle findVehicleByUserId(int id) throws DaoException{
        String query = "select * from vehicles where user_id = ?";
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query);){
            pre.setInt(1,id);
            ResultSet i = pre.executeQuery();
            Vehicle u = new Vehicle();
            while (i.next()){
                u.setVehicleCompany(i.getString("company"));
                u.setUser_id(i.getInt("user_id"));
                u.setVehicleYear(i.getInt("year"));
                u.setVehicleType(i.getInt("vehicleType"));
                u.setVehicleModel(i.getString("model"));
                u.setVehicleId(i.getInt("id"));
                u.setVehicleNumber(i.getString("vehicleNumber"));
            }
            return u;

        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }


    }
    public static List<Vehicle> getAllVehicles()throws DaoException{
        String query = "Select * from vehicles";
        ArrayList<Vehicle> vehicles =  new ArrayList<>();
        try ( Connection connect =  ConnectionDb.getConnection();
              PreparedStatement pre =  connect.prepareStatement(query);){
            ResultSet i = pre.executeQuery();

            while (i.next()){
                Vehicle u =  new Vehicle();
                u.setVehicleCompany(i.getString("company"));
                u.setUser_id(i.getInt("user_id"));
                u.setVehicleYear(i.getInt("year"));
                u.setVehicleType(i.getInt("vehicleType"));
                u.setVehicleModel(i.getString("model"));
                u.setVehicleId(i.getInt("id"));
                u.setVehicleNumber(i.getString("vehicleNumber"));
                vehicles.add(u);
            }



        }catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }
        return vehicles;
    }
//

}

