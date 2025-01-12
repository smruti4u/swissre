package org.swissre.repository;


import org.swissre.entity.Employee;
import org.swissre.utils.Constants;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Repository Class For Reading CSV File
 */
public class CSVRepository {


    /**
     * Read file stored in resources folder. FileName Is Hardcoded but can be moved to config
     * @return Set<Employee>
     * @throws FileNotFoundException when file not found
     */
    public Set<Employee> readFile() throws FileNotFoundException {
        Set<Employee> employeeList = new HashSet<>();

        try (InputStream inputStream = CSVRepository.class.getClassLoader().getResourceAsStream(Constants.FILE_PATH)) {
            if (inputStream == null) {
                throw new FileNotFoundException("file not found in resource folder");
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                String[] values = new String[5];
                while ((line = br.readLine()) != null) {

                    try {
                        values = line.split(Constants.SEPARATOR);
                        createEmployeeListFromCSV(values, employeeList);
                    } catch (Exception exception) {
                        System.out.println("Error in processing employee record with id : " + values[0]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found in path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return employeeList;
    }

    /**
     *
     * @param values from CSV row
     * @param employeeList list of employess
     */
    private static void createEmployeeListFromCSV(String[] values, Set<Employee> employeeList) {
        Employee employee = new Employee();
        employee.setId(values[0]);
        employee.setFirstName(values[1]);
        employee.setLastName(values[2]);
        employee.setSalary(new BigDecimal(values[3]));
        if (values.length == 5)
            employee.setManagerId(values[4]);

        employeeList.add(employee);
    }
}
