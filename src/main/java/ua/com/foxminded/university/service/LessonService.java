package ua.com.foxminded.university.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.repository.LessonRepository;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.entities.Group;
import ua.com.foxminded.university.entities.Lesson;
import ua.com.foxminded.university.exception.NotFoundException;
import ua.com.foxminded.university.formatter.LessonDtoFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final GroupService groupService;
    private final LessonDtoFormatter lessonDtoFormatter = new LessonDtoFormatter();

    public Lesson save(Lesson lesson, List<Integer> lessonForGroups) {
        log.debug("save('{}', '{}') called", lesson, lessonForGroups);
        List<Group> groupsOfOneLesson = new ArrayList<>();
        for (Integer idGroup : lessonForGroups) {
            groupsOfOneLesson.add(groupService.findById(idGroup));
        }
        if (groupsOfOneLesson != null) lesson.setGroups(groupsOfOneLesson);
        Lesson result = lessonRepository.save(lesson);
        return result;
    }

    public LessonDto findLessonDtoById(Integer id) {
        log.debug("findById('{}') called", id);
        NotFoundException notFoundException = new NotFoundException("Lesson not found by id = " + id);
        Lesson result = lessonRepository.findById(id).orElse(null);
        if (result == null) throw notFoundException;
        LessonDto lessonDto = new LessonDto();
        lessonDto = lessonDtoFormatter.getLessonDtoByLesson(result);
        log.debug("findById('{}') returned '{}'", id, lessonDto);
        return lessonDto;
    }


    public List<LessonDto> findAll() {
        log.debug("findAll() called");
        List<Lesson> result = lessonRepository.findAll();
        List<LessonDto> lessonsDto = new ArrayList<>();
        result.stream().forEach(lesson -> lessonsDto.add(lessonDtoFormatter.getLessonDtoByLesson(lesson)));
        log.debug("findAll() returned '{}'", lessonsDto);
        return lessonsDto;
    }

    public long count() {
        log.debug("count() called");
        long result = lessonRepository.count();
        log.debug("count() returned '{}'", result);
        return result;
    }

    public void deleteById(Integer id) {
        log.debug("deleteById('{}') called", id);
        lessonRepository.deleteById(id);
        log.debug("deleteById('{}') was success", id);
    }

    public void delete(Lesson lesson) {
        log.debug("delete('{}') called", lesson);
        lessonRepository.delete(lesson);
        log.debug("delete('{}') was success", lesson);
    }

    public void deleteAll() {
        log.debug("deleteAll() called");
        lessonRepository.deleteAll();
        log.debug("deleteAll() was success");
    }

    public void update(Lesson lesson, List<Integer> lessonForGroups) {
        log.debug("update('{}') called", lesson);
        List<Group> groupsOfOneLesson = new ArrayList<>();
        if (lessonForGroups.isEmpty()) {
            lesson.setGroups(lessonRepository.findById(lesson.getId()).get().getGroups());
        } else {
            for (Integer idGroup : lessonForGroups) {
                groupsOfOneLesson.add(groupService.findById(idGroup));
            }
            lesson.setGroups(groupsOfOneLesson);
        }
        lessonRepository.update(lesson.getClassRoom(), lesson.getDateTime(), lesson.getDuration(),
                lesson.getSubject(), lesson.getGroups(), lesson.getId());
        log.debug("update('{}') was success", lesson);
    }

    public List<LessonDto> getLessonsBetweenDatesForGroup(LocalDateTime startTime, LocalDateTime endTime, Integer idGroup) {
        log.debug("getLessonsBetweenDatesForGroup('{}', '{}', '{}') called", startTime, endTime, idGroup);
        List<Lesson> result = lessonRepository.findAllByGroupsAndDateTimeBetween(groupService.findById(idGroup), startTime, endTime);
        List<LessonDto> lessonDtos = new ArrayList<>();
        result.stream().forEach(lesson -> lessonDtos.add(lessonDtoFormatter.getLessonDtoByLesson(lesson)));
        log.debug("getLessonsBetweenDatesForGroup('{}', '{}', '{}') returned '{}'", startTime, endTime, idGroup, result);
        return lessonDtos;
    }

    public List<LessonDto> getLessonsBetweenDatesForTeacher(LocalDateTime startTime, LocalDateTime endTime, Integer idTeacher) {
        log.debug("getLessonsBetweenDatesForTeacher('{}', '{}', '{}') called", startTime, endTime, idTeacher);
        List<Lesson> result = lessonRepository.findAllBySubject_Teacher_IdAndDateTimeBetween(idTeacher, startTime, endTime);
        List<LessonDto> lessonDtos = new ArrayList<>();
        result.stream().forEach(lesson -> lessonDtos.add(lessonDtoFormatter.getLessonDtoByLesson(lesson)));
        log.debug("getLessonsBetweenDatesForTeacher('{}', '{}', '{}') returned '{}'", startTime, endTime, idTeacher, result);
        return lessonDtos;
    }
}
