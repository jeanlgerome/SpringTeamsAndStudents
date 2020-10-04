package studentsTeams.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import studentsTeams.domain.Team;

public interface TeamRepo extends PagingAndSortingRepository<Team, Long> {

    Team findFirstByTeamNumber(String teamNumber);
}
