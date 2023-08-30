package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
   public Booking assignBooking(ResultSet rs) throws DAOException{
       try {
           Booking book = new Booking();
           VehicleDao vehicle =  new VehicleDao();
           WorkShopDao work  =  new WorkShopDao();

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

           return  book ;
       } catch (SQLException | DAOException e) {
           throw new DAOException(e);
       }

   }

    public  boolean insertBooking(Booking book) throws DAOException {
        String query =  "insert into bookings (vehicle_id,request_status,problem,address,city,state) values (?,?,?,?,?,?)";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,book.getVehicleId());
            preStmt.setBoolean(2,book.isRequestStatus());
            preStmt.setString(3,book.getProblem());
            preStmt.setString(4,book.getAddress());
            preStmt.setString(5,book.getCity());
            preStmt.setString(6,book.getState());
            return preStmt.executeUpdate()==1;
        } catch (DTBException | SQLException e) {
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
    public   boolean updateAcceptSts(int bookingId , int workshopId , boolean status) throws DAOException {

        String query = "update bookings set accept_status = ?,workshop_id = ? where booking_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement preStmt = connect.prepareStatement(query)) {

            preStmt.setBoolean(1,status);
            preStmt.setInt(2,workshopId);
            preStmt.setInt(3,bookingId);
            return preStmt.executeUpdate() == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public  Booking getBookingByVehicleId(int vehicleId) throws DAOException {
       String query = "SELECT * FROM ((bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id) INNER JOIN workshop ON workshop.id = bookings.workshop_id) where vehicle_id = ? ";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement con = connect.prepareStatement(query)) {

            con.setInt(1,vehicleId);
            ResultSet rs = con.executeQuery();

            return assignBooking(rs);
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }
    }
    public List<Integer> findBookingNearByArea(String area) throws DAOException {
        List<Integer> bookings = new ArrayList<>();
        String query = "SELECT * FROM ((bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id) INNER JOIN workshop ON workshop.id = bookings.workshop_id) where city = ? AND is_live = true";
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
        String query = "SELECT * FROM (bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id)  where bookings.booking_id = ?";
        try (Connection connect =  ConnectionDb.getConnection();PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setInt(1,id);
            ResultSet rs =  preStmt.executeQuery();


            return assignBooking(rs);
        }catch (DTBException | SQLException e){
            throw  new DAOException(e);
        }

    }
    public List<Booking> getAllBookings()throws DAOException {
        String query = "SELECT * FROM ((bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id) INNER JOIN workshop ON workshop.id = bookings.workshop_id) where booking_id = ? ";

        List<Booking> bookings = new ArrayList<>();
        try (Connection connect = ConnectionDb.getConnection(); Statement statement = connect.createStatement();ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                Booking book = assignBooking(result);
                bookings.add(book);
            }
            return bookings;
        } catch (SQLException | DTBException ex) {
            throw new DAOException(ex);
        }


    }


}
