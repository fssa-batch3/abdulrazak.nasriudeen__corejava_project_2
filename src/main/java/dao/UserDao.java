package dao;
import exception.DaoException;
import model.User;
import util.ConnectionDb;
import util.DTBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends VehicleDao{
    public static boolean insertUser(User use) throws DaoException {
        // This method is used to create user data in db table
        String  query = "insert into user (name,number,password) values (?,?,?)";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){

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
        String query = "update user set password = ? where number = ?";

        try(Connection connect = ConnectionDb.getConnection();
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
    public static boolean removeUser(long number)throws DaoException{
        String query = "delete from user where number = ? ;";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){

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
        String query =  "Select * from user where number = ?";
        String number = Long.toString(num);
        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement prep =  connect.prepareStatement(query);){
            User work = new User();



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
    public static ArrayList<User> getAllUser() throws  DaoException{
        ResultSet rs = null;
        String query = "select * from user";
        ArrayList<User> users = new ArrayList<>();
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement prep =  connect.prepareStatement(query);


        ){
            rs = prep.executeQuery();
            while(rs.next()){
                User work = new User();
                work.setName(rs.getString("name"));
                long lNum = Long.parseLong(rs.getString("phone"));
                work.setNumber(lNum);
                work.setPassword(rs.getString("password"));
                work.setId(rs.getInt("id"));
                users.add(work);


            }



        }catch (SQLException | DTBException e){
            throw new DaoException(e);
        }
        return users;

    }



}

