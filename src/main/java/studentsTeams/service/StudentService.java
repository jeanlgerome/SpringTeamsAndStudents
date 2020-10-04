package studentsTeams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studentsTeams.domain.Student;
import studentsTeams.domain.Team;
import studentsTeams.repos.StudentRepo;
import studentsTeams.repos.TeamRepo;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeamRepo teamRepo;

    public void addStudentToTeam(String studentFio, String teamNumber) {
        Team studentTeam = teamRepo.findFirstByTeamNumber(teamNumber);
        int teamSize = studentTeam.getSize();
        Student newStudent = new Student(studentFio, studentTeam);
        studentRepo.save(newStudent);
        studentTeam.setSize(++teamSize);
        teamRepo.save(studentTeam);
    }

    public void deleteStudent(String studentId, String teamNumber) {
        Team studentTeam = teamRepo.findFirstByTeamNumber(teamNumber);
        int teamSize = studentTeam.getSize();
        studentRepo.deleteById(Long.parseLong(studentId));
        studentTeam.setSize(--teamSize);
        teamRepo.save(studentTeam);
    }

    public List<Student> getStudentsByTeamNumber(String teamNumber) {
        Team studentTeam = teamRepo.findFirstByTeamNumber(teamNumber);
        return studentRepo.findByTeamOrderByFioAsc(studentTeam);
    }
}
