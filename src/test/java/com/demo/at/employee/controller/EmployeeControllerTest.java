package com.demo.at.employee.controller;

import com.demo.at.employee.controller.advice.GlobalExceptionHandler;
import com.demo.at.employee.model.Employee;
import com.demo.at.employee.repository.EmployeeRepository;
import com.demo.at.employee.service.EmployeeService;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static com.demo.at.employee.util.CommonUtils.objectToJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Employee employeeObj1 = new Employee()
            .setUserId("00001")
            .setFirstName("test-user")
            .setLastName("lastname")
            .setEffectiveDate(new Date())
            .setRole("Dev");
    private final Employee employeeObj2 = new Employee()
            .setUserId("00002")
            .setFirstName("mock-user")
            .setLastName("lastname")
            .setEffectiveDate(new Date())
            .setRole("SA");

    @BeforeEach
    public void before() {
        this.mvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
        employeeRepository.save(employeeObj1);
    }

    @Test
    void retrieveAllEmployee_success() throws Exception {
        String url = "/v1/employee";
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(url)
                .build();

        when(employeeService.retrieveAllEmployee()).thenReturn(Collections.singletonList(employeeObj1));
        mvc.perform(
                get(uriComponents.toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].userId").value("00001"));
    }

    @Test
    void retrieveEmployee_success() throws Exception {
        Map<String, Object> pathVariable = new HashMap<>();
        pathVariable.put("userId", "00001");

        String url = "/v1/employee/{userId}";
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(url).uriVariables(pathVariable)
                .build();

        when(employeeService.retrieveEmployee("00001")).thenReturn(employeeObj1);
        mvc.perform(
                get(uriComponents.toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("00001"));
    }

    @Test
    void saveEmployee_success() throws Exception {
        String url = "/v1/employee";
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(url)
                .build();

        when(employeeService.saveEmployee(employeeObj2.setUserId("00003"))).thenReturn(employeeObj2.setUserId("00003"));
        mvc.perform(
                post(uriComponents.toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(Objects.requireNonNull(objectToJson(employeeObj2.setUserId("00003")))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("00003"))
                .andExpect(jsonPath("$.firstName").value("mock-user"))
                .andExpect(jsonPath("$.role").value("SA"));
    }

    @Test
    void modifyEmployee_success() throws Exception {
        String url = "/v1/employee";
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(url)
                .build();

        when(employeeService.modifyEmployee(employeeObj1.setAddress("abcd"))).thenReturn(employeeObj1.setAddress("abcd"));
        mvc.perform(
                patch(uriComponents.toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(Objects.requireNonNull(objectToJson(employeeObj1.setAddress("abcd")))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("00001"))
                .andExpect(jsonPath("$.address").value("abcd"))
                .andExpect(jsonPath("$.role").value("Dev"));
    }

    @Test
    void deleteEmployee_success() throws Exception {
        Map<String, Object> pathVariable = new HashMap<>();
        pathVariable.put("userId", "00001");

        String url = "/v1/employee/{userId}";
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(url).uriVariables(pathVariable)
                .build();

        when(employeeService.deleteEmployee("00001")).thenReturn("deleted");
        mvc.perform(
                delete(uriComponents.toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("deleted"));
    }

}
