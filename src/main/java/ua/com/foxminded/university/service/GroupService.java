package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.repository.GroupRepository;
import ua.com.foxminded.university.entities.Group;
import ua.com.foxminded.university.exception.NotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group save(Group group) {
        log.debug("save('{}') called", group);
        Group result = groupRepository.save(group);
        log.debug("save('{}') returned '{}'", group, result);
        return result;
    }

    public Group findById(Integer id) {
        log.debug("findById('{}') called", id);
        NotFoundException notFoundException = new NotFoundException("Group not found by id = " + id);
        Group result = groupRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        log.debug("findById('{}') returned '{}'", id, result);
        return result;
    }


    public List<Group> findAll() {
        log.debug("findAll() called");
        List<Group> result = groupRepository.findAll();
        log.debug("findAll() returned '{}'", result);
        return result;
    }

    public long count() {
        log.debug("count() called");
        long result = groupRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        groupRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(Group group) {
        log.debug("delete('{}') called", group);
        groupRepository.delete(group);
        log.debug("delete('{}') was success", group);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        groupRepository.deleteAll();
        log.debug("deleteAll() was success");
    }
}
