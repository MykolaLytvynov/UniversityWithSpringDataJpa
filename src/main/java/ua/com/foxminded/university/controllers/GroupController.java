package ua.com.foxminded.university.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.university.entities.Course;
import ua.com.foxminded.university.entities.Group;
import ua.com.foxminded.university.service.CourseService;
import ua.com.foxminded.university.service.GroupService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/faculties/{idFaculty}/courses/{idCourse}/groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        log.info("Enter: getById('{}')", id);
        Group result = groupService.findById(id);
        model.addAttribute("group", result);
        log.info("Exit: {}", result);
        return "/groups/showOneGroup";
    }

    @GetMapping("/new")
    public String getPageCreateGroup(@PathVariable("idCourse") int idCourse, Model model) {
        log.info("Enter: getPageCreateGroup('{}')", idCourse);
        model.addAttribute("group", new Group());
        Course course = courseService.findById(idCourse);
        model.addAttribute("course", course);
        log.info("Exit: {}", course);
        return "groups/new";
    }

    @PostMapping()
    public String createGroup(@ModelAttribute("group") @Valid Group group,
                              BindingResult bindingResult,
                              @PathVariable("idCourse") int idCourse, Model model) {
        log.info("Enter: createGroup('{}')", group);
        if (bindingResult.hasErrors()) {
            model.addAttribute("course", courseService.findById(idCourse));
            return "groups/new";
        }
        Group result = groupService.save(group);
        model.addAttribute("group", result);
        log.info("Exit: {}", result);
        return "redirect:/faculties/{idFaculty}/courses/{idCourse}/groups/" + result.getId();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("Enter: delete('{}')", id);
        groupService.deleteById(id);
        log.info("delete('{}') was success", id);
        return "redirect:/faculties/{idFaculty}/courses/{idCourse}";
    }
}
