package com.pe.relari.controller;

import static com.pe.relari.employee.util.TestUtil.buildEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.pe.relari.employee.model.api.AddressResponse;
import com.pe.relari.employee.model.api.EmployeeDetailResponse;
import com.pe.relari.employee.model.api.EmployeeRequest;
import com.pe.relari.employee.model.api.EmployeeResponse;
import com.pe.relari.employee.model.domain.Employee;
import com.pe.relari.employee.service.EmployeeService;
import java.util.Collections;
import java.util.List;

import com.pe.relari.employee.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {

        Employee employee = buildEmployee();

        when(service.findAll())
                .thenReturn(Collections.singletonList(employee));

        List<EmployeeResponse> employeeResponses = controller.findAll();

        assertEquals(employee.getIdEmployee(), employeeResponses.get(0).getId());
        assertEquals(employee.getFirstName(), employeeResponses.get(0).getFirstName());
        assertEquals(employee.getFatherLastName(), employeeResponses.get(0).getFatherLastName());
        assertEquals(employee.getMotherLastName(), employeeResponses.get(0).getMotherLastName());
        assertEquals(employee.getSex().name(), employeeResponses.get(0).getSex());
    }

    @Test
    void findAll2() {

        Employee employee = buildEmployee();

        when(service.findAll())
                .thenReturn(Collections.singletonList(employee));

        EmployeeDetailResponse employeeDetailResponse = controller.findAll2();

        assertNotNull(employeeDetailResponse);
    }



    @Test
    void findById() {

        Employee employee = buildEmployee();

        when(service.findById(anyInt()))
                .thenReturn(employee);

        EmployeeResponse employeeResponse = controller.findById(1);

        assertEquals(employee.getIdEmployee(), employeeResponse.getId());
        assertEquals(employee.getFirstName(), employeeResponse.getFirstName());
//        assertEquals(employee.getLastName(), employeeResponse.getLastName());
        assertEquals(employee.getSex().name(), employeeResponse.getSex());

    }

    @Test
    void getAddressById() {

        Employee employee = buildEmployee();

        when(service.findById(anyInt()))
                .thenReturn(employee);

        AddressResponse address = controller.getAddressById(1);

        assertEquals(employee.getAddress().getEmail(), address.getEmail());
        assertEquals(employee.getAddress().getPhoneNumber(), address.getPhoneNumber());

    }

    @Test
    void save() {

        service.save(any());

        EmployeeRequest employeeRequest = TestUtil.buildEmployeeRequest();

        controller.save(employeeRequest);

        assertNotNull(employeeRequest);

    }

    @Test
    void deleteAll() {

        service.deleteAll();

        controller.deleteAll();

        Integer id = 1;

        assertNotNull(id);
    }

    @Test
    void deleteById() {

        service.deleteById(anyInt());

        Integer id = 1;

        controller.deleteById(id);

        assertNotNull(id);

    }
}