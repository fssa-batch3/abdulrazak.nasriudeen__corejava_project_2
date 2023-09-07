package com.fssa.reparo.service;
import com.fssa.reparo.dao.UserDao;
import com.fssa.reparo.dto.user.UserRequestDto;
import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class UserServiceTest {
    private final UserServices userServices = new UserServices();

    @Test
    @Order(1)
    void createUserTest() {
        UserRequestDto request = new UserRequestDto("Razak", 9840326001L, "abd123");
        UserServices user = new UserServices();
        try {
            user.registerUser(request);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Test
    @Order(2)
    void createUserSuccess() {
        UserDao userDao = new UserDao();
        try {
            User us = userDao.findUserByNumber(9840326001L);
            assertEquals("Razak", (us.getName()));


        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Order(3)
    void createUserTestFail() {
        UserRequestDto use = new UserRequestDto("Abdul", 98403265109L, "123456");

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.registerUser(use));
        String[] arr = exception.getMessage().split(":");

        assertEquals(" Invalid Number ", arr[arr.length - 1]);


    }

    @Test
    @Order(4)
    void loginTestSuccess() {
        try {
            assertEquals(35, userServices.loginUser(9840326515L, "pas123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Order(5)
    void loginTestFail() {

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.loginUser(9840326512L, "pas123"));
        String[] arr = exception.getMessage().split(":");
        assertEquals(" user not present ", arr[arr.length - 1]);


    }

    @Test
    @Order(6)
    void getUserByIdTestSuccess() {
        try {
            UserResponseDto dto = userServices.getUserById(35);
            assertEquals("Razak Test", dto.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Order(7)
    void getUserByIdTestFail() {

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.getUserById(24));
        String[] arr = exception.getMessage().split(":");

        assertEquals(" user not present", arr[arr.length - 1]);


    }

    @Test
    @Order(8)
    void getAllUsersTestSuccess() {
        try {
            List<UserResponseDto> users = userServices.getAllUsers();
            Assertions.assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Order(9)
    void updateUserPassword() {
        try {
            Assertions.assertTrue(userServices.updateUserPassword("pas123", 9840326515L));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(10)
    void updateUserPasswordFailInvalidNumber() {
        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.updateUserPassword("pas123", 9840326000L));
        String[] arr = exception.getMessage().split(":");
        assertEquals(" user not present", arr[arr.length - 1]);
    }

    @Test
    @Order(11)
    void updateUserPasswordFailInvalidPassword() {
        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.updateUserPassword("chennai", 9840326515L));
        String[] arr = exception.getMessage().split(":");
        assertEquals(" Invalid Password", arr[arr.length - 1]);
    }

    @Test
    @Order(12)
    void logoutSuccessTest() {
        try {
            Assertions.assertTrue(userServices.logOutUser(35));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(13)
    void logoutFailInvalidNumberTest() {

        ServiceException exception = assertThrows(ServiceException.class, () -> userServices.logOutUser(20));

        String[] arr = exception.getMessage().split(":");
        assertEquals(" user not present", arr[arr.length - 1]);
    }

    @Test
    @Order(14)
    void deleteUserAccount() {
        UserDao dao = new UserDao();
        try {
            Assertions.assertTrue(dao.removeUser(9840326001L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
}


