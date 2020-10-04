package studentsTeams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import studentsTeams.domain.Student;
import studentsTeams.service.StudentService;
import studentsTeams.service.TeamService;

import java.util.List;
import java.util.Map;

@Controller
public class StudentsController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/studentsPage/{teamNumber}")
    public String getStudents(@PathVariable String teamNumber,
                              Map<String, Object> model) {
        model.put("teamNumber", teamNumber);
        if (teamService.teamIsExisting(teamNumber)) {
            return "studentsPage";
        }
        return "404";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentsList/{teamNumber}")
    public @ResponseBody
    List<Student> getStudentsList(@PathVariable String teamNumber
    ) {
        List<Student> studentsList = studentService.getStudentsByTeamNumber(teamNumber);
        return studentsList;
    }

    @PostMapping("/addStudent/")
    public void addStudent(@RequestParam String StudentFio, @RequestParam String teamNumber) {
        studentService.addStudentToTeam(StudentFio, teamNumber);
    }

    @PostMapping("/deleteStudent/")
    public void deleteStudent(@RequestParam String StudentId, @RequestParam String teamNumber) {
        studentService.deleteStudent(StudentId, teamNumber);
    }
}
