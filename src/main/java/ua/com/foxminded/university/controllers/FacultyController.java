package ua.com.foxminded.university.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.university.entities.Faculty;
import ua.com.foxminded.university.service.FacultyService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping
    public String getAll(Model model) {
        log.info("getAll() called");
        List<Faculty> result = facultyService.findAll();
        model.addAttribute("faculties", result);
        log.info("Exit: {}", result);
        return "/faculties/getAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        log.info("Enter: findById('{}')", id);
        Faculty result = facultyService.findById(id);
        model.addAttribute("faculty", result);
        log.info("Exit: {}", result);
        return "/faculties/showOneFaculty";
    }

    @GetMapping("/new")
    public String getPageCreateFaculty(Model model) {
        log.info("getPageCreateEmployee() called");
        model.addAttribute("faculty", new Faculty());
        return "/faculties/new";
    }

    @PostMapping()
    public String createFaculty(@ModelAttribute("faculty") Faculty faculty) {
        log.info("Enter: createFaculty('{}')", faculty);
        Faculty result = facultyService.save(faculty);
        log.info("Exit: {}", result);
        return "redirect:/faculties/" + result.getId();
    }

    @GetMapping("/{id}/edit")
    public String getPageEdit(Model model, @PathVariable("id") int id) {
        log.info("Enter: getPageEdit('{}')", id);
        Faculty result = facultyService.findById(id);
        model.addAttribute("faculty", result);
        log.info("Exit: {}", result);
        return "/faculties/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("faculty") Faculty faculty,
                         @PathVariable("id") int id, Model model) {
        log.info("Enter: update('{}', '{}')", faculty, id);
        faculty.setId(id);
        facultyService.update(faculty);
        Faculty result = facultyService.findById(faculty.getId());
        model.addAttribute("faculty", result);
        log.info("Exit: {}", result);
        return "/faculties/showOneFaculty";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("Enter: delete('{}')", id);
        facultyService.deleteById(id);
        log.info("delete('{}') was success", id);
        return "redirect:/faculties";
    }

}
