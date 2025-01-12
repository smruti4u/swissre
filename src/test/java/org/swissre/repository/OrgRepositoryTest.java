package org.swissre.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.swissre.entity.Employee;
import org.swissre.repository.impl.CSVRepositoryImpl;
import org.swissre.repository.impl.OrgRepositoryImpl;
import org.swissre.utils.CreateTestData;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrgRepositoryTest {

    private OrgRepository orgRepository;

    @Mock
    private CSVRepositoryImpl csvRepository;

    List<Employee> employeeList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        orgRepository =  new OrgRepositoryImpl(csvRepository);
        employeeList = CreateTestData.load();

    }

    @Test
    void loadData() throws FileNotFoundException {
        TreeSet<Employee> employeeSet = new TreeSet<>(employeeList);

        // mock CSV repository
        when(csvRepository.readFile()).thenReturn(employeeSet);

        // invoke
        orgRepository.loadData();

        Set<String> managers =  new HashSet<>();
        employeeList.forEach(e -> managers.add(e.getManagerId()));

        // assert
        assertNotNull(OrgRepositoryImpl.managerMap.keySet());
        assertEquals(managers.size(), OrgRepositoryImpl.managerMap.keySet().size());

    }

    @Test
    void getAverageSalary() {
        String managerId = "123";
        BigDecimal averageSalary =  orgRepository.getAverageSalary(managerId);

        //invoke
        BigDecimal calculatedAverage = orgRepository.getAverageSalary(managerId);

        //assert
        assertEquals(0, calculatedAverage.compareTo(new BigDecimal("45500")));
    }
}