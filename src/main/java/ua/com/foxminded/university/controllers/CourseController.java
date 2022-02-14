package ua.com.foxminded.university.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.university.entities.Course;
import ua.com.foxminded.university.entities.Faculty;
import ua.com.foxminded.university.service.CourseService;
import ua.com.foxminded.university.service.FacultyService;

import javax.validation.Valid;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/faculties/{idFaculty}/courses")
public class CourseController {
    private final CourseService courseService;
    private final FacultyService facultyService;


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        log.info("Enter: getById('{}')", id);
        Course result = courseService.findById(id);
        model.addAttribute("course", result);
        log.info("Exit: {}", result);
        return "/courses/showOneCourse";
    }


    @GetMapping("/new")
    public String getPageCreateCourse(@PathVariable("idFaculty") int idFaculty, Model model) {
        log.info("Enter: getPageCreateCourse(idFaculty: '{}')", idFaculty);
        model.addAttribute("course", new Course());
        Faculty result = facultyService.findById(idFaculty);
        model.addAttribute("faculty", result);
        log.info("Exit: {}", result);
        return "/courses/new";
    }

    @PostMapping()
    public String createCourse(@ModelAttribute("course") @Valid Course course,
                               BindingResult bindingResult,
                               @PathVariable("idFaculty") int idFaculty, Model model) {
        log.info("Enter: createCourse('{}')", course);
        if (bindingResult.hasErrors()) {
            model.addAttribute("faculty", facultyService.findById(idFaculty));
            return "courses/new";
        }
        Course result = courseService.save(course);
        log.info("Exit: {}", result);
        return "redirect:/faculties/{idFaculty}/courses/" + result.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        log.info("Enter: deleteCourse('{}')", id);
        courseService.deleteById(id);
        log.info("deleteCourse('{}') was success", id);
        return "redirect:/faculties/{idFaculty}";
    }
}
