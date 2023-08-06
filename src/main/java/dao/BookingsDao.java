package dao;

import exception.DaoException;
import model.Bookings;
import util.ConnectionDb;
import util.DTBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingsDao {

    public static boolean insertBooking(Bookings book) throws DaoException{
        try {
            Connection connect = ConnectionDb.getConnection();
            String query =  "insert into bookings (vehicle_id,customer_id,workshop_id,request_status,accept_status,problem,address,city,state) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre =  connect.prepareStatement(query);
            pre.setInt(1,book.getVehicleId());
            pre.setInt(2,book.getCustomerId());
            pre.setInt(3,book.getWorkShopId());
            pre.setInt(4,book.getVehicleId());
            pre.setBoolean(5,book.isRequestStatus());
            pre.setBoolean(6,book.isAcceptStatus());
            pre.setString(7,);



        } catch (DTBException | SQLException e) {
            throw new DaoException(e);
        }
   return true;
    }
}
