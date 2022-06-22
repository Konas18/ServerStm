package com.stm.controller;

import com.stm.Entity.*;
import com.stm.autentification.AuthenticationService;
import com.stm.dto.GetAllDoctorsScheduleByPersonalIdRqDto;
import com.stm.dto.GetAllServicesByPersonalIdRqDto;
import com.stm.dto.GetClientByIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.repository.UsersTokenRepository;
import com.stm.service.UserService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import java.sql.*;
import java.util.Date;
import java.util.List;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class MyController {
    @Inject
    UserService userService;
    @Inject
    AuthenticationService authenticationService;
    @Inject
    UsersTokenRepository usersTokenRepository;

    @Get( "/user/list")
    public List<Client> getUserList(@Header(AUTHORIZATION)String token){
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getList();
            }
        }
        return null;
    }

    @Post(value = "/user/find", consumes = MediaType.APPLICATION_JSON)
    public GetClientByIdRsDto getById(@Header(AUTHORIZATION)String token, @Body GetClientByIdRqDto getUserByIdRqDto) throws SQLException {
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getById(getUserByIdRqDto.getId());
            }
        }
        return null;
        //return userService.getById(getUserByIdRqDto.getId());
    }

    @Get(value = "/personal/findall", consumes = MediaType.APPLICATION_JSON)
    public List<Personal>  getAllPersonal(@Header(AUTHORIZATION)String token) throws SQLException {
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getAllPersonal();
            }
        }
        return null;
        //        return userService.getAllPersonal();
    }
    @Post(value = "/personal/findallservices", consumes = MediaType.APPLICATION_JSON)
    public List<PersonalService> getAllServiceByPersonal(@Header(AUTHORIZATION)String token,
                                                         @Body GetAllServicesByPersonalIdRqDto getAllServicesByPersonalIdRqDto) throws SQLException {
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getAllServiceByPersonal(getAllServicesByPersonalIdRqDto.getId());
            }
        }
        return null;
        //return userService.getAllServiceByPersonal(getAllServicesByPersonalIdRqDto.getId());
    }
    @Post(value = "/personal/findallschedule", consumes = MediaType.APPLICATION_JSON)
    public List<Doctorsschedule> getAllDoctorsscheduleByPersonal(@Header(AUTHORIZATION)String token,
                                                                 @Body GetAllDoctorsScheduleByPersonalIdRqDto getAllDoctorsScheduleByPersonalIdRqDto) throws SQLException {
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getAllDoctorsscheduleByPersonal(getAllDoctorsScheduleByPersonalIdRqDto.getId());
            }
        }
        return null;
        //return userService.getAllDoctorsscheduleByPersonal(getAllDoctorsScheduleByPersonalIdRqDto.getId());
    }

    @Post(value = "/personal/findpersonbyid", consumes = MediaType.APPLICATION_JSON)
    public Personal getPersonalById(@Header(AUTHORIZATION)String token,
                                    @Body GetAllDoctorsScheduleByPersonalIdRqDto getAllDoctorsScheduleByPersonalIdRqDto) throws SQLException {
        for (UsersToken ut : usersTokenRepository.getUsersTokenList()) {
            String userToken = ut.getUserToken();
            Long l = Long.parseLong(authenticationService.DecodeTokenDate(token));
            if (userToken.equals(token) || l > new Date().getTime()) {
                return userService.getPersonalById(getAllDoctorsScheduleByPersonalIdRqDto.getId());
            }
        }
        return null;
        //return userService.getPersonalById(getAllDoctorsScheduleByPersonalIdRqDto.getId());
    }
}
