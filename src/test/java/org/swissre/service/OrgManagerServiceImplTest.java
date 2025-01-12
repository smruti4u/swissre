package org.swissre.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.swissre.entity.Employee;
import org.swissre.entity.ManagerEarning;
import org.swissre.repository.OrgRepository;
import org.swissre.repository.impl.OrgRepositoryImpl;
import org.swissre.service.impl.OrgManagerServiceImpl;
import org.swissre.utils.CreateTestData;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * test class for OrgManagerServiceImpl
 */
public class OrgManagerServiceImplTest {

    /**
     * Mocked OrgRepository
     */
    @Mock
    private OrgRepository orgRepository;

    /**
     * Testing with concrete class OrgManagerServiceImpl
     */
    private OrgManagerServiceImpl orgManagerService;

    /**
     * List Of employees
     */
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        orgManagerService = new OrgManagerServiceImpl(orgRepository);
        employees = CreateTestData.load();
    }

    @Test
    void testBootStrapApplication() throws Exception {
        doNothing().when(orgRepository).loadData();
        orgManagerService.bootStrapApplication();
        verify(orgRepository, times(1)).loadData();
    }

    @Test
    void testGetManagerEarningLess() {

        int averageSalary = 50000;

        // Mock the OrgRepository behavior
        when(orgRepository.getAverageSalary(anyString())).thenReturn(BigDecimal.valueOf(averageSalary));
        BigDecimal lowestSalary = BigDecimal.valueOf(averageSalary * 0.2 + averageSalary); // 20% more from average Salary

        // Get All Managers Earnings less than the lowest salary from employees list
        List<Employee> employee = employees.stream().filter(
                e -> OrgRepositoryImpl.managerMap.containsKey(e.getId()) && e.getSalary()
                        .compareTo(lowestSalary) < 0).collect(Collectors.toList());

        // Invoke
        List<Employee> managers = orgManagerService.getManagerEarning(ManagerEarning.LESS);

        // assert
        assertEquals(employee.size(), managers.size());
        assertNotNull(managers.get(0).getEarningLessPercentage());
    }

    @Test
    void testGetManagerEarningMore() {

        int averageSalary = 20000;

        // Mock the OrgRepository behavior
        when(orgRepository.getAverageSalary(anyString())).thenReturn(BigDecimal.valueOf(averageSalary));
        BigDecimal highest = BigDecimal.valueOf(averageSalary * 0.5 + averageSalary); // 50% more from average Salary

        // Get All Managers Earnings more than the highest salary from employees list
        List<Employee> employee = employees.stream().filter(
                e -> OrgRepositoryImpl.managerMap.containsKey(e.getId()) && e.getSalary()
                        .compareTo(highest) > 0).collect(Collectors.toList());
        // invoke
        List<Employee> managers = orgManagerService.getManagerEarning(ManagerEarning.MORE);

        // assert
        assertEquals(employee.size(), managers.size());
        assertNotNull(managers.get(0).getEarningMorePercentage());
    }

    @Test
    void testGetEmployeesWithLongReporting() {
        // invoke
        List<Employee> employeesWithLongReporting = orgManagerService.getEmployeesWithLongReporting();

        // assert
        assertEquals(employeesWithLongReporting.get(0).getId(), "133");
        assertTrue(employeesWithLongReporting.get(0).getLevel() > 4);
        assertNotNull(employeesWithLongReporting);
    }

    @Test
    void testPrintAllEmployee() {
        orgManagerService.printAllEmployee();
    }
}