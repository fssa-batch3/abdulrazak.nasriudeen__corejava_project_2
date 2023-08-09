package dao;

import exception.DaoException;
import model.Bookings;
import util.ConnectionDb;
import util.DTBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingsDao {

    public static boolean insertBooking(Bookings book) throws DaoException{
        String query =  "insert into bookings (vehicle_id,customer_id,workshop_id,request_status,accept_status,problem,address,city,state) values (?,?,?,?,?,?,?,?,?)";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre =  connect.prepareStatement(query);) {

            pre.setInt(1,book.getVehicleId());
            pre.setInt(2,book.getCustomerId());
            pre.setInt(3,book.getWorkShopId());
            pre.setBoolean(4,book.isRequestStatus());
            pre.setBoolean(5,book.isAcceptStatus());
            pre.setString(6,book.getProblem());
            pre.setString(7,book.getAddress());
            pre.setString(8,book.getCity());
            pre.setString(9,book.getState());
            int i =  pre.executeUpdate();
            return i==1 ;



        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }

    }
    public static boolean removeBooking(int id)throws DaoException{
        int i = 0;
        String query = "delete from bookings where customer_id = ?";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement pre =  connect.prepareStatement(query);) {


            pre.setInt(1,id);
          i =  pre.executeUpdate()  ;
            

        }catch (DTBException | SQLException e){
            throw new DaoException(e);


        }

    return i == 1 ; 
    }
    public static  boolean updateRequestSts(int j , boolean ch) throws DaoException{
        int i = 0 ;
        String query = "update bookings set request_status = ? where customer_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement pre = connect.prepareStatement(query);) {

            pre.setBoolean(1,ch);
            pre.setInt(2,j);
            i = pre.executeUpdate();
            return i == 1 ;
        }catch (SQLException | DTBException e){
            throw new DaoException(e);

        }
    }
    public static  boolean updateAcceptSts(int j , boolean ch) throws DaoException{
        int i = 0 ;
        String query = "update bookings set accept_status = ? where customer_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement pre = connect.prepareStatement(query);) {

            pre.setBoolean(1,ch);
            pre.setInt(2,j);
            i = pre.executeUpdate();
            return i == 1 ;
        }catch (SQLException | DTBException e){
            throw new DaoException(e);

        }
    }
    public static Bookings getBookingsByCustomerId(int id) throws DaoException {
        Bookings book = new Bookings();
        String query = "select * from bookings where customer_id = ?";
        try(Connection connect = ConnectionDb.getConnection();

            PreparedStatement con = connect.prepareStatement(query);) {

            con.setInt(1,id);
            ResultSet rs = con.executeQuery();
            while(rs.next()){
                book.setBookingId(rs.getInt("booking_id"));
                book.setCustomerId(rs.getInt("customer_id"));
                book.setCity(rs.getString("city"));
                book.setVehicleId(rs.getInt("vehicle_id"));
                book.setState(rs.getString("state"));
                book.setAddress(rs.getString("address"));
                book.setWorkShopId(rs.getInt("workshop_id"));
                book.setProblem(rs.getString("problem"));
                book.setAcceptStatus(rs.getBoolean("accept_status"));
                book.setRequestStatus(rs.getBoolean("request_status"));


            }
            return book;
        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }
    }
    public static List<Bookings> getBookingsByArea(String area) throws DaoException {
        String query = "select * from bookings where city = ?";

        try( Connection connect = ConnectionDb.getConnection();
             PreparedStatement con = connect.prepareStatement(query);) {

            con.setString(1,area);
            ResultSet rs = con.executeQuery();
            ArrayList<Bookings> bookings= new ArrayList<>();
            while(rs.next()){
                Bookings book = new Bookings();
                book.setBookingId(rs.getInt("booking_id"));
                book.setCustomerId(rs.getInt("customer_id"));
                book.setCity(rs.getString("city"));
                book.setVehicleId(rs.getInt("vehicle_id"));
                book.setState(rs.getString("state"));
                book.setAddress(rs.getString("address"));
                book.setWorkShopId(rs.getInt("workshop_id"));
                book.setProblem(rs.getString("problem"));
                book.setAcceptStatus(rs.getBoolean("accept_status"));
                book.setRequestStatus(rs.getBoolean("request_status"));
                bookings.add(book);


            }
            return bookings;
        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }
    }
    public static  ArrayList<Integer> getAllWorkShopByArea(String area) throws DaoException{
        String query = "Select * from workshops where city = ?";
        try(Connection connection =  ConnectionDb.getConnection();PreparedStatement pre = connection.prepareStatement(query)){
            pre.setString(1,area);
            ResultSet rs = pre.executeQuery();
            ArrayList<Integer> arr = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                arr.add(id);
            }
            return arr ;

        }catch (DTBException | SQLException e){
            throw new DaoException(e);
        }

    }


}
