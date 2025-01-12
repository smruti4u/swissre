package org.swissre.repository;

import org.swissre.entity.Employee;

import java.io.FileNotFoundException;
import java.util.Set;

public interface FileRepository {
    /**
     * Read file stored in resources folder. FileName Is Hardcoded but can be moved to config
     * @return Set<Employee>
     * @throws FileNotFoundException when file not found
     */
    Set<Employee> readFile() throws FileNotFoundException;
}
