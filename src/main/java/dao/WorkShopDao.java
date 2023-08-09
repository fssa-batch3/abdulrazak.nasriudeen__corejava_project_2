package dao;

import exception.DaoException;
import model.WorkShop;
import util.ConnectionDb;
import util.DTBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkShopDao {

    public static boolean insertWorkShop(WorkShop work) throws DaoException {
        // This method is used to create user data in db table
        String  query = "insert into workshops (name,phone,password,address,city,state,workShoptype) values (?,?,?,?,?,?,?)";

        try (
            Connection connect = ConnectionDb.getConnection();
                PreparedStatement pre = connect.prepareStatement(query);)
        {
            pre.setString(1, work.getName());
            String num =  Long.toString(work.getNumber());

            pre.setString(2,num);

            pre.setString(3,work.getPassword());
            pre.setString(4,work.getAddress());
            pre.setString(5,work.getCity());
            pre.setString(6,work.getState());
            pre.setInt(7,work.getType());
            int i = pre.executeUpdate();
            return (i==1);
        }catch (SQLException | DTBException e){
            throw new DaoException(e);
        }

    }
    public static WorkShop findWorkShopByNumber(long num) throws DaoException {
        ResultSet rs = null;
        String query =  "Select * from Workshops where phone = ?";
        WorkShop work = new WorkShop();
        String number = Long.toString(num);

        try(

            Connection connect = ConnectionDb.getConnection();


            PreparedStatement prep =  connect.prepareStatement(query);
        ){

            prep.setString(1,number);
            rs = prep.executeQuery();
            if(rs.next()){
                work.setName(rs.getString("name"));
                long lNum = Long.parseLong(rs.getString("phone"));
                work.setNumber(lNum);
                work.setPassword(rs.getString("password"));
                work.setId(rs.getInt("id"));
                work.setAddress(rs.getString("address"));
                work.setCity(rs.getString("city"));
                work.setState(rs.getString("state"));
                work.setType(rs.getInt("workShoptype"));


            }
            return work;

        } catch (SQLException | DTBException e){
            throw new DaoException(e);
        }

    }
    public static boolean removeWorkShopAccount(long number)throws DaoException{
        String query = "delete from workshops where phone = ? ;";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre =  connect.prepareStatement(query);
        ){



            String num = Long.toString(number);
            pre.setString(1,num);
            int i = pre.executeUpdate();
            return i == 1;



        }catch (SQLException | DTBException e){
            throw new DaoException(e);
        }


    }
    public static boolean updateWorkShopPassword(Long num , String password)throws DaoException{
        // this method update the data of the user's password ;
        String query = "update workshops set password = ? where phone = ?";
        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){
            pre.setString(1,password);
            String number = Long.toString(num);
            pre.setString(2,number);
            int i = pre.executeUpdate();
            return i==1;
        }catch(SQLException | DTBException e){
            throw new DaoException(e);
        }
    }
    public static List<WorkShop> getAllWorkShops()throws DaoException{
        String query = "Select * from workshops";
        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){
            ResultSet rs =  pre.executeQuery();
            List<WorkShop> workshops = new ArrayList<>();
            while(rs.next()){
                WorkShop work = new WorkShop();
                work.setName(rs.getString("name"));
                work.setCity(rs.getString("city"));
                String num  = rs.getString("phone");

                work.setNumber(Long.parseLong(num));
                work.setAddress(rs.getString("address"));
                work.setState(rs.getString("state"));
                work.setType(rs.getInt("workShoptype"));
                work.setId(rs.getInt("id"));
                workshops.add(work);
            }
            return workshops;

        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }
    }

}
