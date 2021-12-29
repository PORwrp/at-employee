package com.demo.at.employee.mapper;

import com.demo.at.employee.model.Employee;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(EmployeeMapperImp.class)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "effectiveDate", constant = "")
    @Mapping(target = "role", ignore = true)
    Employee mappingEmployeeRequiredField(Employee target);

    Employee mappingEmployee(@MappingTarget Employee oldEmp, Employee newEmp);

}
