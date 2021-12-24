package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.repository.EmployeeRepository;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.entities.person.Employee;
import ua.com.foxminded.university.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SubjectService subjectService;

    public Employee save(Employee employee) {
        log.debug("save('{}') called", employee);
        Employee result = employeeRepository.save(employee);
        log.debug("save('{}') returned '{}'", employee, result);
        return result;
    }

    public Employee findById(Integer id) {
        log.debug("called :: findById('{}')", id);
        NotFoundException notFoundException = new NotFoundException("Employee not found by id = " + id);
        Employee result = employeeRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        result.setName(result.getName().trim());
        result.setLastName(result.getLastName().trim());
        log.debug("findById('{}') returned '{}'", id, result);
        return result;
    }


    public List<Employee> findAll() {
        log.debug("findAll() called");
        List<Employee> result = employeeRepository.findAll();
        log.debug("findAll() returned '{}'", result);
        return result;
    }

    public long count() {
        log.debug("count() called");
        long result = employeeRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        employeeRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(Employee employee) {
        log.debug("delete('{}') called", employee);
        employeeRepository.delete(employee);
        log.debug("delete('{}') was success", employee);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        employeeRepository.deleteAll();
        log.debug("deleteAll() was success");
    }


    public void update(Employee employee) {
        log.debug("update('{}') called", employee);
        employeeRepository.update(employee.getName(), employee.getLastName(), employee.getSalary(),
                employee.getPosition(), employee.getId());
        log.debug("update('{}') was success", employee);
    }

    public List<TeacherDto> getAllTeacher() {
        log.debug("getAllTeacher() called");
        List<TeacherDto> result = new ArrayList<>();
        List<Employee> teachers = employeeRepository.getAllTeacher();
        for (Employee teacher : teachers) {
            result.add(TeacherDto.builder().id(teacher.getId())
                    .name(teacher.getName())
                    .lastName(teacher.getLastName())
                    .position(teacher.getPosition())
                    .salary(teacher.getSalary())
                    .subjectList(subjectService.getAllSubjectsOneTeacher(teacher.getId()))
                    .build());
        }
        log.debug("getAllTeacher() returned '{}'", result);
        return result;
    }

}
