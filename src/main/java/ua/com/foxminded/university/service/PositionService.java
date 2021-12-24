package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.repository.PositionRepository;
import ua.com.foxminded.university.entities.Position;
import ua.com.foxminded.university.exception.NotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public Position save(Position position) {
        log.debug("save('{}') called", position);
        Position result = positionRepository.save(position);
        log.debug("save('{}') returned '{}'", position, result);
        return result;
    }

    public Position findById(Integer id) {
        log.debug("findById('{}') called", id);
        NotFoundException notFoundException = new NotFoundException("Position not found by id = " + id);
        Position result = positionRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        result.setName(result.getName().trim());
        log.debug("findById('{}') returned '{}'", id, result);
        return result;
    }

    public List<Position> findAll() {
        log.debug("findAll() called");
        List<Position> result = positionRepository.findAll();
        log.debug("findAll() returned '{}'", result);
        return result;
    }

    public long count() {
        log.debug("count() called");
        long result = positionRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        positionRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(Position position) {
        log.debug("deleteById('{}') called", position);
        positionRepository.delete(position);
        log.debug("deleteById('{}') was success", position);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        positionRepository.deleteAll();
        log.debug("deleteAll() was success");
    }

    public void update(Position position) {
        log.debug("update('{}') called", position);
        positionRepository.update(position.getName(), position.getId());
        log.debug("update('{}') was success", position);
    }
}
