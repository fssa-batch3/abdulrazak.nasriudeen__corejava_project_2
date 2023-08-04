package dao;
import exception.DaoException;
import model.User;
import util.ConnectionDb;
import util.DTBException;
import validation.InvalidEntryException;
import validation.UserValidation;

import java.sql.*;
public class UserDao extends VehicleDao{
    public static boolean insertUser(User use) throws DaoException {
        // This method is used to create user data in db table
        try {
            Connection connect = ConnectionDb.getConnection();
            String  query = "insert into user (name,phone,password) values (?,?,?)";
            PreparedStatement pre = connect.prepareStatement(query);
            pre.setString(1, use.getName());
            String num =  Long.toString(use.getNumber());

            pre.setString(2,num);

            pre.setString(3,use.getPassword());
            int i = pre.executeUpdate();
            return (i==1);
        }catch (SQLException | DTBException e){
            throw new DaoException(e);
        }

    }
    public static boolean updateUserPassword(Long num , String password)throws DaoException{
        // this method update the data of the user's password ;
        try{
            Connection connect = ConnectionDb.getConnection();
            String query = "update user set password = ? where phone = ?";
            PreparedStatement pre = connect.prepareStatement(query);
            pre.setString(1,password);
            String number = Long.toString(num);
            pre.setString(2,number);
            int i = pre.executeUpdate();
            return i==1;
        }catch(SQLException | DTBException e){
            throw new DaoException(e);
        }
    }
    public static boolean deleteUserAccount(long number)throws DaoException{
        try {
            Connection connect = ConnectionDb.getConnection();
            String query = "delete from user where phone = ? ;";
            PreparedStatement pre = connect.prepareStatement(query);
            String num = Long.toString(number);
            pre.setString(1,num);
            int i = pre.executeUpdate();
            return i == 1;



        }catch (SQLException | DTBException e){
            throw new DaoException(e);
        }


    }
    public static User findUserByNumber(long num) throws DaoException {
        ResultSet rs = null;
        try{
            User work = new User();
            Connection connect = ConnectionDb.getConnection();
            String query =  "Select * from User where phone = ?";
            String number = Long.toString(num);
            Statement pre = connect.createStatement();
            PreparedStatement prep =  connect.prepareStatement(query);

            prep.setString(1,number);
            rs = prep.executeQuery();
            if(rs.next()){
                work.setName(rs.getString("name"));
                long lNum = Long.parseLong(rs.getString("phone"));
                work.setNumber(lNum);
                work.setPassword(rs.getString("password"));
                work.setId(rs.getInt("id"));


            }
            return work;

        } catch (SQLException | DTBException e){
            throw new DaoException(e);
        }

    }



}
class VehicleDao{

    public static boolean addVehicle(User use) throws DaoException {
        try {
            UserValidation usValid = new UserValidation();
            if(usValid.userValidVehicle(use)){


            Connection connect = ConnectionDb.getConnection();
            String  query = "insert into vehicles (model,company,vehicleNumber,vehicleType,user_id,year) values (?,?,?,?,?,?)";
            PreparedStatement pre = connect.prepareStatement(query);
            pre.setString(1,use.getVehicleModel());
            pre.setString(2,use.getVehicleCompany());
            pre.setString(3,use.getVehicleNumber());
            pre.setInt(4,use.getVehicleType());
            pre.setInt(5,use.getId());
            pre.setInt(6,use.getVehicleYear());

            int i = pre.executeUpdate();
            return (i==1);}
            else return false;
        }catch (SQLException | DTBException | InvalidEntryException e){
            throw new DaoException(e);
        }


    }



}

