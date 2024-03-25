package org._07_automappingobjects;

import org._07_automappingobjects.models.dto.ManagerDTO;
import org._07_automappingobjects.models.entities.Employee;
import org._07_automappingobjects.models.entities.WorkStatus;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //exercise 01 - Create Employee and EmployeeDTO classes.
        //exercise 02 - Create ManagerDTO and format it.


        ModelMapper mapper = new ModelMapper();
        Employee manager = new Employee(
                "Steve", "Jobbsen", BigDecimal.TEN, LocalDate.now(), "Dubai",
                WorkStatus.HOLIDAY, null, List.of()
        );

        Employee first_employee = new Employee
                ("Stephen", "Bjorn", BigDecimal.valueOf(4300),
                        LocalDate.of(2003, 06, 01), "New York",
                        WorkStatus.PRESENT, null, List.of());

        Employee second_employee = new Employee
                ("Kirilyc", "Lefi", BigDecimal.valueOf(4400),
                        LocalDate.of(1999, 03, 21), "London",
                        WorkStatus.PRESENT, null, List.of());

        Employee third_employee = new Employee
                ("Kopp", "Spidok", BigDecimal.valueOf(2000.21),
                        LocalDate.of(1989, 01, 01), "London",
                        WorkStatus.PRESENT, null, List.of());

        manager.setEmployees(List.of(first_employee, second_employee, third_employee));

        ManagerDTO managerDTO = mapper.map(manager, ManagerDTO.class);

        System.out.println(managerDTO);
    }
}
