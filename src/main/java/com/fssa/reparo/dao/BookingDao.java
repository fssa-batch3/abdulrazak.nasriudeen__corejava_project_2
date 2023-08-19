package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BookingDao {
    private final String joinQuery = "SELECT * FROM ((bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id) INNER JOIN workshop ON workshop.id = bookings.workshop_id)";

   public Booking assignBooking(ResultSet rs) throws DAOException{
       try {
           Booking book = new Booking();
           VehicleDao vehicle =  new VehicleDao();
           WorkShopDao work  =  new WorkShopDao();
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
               book.setLive(rs.getBoolean("is_live"));
               book.setVehicle(vehicle.assignVehicle(rs));
               book.setWorkShop(work.assignWorkShop(rs));
           }
           return  book ;
       } catch (SQLException | DAOException e) {
           throw new DAOException(e);
       }

   }

    public  boolean insertBooking(Booking book) throws DAOException {
        String query =  "insert into bookings (vehicle_id,workshop_id,request_status,accept_status,problem,address,city,state) values (?,?,?,?,?,?,?,?)";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,book.getVehicleId());
            preStmt.setInt(2,book.getWorkShopId());
            preStmt.setBoolean(3,book.isRequestStatus());
            preStmt.setBoolean(4,book.isAcceptStatus());
            preStmt.setString(5,book.getProblem());
            preStmt.setString(6,book.getAddress());
            preStmt.setString(7,book.getCity());
            preStmt.setString(8,book.getState());
            return preStmt.executeUpdate()==1;
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }

    }
    public boolean  updateWorkshopId(int workshopId , int bookingId)throws  DAOException{
       String query = "update bookings set workshop_id = ? where booking_id = ?";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,workshopId);
            preStmt.setInt(2,bookingId);
            return preStmt.executeUpdate() == 1;
        }catch (DTBException | SQLException e){
            throw new DAOException(e);
        }

    }
    public  boolean removeBooking(int id)throws DAOException {

        String query = "delete from bookings where booking_id = ?";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,id);
            return  preStmt.executeUpdate() == 1 ;
        }catch (DTBException | SQLException e){
            throw new DAOException(e);
        }


    }
    public   boolean updateRequestSts(int bookingId , boolean status) throws DAOException {

        String query = "update bookings set request_status = ? where booking_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement preStmt = connect.prepareStatement(query)) {

            preStmt.setBoolean(1,status);
            preStmt.setInt(2,bookingId);
            return preStmt.executeUpdate() == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public   boolean updateAcceptSts(int bookingId , boolean status) throws DAOException {

        String query = "update bookings set accept_status = ? where booking_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement preStmt = connect.prepareStatement(query)) {

            preStmt.setBoolean(1,status);
            preStmt.setInt(2,bookingId);
            return preStmt.executeUpdate() == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public  Booking getBookingsByVehicleId(int vehicleId) throws DAOException {
       String query = joinQuery + " where vehicle_id = ? ";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement con = connect.prepareStatement(query)) {

            con.setInt(1,vehicleId);
            ResultSet rs = con.executeQuery();

            return assignBooking(rs);
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }
    }
    public ArrayList<Integer> findBookingNearByArea(String area) throws DAOException {
        ArrayList<Integer> bookings = new ArrayList<>();
        String query = joinQuery + " where city = ? AND is_live = true";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement preStmt = connect.prepareStatement(query)) {
            preStmt.setString(1 , area);
            ResultSet rs =  preStmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("booking_id");
                bookings.add(id);
            }
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);

        }
        return bookings ;
    }
    public Booking getBookingById(int id) throws DAOException{
        String query = joinQuery + " where booking_id = ? ";
        try (Connection connect =  ConnectionDb.getConnection();PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setInt(1,id);
            ResultSet rs =  preStmt.executeQuery();


            return assignBooking(rs);
        }catch (DTBException | SQLException e){
            throw  new DAOException(e);
        }

    }









}
