package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.entities.Faculty;
import ua.com.foxminded.university.exception.NotFoundException;
import ua.com.foxminded.university.repository.FacultyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty) {
        log.debug("save('{}') called", faculty);
        Faculty result = facultyRepository.save(faculty);
        log.debug("save('{}') was success. Returned '{}'", faculty, result);
        return result;
    }

    public Faculty findById(Integer id) {
        log.debug("findById('{}') called");
        NotFoundException notFoundException = new NotFoundException("Faculty not found by id = " + id);
        Faculty result = facultyRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        result.setName(result.getName().trim());
        result.setDescription(result.getDescription().trim());
        log.debug("findById('{}') returned '{}'", id, result);
        return result;
    }


    public List<Faculty> findAll() {
        log.debug("findAll() called");
        List<Faculty> result = facultyRepository.findAll();
        log.debug("findAll() returned '{}'", result);
        return result;
    }

    public long count() {
        log.debug("count() called");
        long result = facultyRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        facultyRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(Faculty faculty) {
        log.debug("delete('{}') called", faculty);
        facultyRepository.delete(faculty);
        log.debug("delete('{}') was success", faculty);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        facultyRepository.deleteAll();
        log.debug("deleteAll() was success");
    }

    public void update(Faculty faculty) {
        log.debug("update('{}') called", faculty);
        facultyRepository.update(faculty.getName(), faculty.getDescription(), faculty.getId());
        log.debug("update('{}') was success", faculty);
    }
}
