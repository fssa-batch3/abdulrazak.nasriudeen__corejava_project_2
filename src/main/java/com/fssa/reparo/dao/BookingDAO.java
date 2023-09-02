package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.util.ConnectionDb;
import com.fssa.reparo.exception.DTBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
   public Booking assignBooking(ResultSet rs) throws DAOException{
       try {
           Booking book = new Booking();
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


           return  book ;
       } catch (SQLException  e) {
           throw new DAOException(e);
       }

   }

    public void insertBooking(Booking book) throws DAOException {
        String query =  "insert into bookings (vehicle_id,request_status,problem,address,city,state,is_live,accept_status) values (?,?,?,?,?,?,?,?)";

        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,book.getVehicleId());
            preStmt.setBoolean(2,book.isRequestStatus());
            preStmt.setString(3,book.getProblem());
            preStmt.setString(4,book.getAddress());
            preStmt.setString(5,book.getCity());
            preStmt.setString(6,book.getState());
            preStmt.setBoolean(7,book.isLive());
            preStmt.setBoolean(8,book.isAcceptStatus());


            preStmt.executeUpdate();
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);
        }

    }
    public void removeBooking(int id)throws DAOException {

        String query = "delete from bookings where vehicle_id = ?";
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,id);
              preStmt.executeUpdate();
        }catch (DTBException | SQLException e){
            throw new DAOException(e);
        }


    }
    public void updateRequestSts(int bookingId , boolean status) throws DAOException {

        String query = "update bookings set request_status = ? where booking_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement preStmt = connect.prepareStatement(query)) {

            preStmt.setBoolean(1,status);
            preStmt.setInt(2,bookingId);
            preStmt.executeUpdate();
        }catch (SQLException | DTBException e){
            throw new DAOException(e);

        }
    }
    public void updateAcceptSts(int bookingId , int workshopId , boolean status) throws DAOException {

        String query = "update bookings set accept_status = ?,workshop_id = ? where booking_id = ? ";

        try (Connection connect = ConnectionDb.getConnection();PreparedStatement preStmt = connect.prepareStatement(query)) {

            preStmt.setBoolean(1,status);
            preStmt.setInt(2,workshopId);
            preStmt.setInt(3,bookingId);
            preStmt.executeUpdate();
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
        public List<Booking> findBookingNearByCity(String area) throws DAOException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM ((bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id))  where city = ? AND is_live = true AND accept_Status = false" ;
        try (Connection connect = ConnectionDb.getConnection(); PreparedStatement preStmt = connect.prepareStatement(query)) {
            preStmt.setString(1 , area);
            ResultSet rs =  preStmt.executeQuery();
            while(rs.next()){
                Booking booking = assignBooking(rs);
                bookings.add(booking);
            }
        } catch (DTBException | SQLException e) {
            throw new DAOException(e);

        }
        return bookings ;
    }
    public Booking getBookingById(int id) throws DAOException{
       String query = "SELECT * FROM (bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id)  where bookings.booking_id = ?;";
        Booking booking = null;
        try (Connection connect =  ConnectionDb.getConnection();PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setInt(1,id);
            ResultSet rs =  preStmt.executeQuery();
            if(rs.next())booking = assignBooking(rs);



            return booking;
        }catch (DTBException | SQLException e){
            throw  new DAOException(e);
        }

    }
    public List<Booking> getAllBookings()throws DAOException {
        String query = "SELECT * FROM bookings ";

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
    public Booking findUnAcceptedLiveBookingById(int id) throws DAOException{
        Booking booking =  new Booking();
        VehicleDAO vehicleDAO =  new VehicleDAO();
        String query =  "SELECT * FROM bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id WHERE booking_id = ? AND is_live = true AND accept_status = false";
        try(Connection connection =  ConnectionDb.getConnection();PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()){
                booking  = assignBooking(rs);
                booking.setVehicle(vehicleDAO.assignVehicle(rs));
            }
            rs.close();
        } catch (SQLException |DTBException e) {
            throw new DAOException(e);
        }
        return booking;
    }
    public List<Booking>getAllUnAcceptedBooking() throws DAOException{
        String query =  "SELECT * FROM bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id where is_live = true AND accept_status = false";
        List<Booking> bookings = new ArrayList<>();
        VehicleDAO vehicleDAO =  new VehicleDAO();
        try(Connection connection =  ConnectionDb.getConnection();Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
              Booking booking  = assignBooking(rs);
              booking.setVehicle(vehicleDAO.assignVehicle(rs));
              bookings.add(booking);
            }
            rs.close();
        } catch (SQLException |DTBException e) {
            throw new DAOException(e);
        }
        return bookings;

    }
    public Booking findAcceptedLiveBookingById(int id) throws DAOException{
        Booking booking =  new Booking();
        VehicleDAO vehicleDAO =  new VehicleDAO();
        WorkShopDAO workShopDAO = new WorkShopDAO();
        String query =  "SELECT * FROM bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id INNER JOIN workshop ON bookings.workshop_id = workshop.id WHERE bookings.booking_id = ? AND is_live = true AND accept_status = true";
        try(Connection connection =  ConnectionDb.getConnection();PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()){
                booking  = assignBooking(rs);
                booking.setVehicle(vehicleDAO.assignVehicle(rs));
                booking.setWorkShop(workShopDAO.assignWorkShop(rs));
            }
            rs.close();
        } catch (SQLException |DTBException e) {
            throw new DAOException(e);
        }
        return booking;
    }
    public List<Booking>getAllAcceptedBooking() throws DAOException{
        String query =  "SELECT * FROM bookings INNER JOIN vehicles ON bookings.vehicle_id = vehicles.id INNER JOIN workshop ON bookings.workshop_id = workshop.id WHERE is_live = true AND accept_status = true";
        List<Booking> bookings = new ArrayList<>();
        VehicleDAO vehicleDAO =  new VehicleDAO();
        WorkShopDAO workShopDAO = new WorkShopDAO();
        try(Connection connection =  ConnectionDb.getConnection();Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Booking booking  = assignBooking(rs);
                booking.setVehicle(vehicleDAO.assignVehicle(rs));
                booking.setWorkShop(workShopDAO.assignWorkShop(rs));
                bookings.add(booking);
            }
            rs.close();
        } catch (SQLException |DTBException e) {
            throw new DAOException(e);
        }
        return bookings;

    }


}
