package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.DTBException;
import com.fssa.reparo.model.ServiceList;
import com.fssa.reparo.model.Services;
import com.fssa.reparo.util.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao extends ServiceListDao{

    public Services assignService(ResultSet rs) throws DAOException {
        Services service = new Services();
        try{
            service.setServiceListId(rs.getInt(serviceListId));
            service.setServiceAmount(rs.getInt("service_price"));
            service.setBookingId(rs.getInt("booking_id"));
            service.setLive(rs.getBoolean("is_live"));
            service.setAcceptStatus(rs.getBoolean("accept_status"));
            service.setCancelStatus(rs.getBoolean("cancel_status"));
            service.setCancelReason(rs.getString("cancel_reason"));
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
    public Services getServiceListById(int listId) throws DAOException{
        String query = "select * from services where service_list_id = ?";
        Services service =  new Services();
        try(Connection connection =  ConnectionDb.getConnection();PreparedStatement preStmt =  connection.prepareStatement(query)){
            preStmt.setInt(1,listId);
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
    public boolean updateServiceAmount(int serviceListId , int amount) throws DAOException {
        String query = "UPDATE services SET service_price = ? WHERE service_list_id = ? ";
        try(Connection connect = ConnectionDb.getConnection();
        PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setInt(1,amount);
            preStmt.setInt(2,serviceListId);
            return  preStmt.executeUpdate()==1;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public boolean updateCancelStatus(int serviceListId , boolean cancelStatus , String reason ) throws DAOException {
        String query = "UPDATE services SET cancel_status = ?,cancel_reason = ? WHERE service_list_id = ? ";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setBoolean(1,cancelStatus);
            preStmt.setString(2,reason);
            preStmt.setInt(3,serviceListId);
            return  preStmt.executeUpdate()==1;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }
    public boolean updateAcceptStatus(int serviceListId , boolean acceptStatus ) throws DAOException {
        String query = "UPDATE services SET accept_status = ? WHERE service_list_id = ? ";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)){
            preStmt.setBoolean(1,acceptStatus);
            preStmt.setInt(2,serviceListId);
            return  preStmt.executeUpdate()==1;
        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }

    }

    public List<Integer> getAllServicelist() throws DAOException {
        List<Integer> servicesList =  new ArrayList<>();
        String query = "select * from services";
        try(Connection connection = ConnectionDb.getConnection(); Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
               int id =  rs.getInt(serviceListId);

                servicesList.add(id);
            }

        }catch (SQLException | DTBException e){
            throw new DAOException(e);
        }
        return servicesList;
    }
   




}
class ServiceListDao{
    public static final String serviceListId= "service_list_id";


    public ServiceList assignServiceList(ResultSet rs) throws DAOException {
        ServiceList list = new ServiceList();
        try {
            list.setServiceListId(rs.getInt(serviceListId));
            list.setServiceId(rs.getInt("service_id"));
            list.setServiceName(rs.getString("service_name"));
            list.setPrice(rs.getInt("service_price"));
            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    public boolean createService(ServiceList service) throws DAOException {
        String query = "INSERT INTO service_list (service_list_id,service_name,service_price)\n" +
                "VALUES (?,?,?)";
        try(Connection connect = ConnectionDb.getConnection();
            PreparedStatement preStmt =  connect.prepareStatement(query)) {
            preStmt.setInt(1,service.getServiceListId());
            preStmt.setString(2,service.getServiceName());
            preStmt.setInt(3,service.getPrice());
            return  preStmt.executeUpdate() == 1 ;
        }catch (SQLException | DTBException e){
            throw new DAOException(e.getMessage());

        }
    }


    public List<ServiceList> getServicesFromListId(int id) throws DAOException{
        String query = "select * from service_list where service_list_id = ?";
        try(Connection connection = ConnectionDb.getConnection();PreparedStatement preStmt = connection.prepareStatement(query)){
            preStmt.setInt(1,id);
            List<ServiceList> listService = new ArrayList<>();
            ResultSet rs = preStmt.executeQuery();

            while(rs.next()){
                ServiceList ser = assignServiceList(rs);
                listService.add(ser);
            }

            rs.close();
            return listService;

        }catch (SQLException|DTBException e){
            throw new DAOException(e);
        }
    }
    public boolean updateServiceDetails(ServiceList  serv) throws DAOException {
        String query = "UPDATE service_list SET service_name = ? , service_price = ? WHERE service_id = ? ";
        try(Connection connection = ConnectionDb.getConnection();PreparedStatement preStmt = connection.prepareStatement(query)){
            preStmt.setString(1,serv.getServiceName());
            preStmt.setInt(2,serv.getPrice());
            preStmt.setInt(3,serv.getServiceId());
            return  preStmt.executeUpdate() == 1 ;
        }catch (SQLException|DTBException e){
            throw new DAOException(e);
        }

    }
    public int getTotalAmount(List<ServiceList> list){
        int amount = 0;
        if(list != null){
        for(ServiceList service : list){
            amount+= service.getPrice();

        }}
        return amount;
    }
    public boolean deleteEachService(int id) throws DAOException {
        String query = "DELETE FROM service_list WHERE service_id = ?";
        try (Connection connection = ConnectionDb.getConnection(); PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setInt(1,id);
            return preStmt.executeUpdate()==1;
        }catch (SQLException |DTBException e ){
            throw new DAOException(e);
        }

    }
    public ServiceList getEachServiceById(int id) throws DAOException {
        String query = "select * from service_list where service_id = ?";
        ServiceList list =  new ServiceList();
        try (Connection connection = ConnectionDb.getConnection(); PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()){
               list = assignServiceList(rs);
            }
            rs.close();
        }catch (SQLException |DTBException e ){
            throw new DAOException(e);
        }
        return list;
    }



}
