package com.saru.Springboot.tutorial.service;

import com.saru.Springboot.tutorial.entity.Department;
import com.saru.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
//        mocking the data
Department department=
        Department.builder()
                .departmentName("IT")
                .departmentAddress("LA")
                .departmentCode("IT006")
                .departmentId(1L).build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartmentmentShouldFound(){
        String departmentName="IT";
        Department found=departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName,found.getDepartmentName());
    }
}