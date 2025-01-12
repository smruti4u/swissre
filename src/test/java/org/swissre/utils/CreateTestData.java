package org.swissre.utils;

import org.swissre.entity.Employee;
import org.swissre.repository.impl.OrgRepositoryImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Creates The Test Data
 */
public class CreateTestData {

    /**
     * Populates managerMap and employeeMap with test Data
     * @return List Of employees
     */
    public static List<Employee> load() {
        List<Employee> employees =  new ArrayList<>();

        // Create individual employee variables
        Employee joe = new Employee("123", "Joe", "Doe", BigDecimal.valueOf(60000), null);
        Employee martin = new Employee("124", "Martin", "Chekov", BigDecimal.valueOf(45000), "123");
        Employee bob = new Employee("125", "Bob", "Ronstad", BigDecimal.valueOf(47000), "123");
        Employee john = new Employee("126", "John", "Chekov", BigDecimal.valueOf(35000), "123");
        Employee boby = new Employee("127", "Boby", "Hairpin", BigDecimal.valueOf(55000), "123");
        Employee alice = new Employee("128", "Alice", "Hasacat", BigDecimal.valueOf(50000), "124");
        Employee hyatt = new Employee("129", "Hyatt", "Hardleaf", BigDecimal.valueOf(34000), "124");
        Employee surya = new Employee("130", "Surya", "Hyat", BigDecimal.valueOf(24000), "124");
        Employee rivin = new Employee("131", "Rivin", "Jose", BigDecimal.valueOf(64000), "130");
        Employee rohan = new Employee("132", "Rohan", "Panda", BigDecimal.valueOf(14000), "131");
        Employee sayali = new Employee("133", "Sayali", "Panda", BigDecimal.valueOf(78000), "132");

        employees.add(joe);
        employees.add(martin);
        employees.add(bob);
        employees.add(john);
        employees.add(boby);
        employees.add(alice);
        employees.add(hyatt);
        employees.add(surya);
        employees.add(rivin);
        employees.add(rohan);
        employees.add(sayali);

        // Populate the employeeMap
        OrgRepositoryImpl.employeeMap.put(joe.getId(), joe);
        OrgRepositoryImpl.employeeMap.put(martin.getId(), martin);
        OrgRepositoryImpl.employeeMap.put(bob.getId(), bob);
        OrgRepositoryImpl.employeeMap.put(john.getId(), john);
        OrgRepositoryImpl.employeeMap.put(boby.getId(), boby);
        OrgRepositoryImpl.employeeMap.put(alice.getId(), alice);
        OrgRepositoryImpl.employeeMap.put(hyatt.getId(), hyatt);
        OrgRepositoryImpl.employeeMap.put(surya.getId(), surya);
        OrgRepositoryImpl.employeeMap.put(rivin.getId(), rivin);
        OrgRepositoryImpl.employeeMap.put(rohan.getId(), rohan);
        OrgRepositoryImpl.employeeMap.put(sayali.getId(), sayali);

        // Populate the managerMap
        OrgRepositoryImpl.managerMap.computeIfAbsent(null, k -> new TreeSet<>()).add(joe); // CEO case
        OrgRepositoryImpl.managerMap.computeIfAbsent("123", k -> new TreeSet<>()).addAll(Arrays.asList(martin, bob, john, boby));
        OrgRepositoryImpl.managerMap.computeIfAbsent("124", k -> new TreeSet<>()).addAll(Arrays.asList(alice, hyatt, surya));
        OrgRepositoryImpl.managerMap.computeIfAbsent("130", k -> new TreeSet<>()).add(rivin);
        OrgRepositoryImpl.managerMap.computeIfAbsent("131", k -> new TreeSet<>()).add(rohan);
        OrgRepositoryImpl.managerMap.computeIfAbsent("132", k -> new TreeSet<>()).add(sayali);

        return employees;
    }
}
