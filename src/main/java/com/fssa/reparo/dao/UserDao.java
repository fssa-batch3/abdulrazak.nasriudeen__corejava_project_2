package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    public User assignUser(ResultSet rs) throws DAOException{
        User user  = new User();
        try {
            if(rs.next()){
                user.setName(rs.getString("name"));
                long lNum = Long.parseLong(rs.getString("number"));
                user.setNumber(lNum);
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    public boolean insertUser(User use) throws DAOException {
        // This method is used to create user data in db table
        String  query = "insert into user (name,number,password) values (?,?,?)";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query)){

            pre.setString(1, use.getName());
            String num =  Long.toString(use.getNumber());

            pre.setString(2,num);

            pre.setString(3,use.getPassword());
            int i = pre.executeUpdate();
            return (i==1);
        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public  boolean updateUserPassword(Long num , String password)throws DAOException {
        // this method update the data of the user's password ;
        String query = "update user set password = ? where number = ?";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre = connect.prepareStatement(query)){

            pre.setString(1,password);
            String number = Long.toString(num);
            pre.setString(2,number);
            int i = pre.executeUpdate();
            return i==1;
        }catch(SQLException | DTBException e){
            throw new DAOException(e);
        }
    }
    public  boolean removeUser(long number)throws DAOException {
        String query = "delete from user where number = ? ;";

        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query)){

            String num = Long.toString(number);
            pre.setString(1,num);
            int i = pre.executeUpdate();
            return i == 1;

        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }


    }
    public  User findUserById(int id) throws DAOException {

        String query =  "Select * from user where id = ?";

        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement prep =  connect.prepareStatement(query)){


            prep.setInt(1,id);
            ResultSet   rs = prep.executeQuery();

            return assignUser(rs);

        } catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public  User findUserByNumber(long num) throws DAOException {

        String query =  "Select * from user where number = ?";
        String number = Long.toString(num);
        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement prep =  connect.prepareStatement(query)){


            prep.setString(1,number);
            ResultSet   rs = prep.executeQuery();

            return assignUser(rs);

        } catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public  ArrayList<User> getAllUser() throws DAOException {

        String query = "select * from user";
        ArrayList<User> users = new ArrayList<>();
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement prep =  connect.prepareStatement(query)
        ){
            ResultSet  rs = prep.executeQuery();
            while(rs.next()){
                User work = assignUser(rs);
                users.add(work);
            }



        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }
        return users;

    }



}

