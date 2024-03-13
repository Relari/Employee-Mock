package com.pe.relari.employee.dao;

import static com.pe.relari.employee.util.TestUtil.buildEmployeeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pe.relari.employee.dao.impl.EmployeeDaoImpl;
import com.pe.relari.employee.dao.repository.EmployeeRepository;
import com.pe.relari.employee.exception.ApiException;
import com.pe.relari.employee.model.domain.Employee;
import com.pe.relari.employee.model.entity.EmployeeEntity;
import com.pe.relari.employee.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDaoImpl employeeDao;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {

        EmployeeEntity employeeEntity = buildEmployeeEntity();

        Mockito.when(employeeRepository.findAll())
                .thenReturn(Collections.singletonList(employeeEntity));

        List<Employee> employees = employeeDao.findAll();

//        assertEquals(employeeEntity.getId(), employees.get(0).getIdEmployee());
        assertEquals(employeeEntity.getFirstName(), employees.get(0).getFirstName());
//        assertEquals(employee.getLastName(), employees.get(0).getLastName());
        assertEquals(employeeEntity.getSex(), employees.get(0).getSex());
//        assertEquals(employeeEntity.getAddress().getEmail(), employees.get(0).getAddress().getEmail());
//        assertEquals(employeeEntity.getAddress().getPhoneNumber(), employees.get(0).getAddress().getPhoneNumber());

    }

    @Test
    void deleteAll() {

        EmployeeEntity employee = buildEmployeeEntity();

        employeeRepository.deleteAll();

        employeeDao.deleteAll();

        assertNotNull(employee);

    }

    @Test
    void deleteById() {

        Integer id = 1;

        employeeRepository.deleteById(Mockito.anyInt());

        employeeDao.deleteById(id);

        assertNotNull(id);
    }

    @Test
    void findById() {

        EmployeeEntity employeeEntity = buildEmployeeEntity();

        Mockito.when(employeeRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(employeeEntity));

        Employee employeeDomain = employeeDao.findById(1);
//        assertEquals(employeeEntity.getIdEmployee(), employeeDomain.getIdEmployee());
        assertEquals(employeeEntity.getFirstName(), employeeDomain.getFirstName());
//        assertEquals(employee.getLastName(), employees.get(0).getLastName());
        assertEquals(employeeEntity.getSex(), employeeDomain.getSex());
//        assertEquals(employeeEntity.getAddress().getEmail(), employeeDomain.getAddress().getEmail());
//        assertEquals(employeeEntity.getAddress().getPhoneNumber(), employeeDomain.getAddress().getPhoneNumber());


    }

    @Test
    void findByIdError() {

        Mockito.when(employeeRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> employeeDao.findById(2));

    }

    @Test
    void save() {

        EmployeeEntity employeeEntity = TestUtil.buildEmployeeEntity();

        Mockito.when(employeeRepository.save(Mockito.any()))
                .thenReturn(employeeEntity);

        Employee employee = TestUtil.buildEmployee();
        employeeDao.save(employee);

        //        assertEquals(employeeEntity.getIdEmployee(), employeeDomain.getIdEmployee());
        assertEquals(employeeEntity.getFirstName(), employee.getFirstName());
//        assertEquals(employee.getLastName(), employees.get(0).getLastName());
        assertEquals(employeeEntity.getSex(), employee.getSex());
//        assertEquals(employeeEntity.getAddress().getEmail(), employeeDomain.getAddress().getEmail());
//        assertEquals(employeeEntity.getAddress().getPhoneNumber(), employeeDomain.getAddress().getPhoneNumber());

    }
}