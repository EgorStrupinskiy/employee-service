package com.strupinski.employeeservice.dto.converter;//package com.innowise.employeeserviceee.dto.converter;//package com.innowise.employeeserviceee.dto.converter;

import com.strupinski.employeeservice.dto.EmployeeDTO;
import com.strupinski.employeeservice.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee entity);

    Employee toEntity(EmployeeDTO dto);

}
