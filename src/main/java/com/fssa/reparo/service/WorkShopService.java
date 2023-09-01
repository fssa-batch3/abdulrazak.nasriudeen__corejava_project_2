package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDAO;
import com.fssa.reparo.datamapper.WorkShopMapper;
import com.fssa.reparo.dto.WorkShopDTO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.WorkShop;
import com.fssa.reparo.validation.Validations;
import com.fssa.reparo.validation.WorkShopValidation;
import java.util.ArrayList;
import java.util.List;
public class WorkShopService {
    private final WorkShopDAO workShopDao = new WorkShopDAO();

    /**
     * Registers a workshop by validating the input and inserting it into the database.
     *
     * @param dto The Workshop object to be registered.
     * @return True if the workshop is successfully registered, false otherwise.
     * @throws ServiceException If there is an issue with database access or validation.
     */
    public  boolean registerWorkShop(WorkShopDTO dto)throws ServiceException{
        WorkShopValidation validate = new WorkShopValidation();
        WorkShopMapper map = new WorkShopMapper();
        WorkShop workShop =  map.mapDTOToWorkShop(dto);

            try {
                if(validate.isValidWorkshop(workShop)) {
                    WorkShopDAO work = new WorkShopDAO();
                    return work.insertWorkShop(workShop );

                }

                return false;


            }catch (DAOException | ValidationException e){
               throw  new ServiceException(e) ;
            }

        }


    /**
     * Logs in a workshop user by validating the credentials.
     *
     * @param num The user's Phone   number.
     * @param pass The user's password.
     * @return The result of the login attempt:
     *         - Positive workshop ID if login is successful.
     *         - Negative values for various login failure cases, e.g.:
     *           -1 if validation fails.
     * @throws ServiceException If there is an issue with validation.
     */
    public int loginWorkShop(long num,String pass) throws ServiceException {
        WorkShopValidation validate = new WorkShopValidation();
        try {
           return validate.getWorkShop(num , pass);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
    }
    public boolean logOutWorkShop(int id) throws ServiceException{
        try {
            WorkShop work = workShopDao.getWorkShopsById(id);
            if(work.isLogin()){
            return workShopDao.updateLoginStatus(id,false);
            }
            return false;
        } catch (DAOException e) {
            throw new ServiceException("User is not present");
        }
    }
    /**
     * Retrieves a list of all workshops stored in the database.
     *
     * @return A List of WorkShop objects representing all workshops in the database.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public List<WorkShopDTO> getAllWorkShop() throws ServiceException{
        WorkShopDAO dao  = new WorkShopDAO();
        WorkShopMapper map = new WorkShopMapper();
        try {
            List<WorkShop> workShops = dao.getAllWorkShops();
            List<WorkShopDTO> workShopDTOS = new ArrayList<>();
            for(WorkShop work :workShops){
                WorkShopDTO dto =   map.mapWorkShopToDTO(work);
                workShopDTOS.add(dto);
            }


            return workShopDTOS;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * Retrieves a list of workshop IDs based on the specified city (area).
     *
     * @param city The city (area) for which workshops are to be retrieved.
     * @return A List of integers representing workshop IDs in the specified city.
     *         The list may be empty if no workshops are found in the given city.
     * @throws ServiceException If there is an issue with input validation or accessing the database.
     */
    public List<WorkShopDTO> getWorkShopByArea(String city) throws ServiceException{
        Validations validate =  new Validations();
        WorkShopDAO dao =  new WorkShopDAO() ;
        List<WorkShopDTO> workShopDTOS  =  new ArrayList<>();
        try {
            if(validate.stringValidation(city)){
                List<WorkShop> workShops =  dao.findWorkshopsByArea(city);
                WorkShopMapper map = new WorkShopMapper();
                for (WorkShop work : workShops) {
                    WorkShopDTO dto =  map.mapWorkShopToDTO(work);
                    workShopDTOS.add(dto);
                }
            }
        } catch (InvalidEntryException | DAOException e) {
            throw new ServiceException(e);
        }
        return workShopDTOS ;
    }

    /**
     * Retrieves a workshop based on the specified ID.
     *
     * @param id The ID of the workshop to be retrieved.
     * @return A WorkShop object representing the workshop with the specified ID.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public WorkShopDTO getWorkShopById(int id) throws ServiceException{
        WorkShopDAO dao =  new WorkShopDAO() ;
        try {
            WorkShopMapper map = new WorkShopMapper();
            return map.mapWorkShopToDTO(dao.getWorkShopsById(id));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
    /**
     * Retrieves a list of workshop IDs based on the specified workshop type.
     *
     * @param type The type of workshop for which to retrieve workshop IDs.
     * @return A List of integers representing workshop IDs of the specified type.
     *         The list may be empty if no workshops of the given type are found.
     * @throws ServiceException If there is an issue with input validation or accessing the database.
     */
    public List<Integer> getWorkShopByType(int type) throws ServiceException {
        Validations validate = new Validations();
        List<Integer> arr = new ArrayList<>();
        WorkShopDAO dao = new WorkShopDAO();
        try {

            if (validate.workshopType(type)) {
                arr = dao.getWorkshopsByType(type);
            }
            return arr ;
        }catch (DAOException e){
            throw new ServiceException(e);
        }

    }
    public boolean updateWorkshopPassword(long number , String newPassword)throws ServiceException{
        try {

            Validations validate = new Validations();
            if(validate.loginCredentialValidation(number,newPassword)) {
                WorkShop workShop = workShopDao.findWorkShopByNumber(number);
                if (workShop.getId() != 0) {
                    return workShopDao.updateWorkShopPassword(workShop.getId(), newPassword);

                }

            }
            return false;

        } catch (DAOException |InvalidEntryException e) {
            throw new ServiceException(e);
        }


    }



}
