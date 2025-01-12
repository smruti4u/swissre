## Swissre Case Study

### ObJectives
1. Read from CSV File
2. Get List Of Managers earning less than 20% of their direct sub ordinates
3. Get List Of Managers earning more than 50% of their direct sub ordinates
4. Get Employees where CEO is more than 4 levels up

### Solution Approaches

- Objective 1 has been solved by CSVRepositoryImpl which reads from CSV and Updates employee list
- Objectives 2 and Objectives 3 has been solved using Manager Map and Employee Map which has been created by OrgRepositoryImpl
- Objective 4 has been solved by using BFS.

> Unit Test uses JUNIT and Mockito for mocking

### Concepts Used
- Single Responsibility
- Interface Segregation
  
