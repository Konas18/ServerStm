package com.stm.service;

import com.stm.Entity.*;
import com.stm.dto.GetAllAppointmentByUserIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.dto.NewAppointmentRqDto;
import com.stm.dto.SendFileRqDto;
import com.stm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.text.ParseException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PersonalServiceRepository personalServiceRepository;
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    StatuteRepository statuteRepository;
    @Autowired
    ClinicRepository clinicRepository;

    @Override
    public GetClientByIdRsDto getById(int id) {
        GetClientByIdRsDto getClientByIdRsDto = new GetClientByIdRsDto();
        getClientByIdRsDto.setСlient(clientRepository.getClientById(id));
        return getClientByIdRsDto;
    }

    @Override
    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    @Override
    public List<PersonalService> getAllServiceByPersonal(int id) {
        return personalServiceRepository.findAllByPersonalIdOrderById(id);
    }

    @Override
    public List<Doctorsschedule> getAllDoctorsscheduleByPersonal(int id) {
        return doctorScheduleRepository.findByPersonidIdOrderByDay(id);
    }

    @Override
    public Personal getPersonalById(int id) {
        return personalRepository.getPersonalById(id);
    }

    @Override
    public boolean NewAppoitment(NewAppointmentRqDto newAppointmentRqDto) throws ParseException {
        Appointment newAppointment = new Appointment(newAppointmentRqDto.getReceptionTime(), clientRepository.getClientById(newAppointmentRqDto.getClientid()),
                clinicRepository.getClinicById(newAppointmentRqDto.getClinicid()), personalRepository.getPersonalById(newAppointmentRqDto.getPersonalid()),
                newAppointmentRqDto.getCabinetNumber(), statuteRepository.getStatuteById(newAppointmentRqDto.getStatus()));
        Appointment appointment = appointmentRepository.save(newAppointment);
        if (appointment != null) return true;
        else return false;
    }

    @Override
    public List<Appointment> GetAllAppointmentByUserId(GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto) {
        return appointmentRepository.findByClientidId(getAllAppointmentByUserIdRqDto.getId());
    }
}
