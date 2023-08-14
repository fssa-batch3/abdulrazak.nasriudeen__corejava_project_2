package dao;
import exception.DAOException;
import model.Booking;
import util.ConnectionDb;
import exception.DTBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BookingDao {
    public  boolean insertBooking(Booking book) throws DAOException {
        String query =  "insert into bookings (vehicle_id,workshop_id,request_status,accept_status,problem,address,city,state) values (?,?,?,?,?,?,?,?)";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement pre =  connect.prepareStatement(query)) {
            pre.setInt(1,book.getVehicleId());
            pre.setInt(2,book.getWorkShopId());
            pre.setBoolean(3,book.isRequestStatus());
            pre.setBoolean(4,book.isAcceptStatus());
            pre.setString(5,book.getProblem());
            pre.setString(6,book.getAddress());
            pre.setString(7,book.getCity());
            pre.setString(8,book.getState());
            int i =  pre.executeUpdate();
            return i==1;
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }

    }
    public  boolean removeBooking(int id)throws DAOException {

        String query = "delete from bookings where vehicle_id = ?";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement pre =  connect.prepareStatement(query)) {


            pre.setInt(1,id);
        int  i =  pre.executeUpdate()  ;
            return i == 1 ;

        }catch (DTBException | SQLException e){
            throw new DAOException(e);


        }


    }
    public   boolean updateRequestSts(int j , boolean ch) throws DAOException {

        String query = "update bookings set request_status = ? where vehicle_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement pre = connect.prepareStatement(query)) {

            pre.setBoolean(1,ch);
            pre.setInt(2,j);
          int  i = pre.executeUpdate();
            return i == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public   boolean updateAcceptSts(int j , boolean ch) throws DAOException {

        String query = "update bookings set accept_status = ? where vehicle_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement pre = connect.prepareStatement(query)) {

            pre.setBoolean(1,ch);
            pre.setInt(2,j);
         int   i = pre.executeUpdate();
            return i == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public  Booking getBookingsByVehicleId(int id) throws DAOException {
        Booking book = new Booking();
        String query = "select * from bookings where vehicle_id = ?";
        try(Connection connect = ConnectionDb.getConnection();

            PreparedStatement con = connect.prepareStatement(query)) {

            con.setInt(1,id);
            ResultSet rs = con.executeQuery();
            while(rs.next()){
                book.setBookingId(rs.getInt("booking_id"));
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
            throw new DAOException(e);
        }
    }
    public ArrayList<Integer> findBookingNearByArea(String area) throws DAOException {
        ArrayList<Integer> bookings = new ArrayList<>();
        String query = "Select * from bookings where city = ? AND is_live = true";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement pre = connect.prepareStatement(query)) {
            pre.setString(1 , area);
            ResultSet rs =  pre.executeQuery();
            while(rs.next()){
                int id = rs.getInt("booking_id");
                bookings.add(id);
            }

        } catch (DTBException | SQLException e) {
            throw new DAOException(e);

        }
        return bookings ;
    }









}
