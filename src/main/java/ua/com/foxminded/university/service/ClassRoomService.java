package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.repository.ClassRoomRepository;
import ua.com.foxminded.university.entities.ClassRoom;
import ua.com.foxminded.university.exception.NotFoundException;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;

    public ClassRoom save(ClassRoom classRoom) {
        log.debug("save('{}') called", classRoom);
        ClassRoom result = classRoomRepository.save(classRoom);
        log.debug("save('{}') returned '{}'", classRoom, result);
        return result;
    }

    public ClassRoom findById(Integer id) {
        log.debug("findById('{}') called", id);
        NotFoundException notFoundException = new NotFoundException("Class room not found by id = " + id);
        ClassRoom result = classRoomRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        result.setName(result.getName().trim());
        result.setDescription(result.getDescription().trim());
        log.debug("findById('{}') returned '{}'", id, result);
        return result;
    }


    public List<ClassRoom> findAll() {
        log.debug("findAll() called");
        List<ClassRoom> result = classRoomRepository.findAll();
        log.debug("findAll() returned '{}'", result);
        return result;
    }

    public long count() {
        log.debug("count() called");
        long result = classRoomRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        classRoomRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(ClassRoom classRoom) {
        log.debug("delete('{}') called", classRoom);
        classRoomRepository.delete(classRoom);
        log.debug("delete('{}') was success", classRoom);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        classRoomRepository.deleteAll();
        log.debug("deleteAll() was success");
    }

    public void update(ClassRoom classRoom) {
        log.debug("update('{}') called", classRoom);
        classRoomRepository.update(classRoom.getName(), classRoom.getDescription(), classRoom.getId());
        log.debug("update('{}') was success", classRoom);
    }
}