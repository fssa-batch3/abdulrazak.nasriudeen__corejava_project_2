package dao;
import exception.DAOException;
import model.WorkShop;
import util.ConnectionDb;
import exception.DTBException;
import java.sql.*;
import java.util.ArrayList;


public class WorkShopDao {

    public  boolean insertWorkShop(WorkShop work) throws DAOException {
        // This method is used to create user data in db table
        String  query = "insert into workshop (name,number,password,address,city,state,workshop_type) values (?,?,?,?,?,?,?)";

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
            throw new DAOException(e);
        }

    }
    public  WorkShop findWorkShopByNumber(long num) throws DAOException {

        String query =  "Select * from workshop where number = ?";
        WorkShop work = new WorkShop();
        String number = Long.toString(num);

        try(

            Connection connect = ConnectionDb.getConnection();


            PreparedStatement prep =  connect.prepareStatement(query);
        ){

            prep.setString(1,number);
            ResultSet    rs = prep.executeQuery();
            if(rs.next()){
                work.setName(rs.getString("name"));
                long lNum = Long.parseLong(rs.getString("phone"));
                work.setNumber(lNum);
                work.setPassword(rs.getString("password"));
                work.setId(rs.getInt("id"));
                work.setAddress(rs.getString("address"));
                work.setCity(rs.getString("city"));
                work.setState(rs.getString("state"));
                work.setType(rs.getInt("workshop_type"));


            }
            return work;

        } catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public  boolean removeWorkShopAccount(long number)throws DAOException {
        String query = "delete from workshop where number = ? ;";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre =  connect.prepareStatement(query);
        ){



            String num = Long.toString(number);
            pre.setString(1,num);
            int i = pre.executeUpdate();
            return i == 1;



        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }


    }
    public  boolean updateWorkShopPassword(Long num , String password)throws DAOException {
        // this method update the data of the user's password ;
        String query = "update workshop set password = ? where number = ?";
        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){
            pre.setString(1,password);
            String number = Long.toString(num);
            pre.setString(2,number);
            int i = pre.executeUpdate();
            return i==1;
        }catch(SQLException | DTBException e){
            throw new DAOException(e);
        }
    }
    public  ArrayList<WorkShop> getAllWorkShops()throws DAOException {
        String query = "Select * from workshop";
        try (Connection connect = ConnectionDb.getConnection();
             PreparedStatement pre = connect.prepareStatement(query);){
            ResultSet rs =  pre.executeQuery();
            ArrayList<WorkShop> workshops = new ArrayList<>();
            while(rs.next()){
                WorkShop work = new WorkShop();
                work.setName(rs.getString("name"));
                work.setCity(rs.getString("city"));
                String num  = rs.getString("number");

                work.setNumber(Long.parseLong(num));
                work.setAddress(rs.getString("address"));
                work.setState(rs.getString("state"));
                work.setType(rs.getInt("workshop_type"));
                work.setId(rs.getInt("id"));
                workshops.add(work);
            }
            return workshops;

        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }
    }
    public   ArrayList<Integer> findWorkshopByArea(String area) throws DAOException {
        String query = "select * from workshop where city = ?";

        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement con = connect.prepareStatement(query);) {

            con.setString(1,area);
            ResultSet rs = con.executeQuery();
            ArrayList<Integer> workshops= new ArrayList<>();
            while(rs.next()){
                int book = rs.getInt("id");

                workshops.add(book);


            }
            return workshops;
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }
    }

}
