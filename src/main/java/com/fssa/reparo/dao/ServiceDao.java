package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.DTBException;
import com.fssa.reparo.model.Services;
import com.fssa.reparo.util.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDao {

    public Services assignService(ResultSet rs) throws DAOException {
        Services service = new Services();
        try{
            service.setServiceListId(rs.getInt("service_list_id"));
            service.setServiceAmount(rs.getInt("service_price"));
            service.setBookingId(rs.getInt("booking_id"));
            service.setLive(rs.getBoolean("is_live"));
            service.setAcceptStatus(rs.getBoolean("accept_status"));
            service.setCancelStatus(rs.getBoolean("cancel_status"));
            return service;
        }catch (SQLException e){
            throw new DAOException(e);
        }

    }

    public boolean createServiceList(int bookingId) throws DAOException {
        String query = "INSERT INTO services (booking_id,Accept_status,is_live)\n" +
                "VALUES (?,?,?)";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,bookingId);
            preStmt.setBoolean(2,false);
            preStmt.setBoolean(3,true);
          return  preStmt.executeUpdate() == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e.getMessage());

        }


    }
    public Services getServiceListByBookingId(int bookingId) throws DAOException{
        String query = "select * from services where booking_id = ?";
        Services service =  new Services();
        try(Connection connection =  ConnectionDb.getConnection();PreparedStatement preStmt =  connection.prepareStatement(query)){
            preStmt.setInt(1,bookingId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                service = assignService(rs);
            }
            rs.close();
        return service;

        }catch (DTBException | SQLException  e){
            throw  new DAOException(e);

        }



    }


}
