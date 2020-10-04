package studentsTeams.repos;

import org.springframework.data.repository.CrudRepository;
import studentsTeams.domain.Student;
import studentsTeams.domain.Team;

import java.util.List;

public interface StudentRepo extends CrudRepository<Student, Long> {

    List<Student> findByTeamOrderByFioAsc(Team team);

    @Override
    void deleteById(Long aLong);
}
